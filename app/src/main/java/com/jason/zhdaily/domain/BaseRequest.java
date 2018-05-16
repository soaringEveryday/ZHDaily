package com.jason.zhdaily.domain;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class BaseRequest implements Serializable {
    /**
     * 站长编号 (必传)
     */
    @Expose
    private String adminId;

    /**
     * 请求用户编号
     */
    @Expose
    private String userId;

    /**
     * P的用户名
     */
    private String username;

    public BaseRequest() {
    }

    public BaseRequest(String adminId, String userId) {
        this.adminId = adminId;
        this.userId = userId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

