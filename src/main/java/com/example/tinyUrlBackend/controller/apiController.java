package com.example.tinyUrlBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.largerUrlModel;
import com.example.tinyUrlBackend.Service.shortenUrlService;
import com.example.tinyUrlBackend.dao.UrlDAO;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class apiController {
	@Autowired
	private shortenUrlService shortenurlservice;
	@PostMapping("/api/generateShortenUrl")
	public UrlDAO newShortenUrl(@RequestBody largerUrlModel largerUrlmodel) throws JsonProcessingException{
		 return shortenurlservice.generateUrl(largerUrlmodel);
	}
	
	@GetMapping("/api/{url}")
	public largerUrlModel getActualUrl(@PathVariable("url") String shortenUrl) throws JsonProcessingException {
		return shortenurlservice.getActualUrl(shortenUrl);
	}
}
