package com.aispotify.chatgptservice.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.aispotify.chatgptservice.model.CreatedPlaylist;
import com.aispotify.chatgptservice.model.NewSong;
import com.aispotify.chatgptservice.model.Playlist;
import com.aispotify.chatgptservice.model.Song;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import com.aispotify.chatgptservice.config.GoogleBardConfig;
import com.aispotify.chatgptservice.jpa.CreatedPlaylistRepository;
import com.aispotify.chatgptservice.jpa.NewSongRepository;
import com.aispotify.chatgptservice.jpa.PlaylistRepository;

@Service
public class ChatGptService{
	
	
	@Autowired
    private PlaylistRepository playlistRepository; 
	@Autowired
    private CreatedPlaylistRepository createdPlaylistRepository;
	@Autowired NewSongRepository newSongRepository;
	
	@Autowired
	private GoogleBardConfig bardConfig;
	
	public String retrieveMostRecentPlaylist(String playlistId) throws Exception {
	    Playlist playlist = playlistRepository.findMostRecentByPlaylistId(playlistId);
	    
	    if (playlist == null) {
	        throw new Exception("Playlist not found for playlistId: " + playlistId);
	    }
	    
	    EntityModel<Playlist> entityModel = EntityModel.of(playlist);
	    
	    String result="";
	    for(Song a : entityModel.getContent().getSongs()) {
	    	String name =a.getName();
	    	String artist = a.getArtist().get(0).getName();
	    	result = result + name +"-"+artist+"\n";
	    	
	    }
	    
	    
	    
	    return result;
	}
	
	public String asks(String songs) {
    	GoogleBardClient client = new GoogleBardClient(bardConfig.getToken());
    	String finalQuestion = bardConfig.getQuestion() +"\n" + songs;
    	Answer answer = client.ask(finalQuestion);
    	return answer.getChosenAnswer();
    }
	
	
	
    
	 public static Map<String, String> parseInput(String input) {
	        Map<String, String> songsAndArtists = new HashMap<>();

	        String[] pairs = input.split("-");
	        for (int i = 1; i < pairs.length - 1; i += 2) {
	            String song = pairs[i].replace("*", "");
	            String artist = pairs[i + 1].substring(pairs[i + 1].lastIndexOf('-') + 1).trim();
	            songsAndArtists.put(song, artist);
	        }
	        
	        return songsAndArtists;
	    }

	public int saveNewPlaylistToDb(String last) {

		Map<String, String> songsAndArtists = parseInput(last);

		
		CreatedPlaylist playlist = new CreatedPlaylist();
        for (Map.Entry<String, String> entry : songsAndArtists.entrySet()) {
        	NewSong song = new NewSong();
        	song.setArtist(entry.getValue());
        	song.setName(entry.getKey());
        	song.setPl(playlist);
        	playlist.getSongs().add(song);
        	
            System.out.println("Song: " + entry.getKey() + ", Artist: " + entry.getValue());
        }
        playlist.setLocalDate(LocalDateTime.now());
        CreatedPlaylist savedPlaylist = createdPlaylistRepository.save(playlist);
        newSongRepository.saveAll(playlist.getSongs());
        
       return savedPlaylist.getId();
	}
	
	public String extractPlaylistID(String playlistID) {
	    String regex = "playlist/([a-zA-Z0-9]+)";
	    
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(playlistID);
	    
	    if (matcher.find()) {
	        return matcher.group(1); 
	    } else {
	        return null;
	    }
		
	}
	
    
	
	
}