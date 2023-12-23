package com.aispotify.chatgptservice.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "created_playlist_details")
public class CreatedPlaylist {

		@Id
	    @GeneratedValue
	    private Integer id;
	    
	    @OneToMany(mappedBy = "pl")
	    private List<NewSong> songs = new ArrayList<NewSong>(); // Initialize the list

	    
	    private LocalDateTime localDate;

	    public CreatedPlaylist() {
	        // Default constructor required by JPA
	    }

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<NewSong> getSongs() {
		return songs;
	}


	public void setSongs(List<NewSong> songs) {
		this.songs = songs;
	}

	

	public LocalDateTime getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDateTime localDate) {
		this.localDate = localDate;
	}
}
