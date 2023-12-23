package com.aispotify.chatgptservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class NewSong {

	public NewSong() {
		
	}
	@Id
	@GeneratedValue
    //@JsonProperty("idSong")
	Integer idSong;
    
    //@JsonProperty("name")
	String name;
    
    private String artist;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private CreatedPlaylist pl;
	
	public Integer getIdSong() {
		return idSong;
	}

	public void setIdSong(Integer idSong) {
		this.idSong = idSong;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public CreatedPlaylist getPl() {
		return pl;
	}

	public void setPl(CreatedPlaylist pl) {
		this.pl = pl;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	
}
