package com.hhy.job.executor.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import java.util.*;

/**
 * @author admin
 */
@SuppressWarnings("unchecked")
public class ApiUtils {

    private static final String WEBSITE = "https://www.jiandaoyun.com";

    //接口频率限制是否重试
    private final boolean retryIfRateLimited = true;
    private String urlGetWidgets;
    private String urlGetFormData;
    private String urlRetrieveData;
    private String urlUpdateData;
    private String urlCreateData;
    private String urlDeleteData;
    private String urlBatchCreate;
    private String apiKey;

    /**
     * @param appId   - 应用id
     * @param entryId - 表单id
     * @param apiKey  - apiKey
     */
    public ApiUtils(String appId, String entryId, String apiKey) {
        this.apiKey = apiKey;
        this.initUrl(appId, entryId);
    }

    private void initUrl(String appId, String entryId) {
        urlGetWidgets = WEBSITE + "/api/v1/app/" + appId + "/entry/" + entryId + "/widgets";
        urlGetFormData = WEBSITE + "/api/v1/app/" + appId + "/entry/" + entryId + "/data";
        urlRetrieveData = WEBSITE + "/api/v1/app/" + appId + "/entry/" + entryId + "/data_retrieve";
        urlUpdateData = WEBSITE + "/api/v1/app/" + appId + "/entry/" + entryId + "/data_update";
        urlCreateData = WEBSITE + "/api/v1/app/" + appId + "/entry/" + entryId + "/data_create";
        urlDeleteData = WEBSITE + "/api/v1/app/" + appId + "/entry/" + entryId + "/data_delete";
        urlBatchCreate = WEBSITE + "/api/v1/app/" + appId + "/entry/" + entryId + "/data_batch_create";
    }

