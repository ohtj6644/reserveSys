package com.example.reserveSys.Service;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SpringAiService {
    private  final OpenAiChatModel openAiChatModel;
    // private  final VertexAiGeminiChatModel vertexAiGeminiChatModel;

    public  SpringAiService(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
        //this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
    }
    public String aiExplanationBack(String bookName, String writer) {
        //Map<String, String> responses = new HashMap<>();
        String question = writer +"작가의 "+bookName +" 도서에 대해 설명해줘";

        String openAiResponse = openAiChatModel.call(question);

//        String vertexAiGeminiResponse = vertexAiGeminiChatModel.call(message);
//        responses.put("vertexai(gemini) 응답", vertexAiGeminiResponse);
        return openAiResponse;
    }
}
