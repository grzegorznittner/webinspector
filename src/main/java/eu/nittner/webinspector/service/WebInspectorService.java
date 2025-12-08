package eu.nittner.webinspector.service;

import eu.nittner.webinspector.api.dto.AnalyseRequest;
import eu.nittner.webinspector.api.dto.AnalyseResponse;
import org.springframework.stereotype.Service;

@Service
public class WebInspectorService {

    public AnalyseResponse analyse(AnalyseRequest request) {
        if (request == null || request.getContent() == null || request.getContent().isBlank()) {
            return new AnalyseResponse(
                    "No content provided",
                    "The request body was empty or contained only whitespace."
            );
        }

        // Long‑term placeholder for real analysis logic.
        // Here you could:
        // - Run static checks on the HTML
        // - Call an AI model
        // - Extract metadata, links, issues, etc.
        String content = request.getContent();
        int length = content.length();

        String summary = "Content analyzed successfully";
        String details = "Content length: " + length + " characters.";

        return new AnalyseResponse(summary, details);
    }
}
