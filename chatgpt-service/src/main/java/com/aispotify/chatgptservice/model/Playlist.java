package com.aispotify.chatgptservice.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "playlist_details")
public class Playlist {

		@Id
	    @GeneratedValue
	    private Integer id;
	    
	    private String playlistID;
	    
	    @OneToMany(mappedBy = "pl")
	    private List<Song> songs = new ArrayList<Song>(); // Initialize the list

	    private String playlistName;
	    
	    private LocalDateTime localDate;

	    public Playlist() {
	        // Default constructor required by JPA
	    }

	    public Playlist(String playlistID, String playlistName) {
	        this.playlistID = playlistID;
	        this.setPlaylistName(playlistName);
	    }
	
	public String getPlaylistID() {
		return playlistID;
	}


	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<Song> getSongs() {
		return songs;
	}


	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public LocalDateTime getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDateTime localDate) {
		this.localDate = localDate;
	}
}
