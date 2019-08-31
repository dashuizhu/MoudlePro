package com.test.basemoudle.bean;

public  class AuthBean {
    /**
     * expiresIn : 35998
     * accessToken : 8a9b94cd-57d0-4f2e-8113-9d5967b54aba
     * refreshToken : 269c47d5-1c2c-4dd8-914b-b32246d10723
     */

    private int expiresIn;
    private String accessToken;
    private String refreshToken;

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}