package com.pinyougou.entity;

import java.io.Serializable;

/**
 * 添加返回结果实体
 */
public class Result implements Serializable {
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 辅助信息
     */
    private String message;

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
