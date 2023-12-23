package com.aispotify.chatgptservice.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aispotify.chatgptservice.config.GoogleBardConfig;
import com.aispotify.chatgptservice.jpa.CreatedPlaylistRepository;
import com.aispotify.chatgptservice.model.CreatedPlaylist;
import com.aispotify.chatgptservice.model.SpotifyLink;
import com.aispotify.chatgptservice.service.BardAnswer;
import com.aispotify.chatgptservice.service.ChatGptService;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import com.pkslow.ai.domain.AnswerStatus;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;

@RestController
public class ChatGptController {
	
	// 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
	@Autowired
	ChatGptService service;
	@Autowired
    private  GoogleBardClient client;
    @Autowired
	private GoogleBardConfig bardConfig;
    @Autowired
    private CreatedPlaylistRepository createdPlaylistRepository;    

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
    
	@PostMapping("/createNewPlaylist")
	public int createNewPlaylist(@RequestBody SpotifyLink link ){
			String id = service.extractPlaylistID(link.getLink());
			String oldPlaylist = "error";
			String last ="error";
			int savedId = -1;
			try {
				oldPlaylist  = service.retrieveMostRecentPlaylist(id);
				last = service.asks(oldPlaylist);
				savedId = service.saveNewPlaylistToDb(last);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
		return savedId;
	}
	
	@GetMapping("/fetchCreatedPlaylists/id/{id}")
	public EntityModel<CreatedPlaylist> retrieveUser(@PathVariable int id) throws Exception{
		Optional<CreatedPlaylist> playlist = createdPlaylistRepository.findById(id);
		
		if(playlist.isEmpty())
			throw new Exception("id:"+id);
		
		EntityModel<CreatedPlaylist> entityModel = EntityModel.of(playlist.get());
		
		
		return entityModel;
		
	}
	
	
}
