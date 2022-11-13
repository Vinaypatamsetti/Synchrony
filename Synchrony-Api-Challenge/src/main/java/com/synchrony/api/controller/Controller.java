package com.synchrony.api.controller;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.api.client.ImgurClient;
import com.synchrony.api.model.User;
import com.synchrony.api.model.UserInfo;
import com.synchrony.api.service.UserService;

@RestController
public class Controller {
	
	private static final Logger log =  LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private UserService userService;
	
	
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@GetMapping("/getUserBasedOnName")
	public UserInfo getUser(@RequestParam(name="userName",required = true) String userName,@RequestParam(name="password",required = true) String password) {
		return userService.fetchUser(userName,password);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/saveUser")
	public String saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
    
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param image
	 */
	@PostMapping("/uploadImage")
	public void uploadImage(@RequestParam(name="userName",required = true) String userName,@RequestParam(name="password",required = true) String password,@RequestBody MultipartFile image) {
		userService.fetchUser(userName, password);
		userService.uploadImage(image);
	}
}
