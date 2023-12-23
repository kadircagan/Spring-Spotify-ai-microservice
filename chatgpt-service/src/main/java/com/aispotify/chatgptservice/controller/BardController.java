package com.aispotify.chatgptservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aispotify.chatgptservice.config.GoogleBardConfig;
import com.aispotify.chatgptservice.service.BardAnswer;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import com.pkslow.ai.domain.AnswerStatus;

@RestController
@RequestMapping("/google-bard")
public class BardController {
	
	@Autowired
    private  GoogleBardClient client;
    @Autowired
	private GoogleBardConfig bardConfig;
  

    @GetMapping("/ask")
    public BardAnswer ask(@RequestParam("q") String question) {
        Answer answer = client.ask(question);
        if (answer.getStatus() == AnswerStatus.OK) {
            return new BardAnswer(answer.getChosenAnswer());
        }

        if (answer.getStatus() == AnswerStatus.NO_ANSWER) {
            return new BardAnswer("No Answer");
        }

        throw new RuntimeException("Can't access to Google Bard");

    }
    @GetMapping("/asks")
    public String asks() {
    	GoogleBardClient client = new GoogleBardClient(bardConfig.getToken());
    	Answer answer = client.ask("Hello,how are you ?");
    	return answer.getChosenAnswer();
    }
}
