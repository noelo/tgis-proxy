package org.raffa.openai;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


@Path("/chat/completions")
public class ChatResource {
    
    @POST
    public ChatCompletionResult createChatCompletion(ChatCompletionRequest chatcomp) {
        // do something

        return null;
    }
    
}