    public HttpClient getSSLHttpClient() throws Exception {
        //信任所有
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    /**
     * 获取请求头信息
     *
     * @return
     */
    public Header[] getHttpHeaders() {
        List<Header> headerList = new ArrayList<>();
        headerList.add(new BasicHeader("Authorization", "Bearer " + this.apiKey));
        headerList.add(new BasicHeader("Content-Type", "application/json;charset=utf-8"));
        return headerList.toArray(new Header[headerList.size()]);
    }

    /**
     * 发送HTTP请求
     *
     * @param method - HTTP动词 { GET|POST }
     * @param url    - 请求路径
     * @param data   - 请求的数据
     * @throws Exception
     */
    public Object sendRequest(String method, String url, Map<String, Object> data) throws Exception {
        HttpClient client = this.getSSLHttpClient();
        Header[] headers = this.getHttpHeaders();
        HttpRequestBase request;
        method = method.toUpperCase();
        if ("GET".equalsIgnoreCase(method)) {
            // GET请求
            URIBuilder uriBuilder = new URIBuilder(url);
            if (data != null) {
                // 添加请求参数
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), (String) entry.getValue());
                }
            }
            request = new HttpGet(uriBuilder.build());
        } else if ("POST".equalsIgnoreCase(method)) {
            // POST请求
            request = new HttpPost(url);
            HttpEntity entity = new StringEntity(JSONObject.toJSONString(data), Charsets.UTF_8);
            ((HttpPost) request).setEntity(entity);
        } else {
            throw new RuntimeException("不支持的HTTP动词");
        }
        // 设置请求头
        request.setHeaders(headers);
        // 发送请求并获取返回结果
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        Map<String, Object> result = JSONObject.parseObject(response.getEntity().getContent(),Object.class);
        if (statusCode >= 400) {
            // 请求错误
            if ((Integer) result.get("code") == 8303 && this.retryIfRateLimited) {
                // 频率超限，5s后重试
                Thread.sleep(5000);
                return this.sendRequest(method, url, data);
            }else if ((Integer) result.get("code") == 4214) {
                // 请求超时,5s后重试
                Thread.sleep(5000);
                return this.sendRequest(method, url, data);
            } else {
                throw new RuntimeException("请求错误，Error Code: " + result.get("code") + ", Error Msg: " + result.get("msg"));
            }
        } else {
            // 处理返回结果
            return result;
        }
    }

    /**
     * 获取表单字段
     *
     * @return 表单字段
     */
    public List<Map<String, Object>> getFormWidgets() {
        List<Map<String, Object>> widgets = null;
        try {
            Map<String, Object> result = (Map<String, Object>) this.sendRequest("POST", urlGetWidgets, new HashMap<>());
            widgets = (List<Map<String, Object>>) result.get("widgets");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return widgets;
    }

    /**
     * 按条件获取表单数据
     *
     * @param limit  - 数据条数
     * @param fields - 显示的字段
     * @param filter - 过滤条件
     * @param dataId - 上次取数的最后一个数据id
     * @return - 返回的数据
     */
    public List<Map<String, Object>> getFormData(final int limit, final String[] fields, final Map<String, Object> filter, String dataId) {
        List<Map<String, Object>> data = null;
        try {
            // 构造请求数据
            Map<String, Object> requestData = new HashMap<String, Object>() {
                {
                    put("limit", limit);
                    put("fields", fields);
                    put("filter", filter);
                }
            };
            if (dataId != null) {
                requestData.put("data_id", dataId);
            }
            Map<String, Object> result = (Map<String, Object>) this.sendRequest("POST", urlGetFormData, requestData);
            data = (List<Map<String, Object>>) result.get("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 按条件获取全部表单数据
     *
     * @return 表单数据
     */
    public List<Map<String, Object>> getAllFormData(String[] fields, Map<String, Object> filter) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        String offset = null;
        do {
            List<Map<String, Object>> data = this.getFormData(100, fields, filter, offset);
            // 获取返回的数据
            if (data == null || data.isEmpty()) {
                // 已经获取全部的数据
                offset = null;
            } else {
                // 获取最后一条数据的id
                offset = (String) data.get(data.size() - 1).get("_id");
                dataList.addAll(data);
            }
        } while (offset != null);
        return dataList;
    }

    /**
     * 搜索单条数据
     *
     * @param dataId - 要查询的数据id
     * @return 表单数据
     */
    public Map<String, Object> retrieveData(String dataId) {
        Map<String, Object> data = null;
        try {
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("data_id", dataId);
            Map<String, Object> result = (Map<String, Object>) this.sendRequest("POST", urlRetrieveData, requestData);
            data = (Map<String, Object>) result.get("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 创建单条数据
     *
     * @param rawData - 创建数据内容
     * @return 更新后的数据
     */
    public Map<String, Object> createData(Map<String, Object> rawData) {
        Map<String, Object> data = null;
        try {
            Map<String, Object> requestData = new HashMap<String, Object>();
            requestData.put("data", rawData);
            Map<String, Object> result = (Map<String, Object>) this.sendRequest("POST", urlCreateData, requestData);
            data = (Map<String, Object>) result.get("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 创建多条数据(事务)
     * @param rawDatas 创建的多条数据内容,最多100条一批,要分批创建
     * @param isStartWorkflow 是否要发起流程
     * @return 成功创建的数据id
     */
    public JSONArray batchCreate(List<Map<String, Object>> rawDatas, boolean isStartWorkflow){
        JSONArray successIds = new JSONArray();
        try {
            int listSize = rawDatas.size();
            int toIndex = 100;
            List<Map<String, Object>> batch100List;
            for(int i = 0 ; i < listSize ; i += 100) {
                //作用为toIndex最后没有100条数据则剩余几条newList中就装几条
                if (i + 100 > listSize) {
                    toIndex = listSize - i;
                }
                batch100List = rawDatas.subList(i, i + toIndex);
                Map<String, Object> requestData = new HashMap<>(3);
                requestData.put("transaction_id", UUID.randomUUID().toString());
                requestData.put("data_list", batch100List);
                requestData.put("is_start_workflow", isStartWorkflow);
                Map<String, Object> result = (Map<String, Object>) this.sendRequest("POST", urlBatchCreate, requestData);
                successIds.addAll((JSONArray) result.get("success_ids"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return successIds;
    }

    /**
     * 更新单条数据
     *
     * @return 更新结果
     */
    public Map<String, Object> updateData(String dataId, Map<String, Object> update) {
        Map<String, Object> data = null;
        try {
            Map<String, Object> requestData = new HashMap<String, Object>();
            requestData.put("data_id", dataId);
            requestData.put("data", update);
            Map<String, Object> result = (Map<String, Object>) this.sendRequest("POST", urlUpdateData, requestData);
            data = (Map<String, Object>) result.get("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 删除单条数据
     *
     * @return 删除结果
     */
    public Map<String, String> deleteData(String dataId) {
        Map<String, String> result = null;
        try {
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("data_id", dataId);
            result = (Map<String, String>) this.sendRequest("POST", urlDeleteData, requestData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
