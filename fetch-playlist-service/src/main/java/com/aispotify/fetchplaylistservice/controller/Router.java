package com.aispotify.fetchplaylistservice.controller;


import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aispotify.fetchplaylistservice.jpa.ArtistRepository;
import com.aispotify.fetchplaylistservice.jpa.PlaylistRepository;
import com.aispotify.fetchplaylistservice.jpa.SongRepository;
import com.aispotify.fetchplaylistservice.model.FetchPlaylistResponse;
import com.aispotify.fetchplaylistservice.model.Playlist;
import com.aispotify.fetchplaylistservice.model.SpotifyLink;
import com.aispotify.fetchplaylistservice.services.RetrivePlaylistService;
import com.aispotify.fetchplaylistservice.services.SpotifyAuthService;

@RestController
public class Router {
	@Autowired
    private SpotifyAuthService spotifyAuthService;
	@Autowired
	private RetrivePlaylistService RetrivePlaylistService;
	
	private String accessToken;
	
	
	@Autowired
    private PlaylistRepository playlistRepository; 

	@Autowired
    private SongRepository songRepository;
	
	@Autowired
    private ArtistRepository artistRepository; 
	
	@PostMapping("/fetchPlaylist")
	public ResponseEntity<?> fetchPlaylists(@RequestBody SpotifyLink link) {
		String id = link.getLink();
		ResponseEntity<?> response= null;
		if(accessToken==null) {
			accessToken = spotifyAuthService.requestAccessToken();
		    response = RetrivePlaylistService.getPlaylist(id,accessToken);
		}else {
			response = RetrivePlaylistService.getPlaylist(id,accessToken);
			System.out.println("selam"+ response.status(0));
			if(response ==null) {
				accessToken = spotifyAuthService.requestAccessToken();
				response = RetrivePlaylistService.getPlaylist(id,accessToken);
			}
		}
		
		if(!response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(response.getBody());
		}
		
		RetrivePlaylistService.playlistSaver(songRepository,playlistRepository,artistRepository,(FetchPlaylistResponse) response.getBody());
		
		
			
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/fetchPlaylists/id/{id}")
	public EntityModel<Playlist> retrieveUser(@PathVariable int id) throws Exception {
		Optional<Playlist> playlist = playlistRepository.findById(id);
		
		if(playlist.isEmpty())
			throw new Exception("id:"+id);
		
		EntityModel<Playlist> entityModel = EntityModel.of(playlist.get());
		
		
		return entityModel;
	}
	@GetMapping("/fetchPlaylists/{playlistId}")
	public EntityModel<Playlist> retrieveMostRecentPlaylist(@PathVariable String playlistId) throws Exception {
	    Playlist playlist = playlistRepository.findMostRecentByPlaylistId(playlistId);
	    
	    if (playlist == null) {
	        throw new Exception("Playlist not found for playlistId: " + playlistId);
	    }
	    
	    EntityModel<Playlist> entityModel = EntityModel.of(playlist);
	    
	    return entityModel;
	}

    @GetMapping("/getAccessToken")
    public String getAccessToken() {
        accessToken = spotifyAuthService.requestAccessToken();
        return "Access Token: " + accessToken;
    }
    
    
    
	@Autowired
	private Environment environment;
    @GetMapping("/getEnviroment")
    public String getEnviroment() {
		String port = environment.getProperty("local.server.port");
        return "env: " + port;
    }
    
    
    
}
