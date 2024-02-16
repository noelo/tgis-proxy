package org.raffa.openai;


import java.util.stream.Stream;

import org.raffa.ai.openai.api.CompletionsApi;
import org.raffa.ai.openai.model.CreateCompletionRequest;
import org.raffa.ai.openai.model.CreateCompletionResponse;
import org.raffa.ai.openai.model.CreateCompletionResponseChoicesInner;

import fmaas.MutinyGenerationServiceGrpc;
import fmaas.Generation.BatchedGenerationRequest;
import fmaas.Generation.BatchedGenerationResponse;
import fmaas.Generation.GenerationRequest;
import fmaas.Generation.GenerationResponse;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

public class CompletionService implements CompletionsApi {

  @GrpcClient("tgis")
  MutinyGenerationServiceGrpc.MutinyGenerationServiceStub tgis;
  
  @Override
  public Uni<CreateCompletionResponse> createCompletion(CreateCompletionRequest createCompletionRequest) {
    GenerationRequest generationRequest=GenerationRequest.newBuilder().setText(createCompletionRequest.getPrompt().toString()).build();
    BatchedGenerationRequest batchedGenerationRequest=BatchedGenerationRequest.newBuilder().setModelId(createCompletionRequest.getModel().toString()).addRequests(generationRequest).build();
    Uni<BatchedGenerationResponse> batchedRequestResponse=tgis.generate(batchedGenerationRequest);
    return batchedRequestResponse.map(brr->fromBatchedGenerationResponseToCompletionResponse(brr));
  }

  private CreateCompletionResponse fromBatchedGenerationResponseToCompletionResponse(BatchedGenerationResponse batchedGenerationResponse){
    CreateCompletionResponse createCompletionResponse=new CreateCompletionResponse();
    Stream<CreateCompletionResponseChoicesInner> completionResponseChoiceStream=batchedGenerationResponse.getResponsesList().stream().map(generationResponse -> fromGenerationResponseToCompletionResponseChoice(generationResponse));
    createCompletionResponse.setChoices(completionResponseChoiceStream.toList());
    return createCompletionResponse;
  }

  private CreateCompletionResponseChoicesInner fromGenerationResponseToCompletionResponseChoice(GenerationResponse generationResponse){
    CreateCompletionResponseChoicesInner createCompletionResponseChoicesInner=new CreateCompletionResponseChoicesInner();
    createCompletionResponseChoicesInner.setText(generationResponse.getText());
    return createCompletionResponseChoicesInner;
  }
  
}



