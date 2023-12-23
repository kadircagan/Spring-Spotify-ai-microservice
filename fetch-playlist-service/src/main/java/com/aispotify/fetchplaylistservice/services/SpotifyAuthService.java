package com.aispotify.fetchplaylistservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.aispotify.fetchplaylistservice.config.SpotifyApiConfig;
import com.aispotify.fetchplaylistservice.model.AccessTokenResponse;

@Service
public class SpotifyAuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpotifyApiConfig configuration;

    public String requestAccessToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://accounts.spotify.com/api/token")
                .queryParam("grant_type", "client_credentials")
                .queryParam("client_id", configuration.getClientId())
                .queryParam("client_secret", configuration.getClientSecret());


        ResponseEntity<AccessTokenResponse> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                null,
                AccessTokenResponse.class
        );


        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            AccessTokenResponse accessTokenResponse = responseEntity.getBody();
            return accessTokenResponse.getAccessToken();
        } else {

            return null;
        }
    }
}
