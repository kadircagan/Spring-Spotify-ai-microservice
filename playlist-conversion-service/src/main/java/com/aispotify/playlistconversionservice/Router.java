package com.aispotify.playlistconversionservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aispotify.playlistconversionservice.model.Playlist;
import com.aispotify.playlistconversionservice.model.CreatedPlaylist;
import com.aispotify.playlistconversionservice.model.SpotifyLink;

@RestController
public class Router{
	@Autowired
	private FetchPlaylistProxy fetchProxy;
	@Autowired
	private AIProxy aiProxy;
	
	//@Autowired
	//private Controller service;


	@PostMapping("/feigh/fetchAndCreatePlaylist")
	public EntityModel<CreatedPlaylist> fetchAndCreatePlaylists(@RequestBody SpotifyLink link) {
        ResponseEntity<?> responseEntity = fetchProxy.fetchPlaylists(link);
		
		
        int newPlaylistID = aiProxy.createNewPlaylist(link);
        EntityModel<CreatedPlaylist> resp = aiProxy.retrieveUser(newPlaylistID);//int degeri ile degisecek
        
        return resp;
	}
	
	
	
	
	
	
	
}