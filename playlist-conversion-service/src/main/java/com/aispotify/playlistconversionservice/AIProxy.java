package com.aispotify.playlistconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aispotify.playlistconversionservice.model.CreatedPlaylist;
import com.aispotify.playlistconversionservice.model.SpotifyLink;


@FeignClient(name="chatgpt-service")
public interface AIProxy {
	

	@PostMapping("/createNewPlaylist")
	public int createNewPlaylist(@RequestBody SpotifyLink link);
	
	@GetMapping("/fetchCreatedPlaylists/id/{id}")
	public EntityModel<CreatedPlaylist> retrieveUser(@PathVariable int id);

}

