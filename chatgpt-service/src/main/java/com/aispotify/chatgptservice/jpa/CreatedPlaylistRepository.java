package com.aispotify.chatgptservice.jpa;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aispotify.chatgptservice.model.CreatedPlaylist;


public interface CreatedPlaylistRepository extends JpaRepository<CreatedPlaylist, Integer> {

	
    

}
