package com.synchrony.api.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.api.exception.ImgurException;
import com.synchrony.api.model.AccessToken;

@Component
public class ImgurClient {
	
	@Value("${imgur.url.oauth}")
	private String oauthUrl;
	
	@Value("${imgur.url.upload}")
	private String uploadUrl;
	
	
	private static final Logger log = LoggerFactory.getLogger(ImgurClient.class);
	/**
	 * 
	 * @param image
	 */
	public void uploadImageToImgur(MultipartFile image) {
		RestTemplate restTemplate = new RestTemplate();

		String ErrorDescription;

		try {
			HttpHeaders tokenHeaders = new HttpHeaders();
			tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			tokenHeaders.add("Accept", MediaType.TEXT_PLAIN_VALUE);
			tokenHeaders.add("Authorization", "Client-ID " + "f9de18306322c8c");
			MultiValueMap<String, String> entityMap = new LinkedMultiValueMap<>();
			entityMap.add("grant_type", "authorization_code");
			Map<String, String> parms = new HashMap<>();
			parms.put("client_id", "f9de18306322c8c");
			parms.put("response_type", "token");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(entityMap, tokenHeaders);
			ResponseEntity<String> tokenresponse = restTemplate.exchange(oauthUrl, HttpMethod.GET, entity, String.class,
					parms);
			String cookie = tokenresponse.getHeaders().get("set-cookie").get(0);
			AccessToken token = new AccessToken();
			token.setAccess_token(cookie.substring(cookie.indexOf('=') + 1, cookie.indexOf(';')));
			token.setDomain("api.imgur.com");
			log.info("token reponse cookie" + cookie);
			
			RestTemplate imgurRestTemplate = new RestTemplate();
			HttpHeaders imgurRestTemplateHeaders = new HttpHeaders();
			imgurRestTemplateHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
			imgurRestTemplateHeaders.add("Accept", MediaType.MULTIPART_FORM_DATA_VALUE);
			imgurRestTemplateHeaders.add("Authorization", "Bearer " + token.getAccess_token());
			imgurRestTemplateHeaders.add("Authorization", "Client-ID " + "f9de18306322c8c");
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("image", image);
			HttpEntity<MultiValueMap<String, Object>> imageEntity = new HttpEntity<>(body, imgurRestTemplateHeaders);
			ResponseEntity<String> uploadResponse = imgurRestTemplate.exchange(uploadUrl, HttpMethod.POST, imageEntity,
					String.class);
			log.info("ImageUpload Response" + uploadResponse);

		} catch (HttpClientErrorException e) {
			ErrorDescription = "Error uploading image,  status code : " + e.getRawStatusCode()
					+ " and  error message : " + e.getResponseBodyAsString();
			log.error(ErrorDescription);
			throw new ImgurException(ErrorDescription);
		} catch (HttpServerErrorException e) {
			ErrorDescription = "Error uploading image,  status code : " + e.getRawStatusCode()
					+ " and  error message : " + e.getResponseBodyAsString();
			log.error(ErrorDescription);
			throw new ImgurException(ErrorDescription);
		} 

	}
	
	
}
