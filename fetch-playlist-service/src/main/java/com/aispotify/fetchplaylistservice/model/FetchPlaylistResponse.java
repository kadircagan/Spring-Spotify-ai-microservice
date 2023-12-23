package com.aispotify.fetchplaylistservice.model;

public class FetchPlaylistResponse {

   
    private boolean  collaborative;
    private String description;
    private String href;
    private String id;
    private String name;
    private Tracks tracks;
    private String enviroment;
	public boolean isCollaborative() {
		return collaborative;
	}
	public void setCollaborative(boolean collaborative) {
		this.collaborative = collaborative;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Tracks getTracks() {
		return tracks;
	}
	public void setTracks(Tracks tracks) {
		this.tracks = tracks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEnviroment() {
		return enviroment;
	}
	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}
    
    

    // Getters and setters
}

