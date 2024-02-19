package org.raffa.openai;

import java.util.stream.Stream;

import org.raffa.ai.openai.api.ChatApi;
import org.raffa.ai.openai.model.CreateChatCompletionRequest;
import org.raffa.ai.openai.model.CreateChatCompletionResponse;
import org.raffa.ai.openai.model.CreateChatCompletionResponseChoicesInner;
import org.raffa.ai.openai.model.CreateCompletionResponse;
import org.raffa.ai.openai.model.CreateCompletionResponseChoicesInner;

import fmaas.Generation.BatchedGenerationRequest;
import fmaas.Generation.BatchedGenerationResponse;
import fmaas.Generation.GenerationRequest;
import fmaas.Generation.GenerationResponse;
import fmaas.MutinyGenerationServiceGrpc;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

public class ChatService implements ChatApi{

  @GrpcClient("tgis")
  MutinyGenerationServiceGrpc.MutinyGenerationServiceStub tgis;

  @Override
  public Uni<CreateChatCompletionResponse> createChatCompletion(CreateChatCompletionRequest createChatCompletionRequest) {
    throw new UnsupportedOperationException("Unimplemented method 'createChatCompletion'");
  }


}
