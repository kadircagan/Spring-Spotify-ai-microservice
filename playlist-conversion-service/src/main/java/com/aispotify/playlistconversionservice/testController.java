package com.aispotify.playlistconversionservice;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class testController {
	@Autowired
	private FetchPlaylistProxy proxy;
	
	@GetMapping("/feign/getEnviroment")
	public String getEnviromentNew() {
				
		String port = proxy.getEnviroment();
		
		return "hailProxy: " + port;
	}

	@GetMapping("/feign/getAccessToken")
	public String getAccessToken() {
				
		String port = proxy.getAccessToken();
		
		return "at: " + port;
	}

}
