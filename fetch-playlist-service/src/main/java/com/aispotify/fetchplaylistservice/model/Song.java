package com.aispotify.fetchplaylistservice.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Song {

	public Song() {
		
	}
	@Id
	@GeneratedValue
    //@JsonProperty("idSong")
	Integer idSong;
    
    //@JsonProperty("name")
	String name;
    String spotifyId;
    //@JsonProperty("artist")
	@ElementCollection
	List<Artist> artist = new ArrayList<Artist>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Playlist pl;
	
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

	public List<Artist> getArtist() {
		return artist;
	}

	public void setArtist(List<Artist> artist) {
		this.artist = artist;
	}

	public Playlist getPl() {
		return pl;
	}

	public void setPl(Playlist pl) {
		this.pl = pl;
	}

	public Song( String name, List<Artist> list,String id) {
		super();
		this.name = name;
		this.artist = list;
		this.spotifyId = id;
	}

	public String getSpotifyId() {
		return spotifyId;
	}

	public void setSpotifyId(String spotifyId) {
		this.spotifyId = spotifyId;
	}
}
