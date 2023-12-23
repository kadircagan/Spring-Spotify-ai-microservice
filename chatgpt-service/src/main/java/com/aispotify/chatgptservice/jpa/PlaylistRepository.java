package com.aispotify.chatgptservice.jpa;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aispotify.chatgptservice.model.Playlist;


public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

	
    @Query("SELECT p FROM playlist_details p WHERE p.playlistID = :playlistId ORDER BY p.localDate DESC LIMIT 1")
    Playlist findMostRecentByPlaylistId(@Param("playlistId") String playlistId);
    

}
