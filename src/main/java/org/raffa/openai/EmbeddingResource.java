package org.raffa.openai;

import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.embedding.EmbeddingResult;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/embeddings")
public class EmbeddingResource {

    @POST
    public EmbeddingResult createEmbedding(EmbeddingRequest embedding) {
        // do something

        return null;
    }

}