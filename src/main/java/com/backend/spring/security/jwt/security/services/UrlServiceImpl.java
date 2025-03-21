package com.backend.spring.security.jwt.security.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.spring.security.jwt.models.Urls;
import com.backend.spring.security.jwt.models.User;
import com.backend.spring.security.jwt.repository.UrlRepository;
import com.backend.spring.security.jwt.repository.UserRepository;



@Service
public class UrlServiceImpl implements UrlService {
	
	  @Autowired
	  UserRepository userRepository;
	
	private final UrlRepository urlRepository;

  public UrlServiceImpl(UrlRepository urlRepository) {
      this.urlRepository = urlRepository;
  }

  @Override
  @Transactional
  public Urls createUrl(String longUrl, Long userId) {
	  if (longUrl == null || longUrl.isEmpty()) {
	        throw new IllegalArgumentException("Long URL cannot be null or empty");
	    }
      
      Optional<User> userOptional = userRepository.findById(userId);
      if (userOptional.isPresent()) {
          User user = userOptional.get();
          String shortCode = UUID.randomUUID().toString().substring(0, 6);
          Urls url = new Urls();
          url.setShortCode(shortCode);
          url.setLongUrl(longUrl); // Make sure this line is included
          url.setUser_id(user);
          url.setCreatedAt(LocalDateTime.now());
          url.setClicks(0);

          
          return urlRepository.save(url);
      }

      throw new IllegalArgumentException("User not found");
  }

	@Override
	@Transactional
	public Optional<Urls> getUrlByShortCode(String shortCode) {
		
		return urlRepository.findByShortCode(shortCode).map(url -> {
          url.setClicks(url.getClicks() + 1);
          urlRepository.save(url);
          return url;
      });
	}

}