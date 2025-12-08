package eu.nittner.webinspector.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.nittner.webinspector.bean.WebsiteAIResponseBean;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteAIService {

    private final ChatClient chatClient;
    private ObjectMapper objectMapper = new ObjectMapper();

    public WebsiteAIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public WebsiteAIResponseBean analyseWebsite(String websiteContent) throws JsonProcessingException {
        // Build the prompt for the LLM
        String question = """
                You are a web analysis assistant.

                Given the following website content, do the following:
                1. Provide a short summary of the website (2–3 sentences).
                2. Provide a high-level category of the website (e.g. "e-commerce", "blog", "portfolio", "news", "SaaS", etc.).
                3. Provide an accessibility score as a decimal number from 0.0 to 1.0, 
                   where 0.0 is very poor accessibility and 1.0 is excellent accessibility.

                Return your answer in strict JSON format like:
                {
                  "summary": "...",
                  "category": "...",
                  "accessibilityScore": 0.0
                }

                Website content:
                %s
                """.formatted(websiteContent);

        UserMessage userMessage = new UserMessage(question);
        Prompt prompt = new Prompt(List.of(userMessage));

        String result = chatClient.prompt(prompt).call().content();
        return objectMapper.readValue(result, WebsiteAIResponseBean.class);
    }
}
