package org.raffa.openai;

import org.raffa.ai.openai.api.EmbeddingsApi;
import org.raffa.ai.openai.model.CreateEmbeddingRequest;
import org.raffa.ai.openai.model.CreateEmbeddingResponse;

import io.smallrye.mutiny.Uni;

public class EmbeddingService implements EmbeddingsApi{

  @Override
  public Uni<CreateEmbeddingResponse> createEmbedding(CreateEmbeddingRequest createEmbeddingRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createEmbedding'");
  }
  
}
