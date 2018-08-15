package com.pinyougou.entity;

import java.io.Serializable;

/**
 * 添加返回结果实体
 */
public class Result implements Serializable {
    /**
     * 是否成功
     */
    private Boolean flag;
    /**
     * 辅助信息
     */
    private String message;

    public Result(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
