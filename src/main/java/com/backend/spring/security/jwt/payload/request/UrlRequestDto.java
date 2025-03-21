package com.backend.spring.security.jwt.payload.request;

public class UrlRequestDto {
	
    private String longUrl;
    private Long userId;

    // Getters and Setters

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
