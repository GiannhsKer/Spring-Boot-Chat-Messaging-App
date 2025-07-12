package com.gi.cryptoChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gi.cryptoChat.model.User;
import com.gi.cryptoChat.repo.UserRepository;

@RestController
public class MainController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute User user) {
		user.setPassword(passEncoder.encode(user.getPassword()));
		userRepo.save(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index.html");
		return mav;
	}
	
	
}
