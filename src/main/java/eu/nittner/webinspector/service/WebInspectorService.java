package eu.nittner.webinspector.service;

import eu.nittner.webinspector.api.dto.AnalyseRequest;
import eu.nittner.webinspector.api.dto.AnalyseResponse;
import eu.nittner.webinspector.bean.WebAnalysisResultsBean;
import eu.nittner.webinspector.bean.WebContentBean;
import eu.nittner.webinspector.bean.WebsiteAIResponseBean;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebInspectorService {
    private final WebContentService webContentService;
    private final WebAnalysisService webAnalysisService;
    private final WebsiteAIService websiteAIService;

    @SneakyThrows
    public AnalyseResponse analyse(AnalyseRequest request) {
        if (request == null || request.getWebsiteUrl() == null || request.getWebsiteUrl().isBlank()) {
            return new AnalyseResponse(
                    "No content provided",
                    "The request body was empty or contained only whitespace."
            );
        }

        String websiteUrl = request.getWebsiteUrl();
        WebContentBean webContentBean = webContentService.fetchWebsiteData(websiteUrl);

        WebAnalysisResultsBean analysisResults = webAnalysisService.analyseWebsiteContent(request.getWebsiteUrl(), webContentBean.getContent());

        WebsiteAIResponseBean aiResponse = websiteAIService.analyseWebsite(websiteUrl);

        // Create and populate the response with structured data
        return mapToAnalyseResponse(webContentBean, analysisResults, aiResponse);

    }

    private static AnalyseResponse mapToAnalyseResponse(WebContentBean webContentBean, WebAnalysisResultsBean analysisResults, WebsiteAIResponseBean aiResponse) {
        AnalyseResponse response = new AnalyseResponse();

        // Set metrics
        AnalyseResponse.Metrics metrics = new AnalyseResponse.Metrics();
        metrics.setLoadTime(webContentBean.getLoadTime());
        metrics.setContentSize(webContentBean.getSize());
        metrics.setContentType(webContentBean.getContentType());
        response.setMetrics(metrics);

        // Set content
        AnalyseResponse.Content content = new AnalyseResponse.Content();
        content.setTitle(analysisResults.getTitle());
        content.setMetadata(Collections.emptyList()); // Empty list as per example
        content.setWordCount(analysisResults.getWordCount());
        content.setHeadings(analysisResults.getHeaders());

        // Extract top words from word frequencies
        List<String> topWords = analysisResults.getWordFrequencies().entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        content.setTopWords(topWords);

        content.setLinks(analysisResults.getLinks());
        response.setContent(content);

        // Set AI info
        AnalyseResponse.AiInfo aiInfo = new AnalyseResponse.AiInfo();
        aiInfo.setSummary(aiResponse.getSummary());
        aiInfo.setCategory(aiResponse.getCategory());
        aiInfo.setAccessibilityScore(aiResponse.getAccessibilityScore());
        response.setAiInfo(aiInfo);

        return response;
    }
}
