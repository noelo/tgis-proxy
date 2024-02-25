package org.raffa.openai;

import java.util.ArrayList;
import java.util.List;

import com.theokanning.openai.Usage;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;

import fmaas.Generation.BatchedGenerationRequest;
import fmaas.Generation.BatchedGenerationResponse;
import fmaas.Generation.DecodingMethod;
import fmaas.Generation.DecodingParameters;
import fmaas.Generation.GenerationRequest;
import fmaas.Generation.Parameters;
import fmaas.Generation.ResponseOptions;
import fmaas.Generation.SamplingParameters;
import fmaas.Generation.StoppingCriteria;
import fmaas.MutinyGenerationServiceGrpc;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/chat/completions")
public class ChatResource {
    @GrpcClient("tgis")
    MutinyGenerationServiceGrpc.MutinyGenerationServiceStub tgis;

    @POST
    public Uni<ChatCompletionResult> createChatCompletion(ChatCompletionRequest chatcomp) {

        StringBuilder sbuilder = new StringBuilder();
        for (ChatMessage msg : chatcomp.getMessages()) {
            sbuilder.append(msg.toString());
        }

        GenerationRequest generationRequest = GenerationRequest.newBuilder()
                .setText(sbuilder.toString())
                .build();

        DecodingParameters dparams = DecodingParameters.newBuilder()
                .setRepetitionPenalty(chatcomp.getPresencePenalty().floatValue())
                .build();

        SamplingParameters sparams = SamplingParameters.newBuilder()
                .setTemperature(chatcomp.getTemperature().floatValue())
                .setTopP(chatcomp.getTopP().floatValue())
                .build();

        StoppingCriteria stopp = StoppingCriteria.newBuilder()
                .setMaxNewTokens(chatcomp.getMaxTokens())
                .setTimeLimitMillis(0)
                .build();

        ResponseOptions rparams = ResponseOptions.newBuilder()
                .setInputText(false)
                .setGeneratedTokens(true)
                .setTokenLogprobs(true)
                .build();

        Parameters params = Parameters.newBuilder().setDecoding(dparams)
                .setMethod(DecodingMethod.GREEDY)
                .setSampling(sparams)
                .setStopping(stopp)
                .setDecoding(dparams)
                .setResponse(rparams)
                .build();

        BatchedGenerationRequest batchrequest = BatchedGenerationRequest.newBuilder()
                .setModelId(chatcomp.getModel())
                .setParams(params)
                .setRequests(0, generationRequest)
                .build();

        Uni<BatchedGenerationResponse> batchedRequestResponse = tgis.generate(batchrequest);

        return batchedRequestResponse.map(brr -> responseMapper(brr));

    }

    private ChatCompletionResult responseMapper(BatchedGenerationResponse tgisresp){

        List<ChatCompletionChoice> respList= new ArrayList<ChatCompletionChoice>();
        int gen_token_count=0,input_token_count=0;

        for (int i=0;i <= tgisresp.getResponsesCount();i++) {
            ChatMessage msg = new ChatMessage();
            msg.setContent(tgisresp.getResponses(i).getText());
            ChatCompletionChoice resp = new ChatCompletionChoice();
            resp.setMessage(msg);
            resp.setFinishReason(tgisresp.getResponses(i).getStopSequence());
            respList.add(resp);
            gen_token_count += tgisresp.getResponses(i).getGeneratedTokenCount();
            input_token_count += tgisresp.getResponses(i).getInputTokenCount();
        }

        ChatCompletionResult resp = new ChatCompletionResult();
        Usage token_usage = new Usage();
        token_usage.setCompletionTokens(gen_token_count);
        token_usage.setPromptTokens(input_token_count);
        resp.setUsage(token_usage);
        resp.setChoices(respList);
        return resp;
    }

}
