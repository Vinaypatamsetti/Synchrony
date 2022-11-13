package com.synchrony.api.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.api.client.ImgurClient;
import com.synchrony.api.controller.Controller;
import com.synchrony.api.exception.DataNotFoundException;
import com.synchrony.api.exception.NotAuthorizedException;
import com.synchrony.api.model.User;
import com.synchrony.api.model.UserInfo;
import com.synchrony.api.repository.UserRepository;

@Service
public class UserService {
	private static final Logger log =  LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImgurClient imgurClient;
	
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return Basic Userinfo after authenticating user
	 * Autenticating the user based on userName and password
	 */
	public UserInfo fetchUser(String userName, String password) {
		User user = fetchAndAuthenticate(userName, password);
		UserInfo userInfo= new UserInfo(user.getName(),user.getAge());
		return userInfo;
	}

	private User fetchAndAuthenticate(String userName, String password) {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			log.info("Data Not Found For User :" + userName);
			throw new DataNotFoundException("Data Not Found For User :" + userName);
		} else if ( !StringUtils.equalsIgnoreCase(userName, user.getUserName())
				|| !StringUtils.equalsIgnoreCase(password, user.getPassword())) {
			log.error("Username and password are not valid Please enter Correct Details");
			throw new NotAuthorizedException("Username and password are not valid Please enter Correct Details");
		}
		return user;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public String saveUser(User user) {

		return "User saved SuccessFully for " + userRepository.save(user).getName();
	}


	public void uploadImage(MultipartFile image) {
		imgurClient.uploadImageToImgur(image);
	}

}
