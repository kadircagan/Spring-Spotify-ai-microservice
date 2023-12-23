package com.aispotify.fetchplaylistservice.services;


import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aispotify.fetchplaylistservice.jpa.ArtistRepository;
import com.aispotify.fetchplaylistservice.jpa.PlaylistRepository;
import com.aispotify.fetchplaylistservice.jpa.SongRepository;
import com.aispotify.fetchplaylistservice.model.FetchPlaylistResponse;
import com.aispotify.fetchplaylistservice.model.Item;
import com.aispotify.fetchplaylistservice.model.Playlist;
import com.aispotify.fetchplaylistservice.model.Song;

@Service
public class RetrivePlaylistService {
	@Autowired
	private Environment env;
	@Autowired
    private RestTemplate restTemplate;
	
	
	public ResponseEntity<?> getPlaylist(String playlistID, String accessToken) {
		playlistID = extractPlaylistID(playlistID);
        try {
            FetchPlaylistResponse response = retrievePlaylist(playlistID, accessToken);
            
            if (response != null) {
            	response.setEnviroment(env.getProperty("local.server.port"));
                return ResponseEntity.ok(response);
            } else {
                String errorMessage = "Error: Response is null"; // Customize the error message
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(errorMessage);
            }
        } catch (Exception e) {
            String errorMessage = "Error has happened: " + e.getMessage(); // Customize the error message
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    
	private String extractPlaylistID(String playlistID) {
	    String regex = "playlist/([a-zA-Z0-9]+)";
	    
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(playlistID);
	    
	    if (matcher.find()) {
	        return matcher.group(1); 
	    } else {
	        return null;
	    }
		
	}


	public FetchPlaylistResponse retrievePlaylist(String playlistID, String accessToken) {
	    String apiUrl = "https://api.spotify.com/v1/playlists/" + playlistID;

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + accessToken);
	    HttpEntity<String> entity = new HttpEntity<>(headers);
	 
	    
	    ResponseEntity<FetchPlaylistResponse> responseEntity = restTemplate.exchange(
	        apiUrl,
	        HttpMethod.GET,
	        entity,
	        FetchPlaylistResponse.class
	    );

	    if (responseEntity.getStatusCode().is2xxSuccessful()) {
	        return responseEntity.getBody();
	    } else {
	        // Handle error here or log the response body
	        System.out.println("Error Response Body: " + responseEntity.getBody());
	    }

	    return null;
	}


	public void  playlistSaver(SongRepository songRepository, PlaylistRepository playlistRepository, ArtistRepository artistRepository, FetchPlaylistResponse body) {
		Playlist playlist = new Playlist(body.getId(),body.getName());
		for(int i = 0;i<body.getTracks().getItems().size();i++) {
			Item item = body.getTracks().getItems().get(i);
			Song song = new Song(item.getTrack().getName(),item.getTrack().getArtists(),item.getTrack().getId());
			song.setPl(playlist);
			//songRepository.save(song);
			artistRepository.saveAll(item.getTrack().getArtists());
			playlist.getSongs().add(song);
		}
		playlist.setLocalDate(LocalDateTime.now());
		playlistRepository.save(playlist);
		songRepository.saveAll(playlist.getSongs());
	}
    
}