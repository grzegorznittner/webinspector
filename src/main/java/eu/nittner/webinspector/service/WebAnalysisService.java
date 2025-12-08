package eu.nittner.webinspector.service;

import org.springframework.stereotype.Service;

@Service
public class WebAnalysisService {

    /**
     * Perform simple, deterministic analysis of the content.
     * This can later be extended with:
     * - HTML validation
     * - link checks
     * - SEO or accessibility rules
     */
    public String analyseContent(String content) {
        if (content == null || content.isBlank()) {
            return "No content to analyse.";
        }

        int length = content.length();
        long lineCount = content.lines().count();

        return "Deterministic analysis: length=" + length + " chars, lines=" + lineCount + ".";
    }
}
