package org.raffa.openai;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

import fmaas.MutinyGenerationServiceGrpc;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import io.smallrye.mutiny.Uni;

@Path("/completions")
public class CompletionsResource {

    @GrpcClient("tgis")
    MutinyGenerationServiceGrpc.MutinyGenerationServiceStub tgis;
    
    @POST
    public Uni<CompletionResult> createCompletion(CompletionRequest comp) {
        // do something

        return null;
    }
    
    
}
