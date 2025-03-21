package com.backend.spring.security.jwt.security.services;

import java.util.Optional;

import com.backend.spring.security.jwt.models.Urls;



public interface UrlService {

	
    Urls createUrl(String longUrl, Long userId);
	
	Optional<Urls> getUrlByShortCode(String shortCode);
}
