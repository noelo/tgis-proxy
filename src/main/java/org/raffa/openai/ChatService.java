package org.raffa.openai;

import org.raffa.ai.openai.api.ChatApi;
import org.raffa.ai.openai.model.CreateChatCompletionRequest;
import org.raffa.ai.openai.model.CreateChatCompletionResponse;

import io.smallrye.mutiny.Uni;

public class ChatService implements ChatApi{

  @Override
  public Uni<CreateChatCompletionResponse> createChatCompletion(
      CreateChatCompletionRequest createChatCompletionRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createChatCompletion'");
  }
  
}
