package com.aispotify.playlistconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PlaylistConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistConversionServiceApplication.class, args);
	}

}
