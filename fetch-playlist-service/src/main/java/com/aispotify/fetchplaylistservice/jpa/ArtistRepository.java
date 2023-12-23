package com.aispotify.fetchplaylistservice.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aispotify.fetchplaylistservice.model.Artist;


public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}
