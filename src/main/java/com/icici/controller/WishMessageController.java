package com.icici.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishMessageController")
public class WishMessageController {
	
	@GetMapping("/wishMessage")
	  public Map<String, String> getWishMessage() {
	        Map<String, String> response = new HashMap();
	        response.put("message", "Welcome to ICICI Digital Banking! Have a great day.");
	        return response;
	    }

}
