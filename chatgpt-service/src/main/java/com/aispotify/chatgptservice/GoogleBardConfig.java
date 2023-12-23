package com.aispotify.chatgptservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pkslow.ai.GoogleBardClient;

@Configuration
public class GoogleBardConfig {
	
	@Value("${ai.google-bard.token}")
    private String token;

	@Value("${bard.question}")
    private String question;
	
    @Bean
    public GoogleBardClient googleBardClient(@Value("${ai.google-bard.token}") String token) {
        return new GoogleBardClient("dwiAP4Sd3TicLtyGaLn6Mgrx2q5SsF9osr1vGqJ3feXyvyiyX7x-a8mPVjKXlqVHonkCtg.;sidts-CjABPVxjSufIAHZHSdqY746NHBOh6VW_q2ImtPEsuZkQF90O6jnH_I8jsjzX8HzohC4QAA");
    }

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
