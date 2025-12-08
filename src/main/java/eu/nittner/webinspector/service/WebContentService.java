package eu.nittner.webinspector.service;

import org.springframework.stereotype.Service;

@Service
public class WebContentService {

    /**
     * Normalize the input content (e.g. trim, normalize line breaks, etc.).
     */
    public String normalizeContent(String rawContent) {
        if (rawContent == null) {
            return "";
        }
        // Basic normalization – extend as needed.
        return rawContent
                .replace("\r\n", "\n")
                .trim();
    }

    /**
     * A hook for future logic like:
     * - extracting text from HTML
     * - resolving URLs to HTML content
     * - removing scripts/styles, etc.
     */
    public String extractText(String content) {
        if (content == null) {
            return "";
        }
        // Placeholder: currently just returns the content as-is.
        return content;
    }
}
