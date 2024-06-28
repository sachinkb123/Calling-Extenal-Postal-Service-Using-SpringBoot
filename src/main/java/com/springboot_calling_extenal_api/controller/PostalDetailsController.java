package com.springboot_calling_extenal_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot_calling_extenal_api.output.PostOfficeResponseBean;
import com.springboot_calling_extenal_api.service.IPostService;

@RestController
@RequestMapping("/postal")
public class PostalDetailsController {
	
	@Autowired
	IPostService iPostService;
	
	@RequestMapping(value = "/byCity", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PostOfficeResponseBean getAndSavePostalByCity(@RequestParam String city) {
		PostOfficeResponseBean response = iPostService.fetchAndSavePostOfficeDetailsByCity(city);
		return response;
	}
	
	@RequestMapping(value = "/City", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PostOfficeResponseBean getPostalByCity(@RequestParam String city) {
		PostOfficeResponseBean response = iPostService.fetchPostOfficeDetailsByCity(city);
		return response;
	}
}
