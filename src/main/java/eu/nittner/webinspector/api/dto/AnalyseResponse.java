package eu.nittner.webinspector.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AnalyseResponse {
    private Metrics metrics;
    private Content content;
    private AiInfo aiInfo;

    // Legacy constructor for backward compatibility
    public AnalyseResponse(String summary, String details) {
        this.aiInfo = new AiInfo();
        this.aiInfo.setSummary(summary);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metrics {
        private long loadTime;
        private long contentSize;
        private String contentType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        private String title;
        private List<String> metadata;
        private long wordCount;
        private List<String> headings;
        private List<String> topWords;
        private List<String> links;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AiInfo {
        private String summary;
        private String category;
        private double accessibilityScore;
    }
}
