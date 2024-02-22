package org.raffa.openai;

import java.util.List;

import com.theokanning.openai.model.Model;

import fmaas.MutinyGenerationServiceGrpc;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import io.smallrye.mutiny.Uni;


@Path("/models")
public class ModelsResource {

    @GrpcClient("tgis")
    MutinyGenerationServiceGrpc.MutinyGenerationServiceStub tgis;

    @GET
    public Uni<List<Model>> listModels() {
        // do something

        return null;
    }

    @GET
    @Path("/{name}")
    public Uni<Model> listModel(@PathParam("name")String modelName) {
        // do something

        return null;
    }
    
}
