package com.aispotify.playlistconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aispotify.playlistconversionservice.model.Playlist;
import com.aispotify.playlistconversionservice.model.SpotifyLink;


//@FeignClient(name="currency-exchange", url="localhost:8000")
@FeignClient(name="fetch-playlist-service")
public interface FetchPlaylistProxy {
	
	@GetMapping("/getEnviroment")
	public String getEnviroment();
	
	@GetMapping("/getAccessToken")
    public String getAccessToken();
	
	@PostMapping("/fetchPlaylist")
	public ResponseEntity<?> fetchPlaylists(@RequestBody SpotifyLink link);

	@GetMapping("/fetchPlaylists/id/{id}")
	public EntityModel<Playlist> retrieveUser(@PathVariable int id);
}

