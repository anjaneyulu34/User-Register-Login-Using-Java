
package com.app.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.register.model.User;
import com.app.register.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
	String tempEmail=user.getEmailId();
	if(tempEmail!=null && !"".equals(tempEmail)) {
      User userobj=  service.fetchUserByEmailId(tempEmail);
	  if(userobj!=null) {
		  throw new Exception("user with "+tempEmail+"is already exist");
	  }
	}
	 
	 User userobj=null;
	 userobj= service.saveUser(user);
	 return userobj;
    }
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId=user.getEmailId();
		String tempPassword=user.getPassword();
		User userObj=null;
		if(tempEmailId !=null && tempPassword !=null) {
		userObj=service.fetchUserByEmailIdAndPassword(tempEmailId, tempPassword);
	
		 }
		if(userObj==null) {
			throw new Exception("bad credential");
		}
		return userObj;
	}
}
