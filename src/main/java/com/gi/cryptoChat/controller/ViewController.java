package com.gi.cryptoChat.controller;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gi.cryptoChat.messaging.ReceiveMessageListener;
import com.gi.cryptoChat.model.User;
import com.gi.cryptoChat.repo.UserRepository;

@Controller
public class ViewController {

	@Autowired
	UserRepository userRepo;

//	@Autowired
//	OnlineUserRepository onlineUserRepo;

	public static Set<String> onlineUsers = new HashSet<String>();

	@Autowired
	ReceiveMessageListener receiver;

	Logger logger = LoggerFactory.getLogger(ViewController.class);

	@GetMapping("/")
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index.html");
		return mav;
	}

	@GetMapping("/chat")
	public ModelAndView privateChatView(HttpServletRequest request) {
		String username = request.getUserPrincipal().getName();
		request.getSession().setAttribute("username", username);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("chat.html");
		mav.addObject("username", username);

		List<User> users = userRepo.findAll();

		mav.addObject("users", users.stream().map(User::getUsername)
				.filter(userUsername -> !userUsername.equals(username)).collect(Collectors.toList()));
		onlineUsers.add(username);

		mav.addObject("online_users", onlineUsers);

		return mav;
	}

	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registration.html");
		return mav;
	}

	@GetMapping("/disconnect")
	public ModelAndView disconnect(HttpSession session) {
		receiver.disconnectListener(session.getId());
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("index.html");
		return mav;
	}

}
