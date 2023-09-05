package com.example.simple;


import com.example.models.ChatCompletionsRequest;
import com.example.models.ChatResponses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.prompt.Prompt;
import org.springframework.ai.prompt.messages.ChatMessage;
import org.springframework.ai.prompt.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController 
{

	@Autowired
	private AiClient aiClient;
	
    @PostMapping("/completions")
    public ChatResponses chatCompletion(@RequestBody ChatCompletionsRequest request) 
    {
    
    	final List<Message> msgs = new ArrayList<>();
    	final List<ChatResponses.Choice> choices = new ArrayList<>();
    	
    	request.getMessages()
    	   .stream()
    	   .forEach(chat -> msgs.add(new ChatMessage(chat.getRole().toUpperCase(), chat.getContent())));    	
    	
    	final ChatResponses resp = new ChatResponses();
		
    	aiClient.generate(new Prompt(msgs))
    	  .getGenerations()
    	  .stream()
    	  .forEach(gen -> {
      		var message = new ChatResponses.Message();
      		message.setContent(gen.getText());
      		
      		var choice = new ChatResponses.Choice();
      		choice.setMessage(message);

      		choices.add(choice);    		  
    	  });

    	resp.setChoices(choices);
    	
    	return resp;
    	
    }
}
