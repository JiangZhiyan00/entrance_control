package com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 违法类型枚举
 */
public enum IllegalTypeEnum {

    TYPE_1("1","0-6点危化品车上高速"),
    TYPE_2("2","2-5点营运大客车上高速"),
    TYPE_3("3","超员"),
    TYPE_4("4","超长、超宽、超高"),
    TYPE_5("5","超重"),
    TYPE_6("6","出入口篷布遮盖车辆"),
    TYPE_7("7","非机动车、行人等"),
    TYPE_8("8","钢材捆扎不牢"),
    TYPE_9("9","其他违规车辆"),
    TYPE_10("10","违法失信车辆"),
    TYPE_11("11","易抛洒滴漏、捆扎不牢");

    private String code;

    private String description;

    IllegalTypeEnum() {
    }

    IllegalTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getCodeByDescription(String description) {
        String code = "err";
        if (StringUtils.isNotBlank(description)){
            for (IllegalTypeEnum value : IllegalTypeEnum.values()) {
                if (value.getDescription().equals(description)){
                    code = value.getCode();
                }
            }
        }
        return code;
    }
}
