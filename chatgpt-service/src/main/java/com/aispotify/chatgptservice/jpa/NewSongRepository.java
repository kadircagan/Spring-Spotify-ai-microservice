package com.aispotify.chatgptservice.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aispotify.chatgptservice.model.NewSong;


public interface NewSongRepository extends JpaRepository<NewSong, Integer> {



}