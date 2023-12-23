package com.aispotify.fetchplaylistservice.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aispotify.fetchplaylistservice.model.Song;


public interface SongRepository extends JpaRepository<Song, Integer> {

}
