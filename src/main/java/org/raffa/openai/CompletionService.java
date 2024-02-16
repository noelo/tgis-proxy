package org.raffa.openai;


import org.raffa.ai.openai.api.CompletionsApi;
import org.raffa.ai.openai.api.EmbeddingsApi;
import org.raffa.ai.openai.model.CreateCompletionRequest;
import org.raffa.ai.openai.model.CreateCompletionResponse;
import org.raffa.ai.openai.model.CreateEmbeddingRequest;
import org.raffa.ai.openai.model.CreateEmbeddingResponse;

import io.smallrye.mutiny.Uni;

public class CompletionService implements CompletionsApi {

  @Override
  
  public Uni<CreateCompletionResponse> createCompletion(CreateCompletionRequest createCompletionRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createCompletion'");
  }
  
}



