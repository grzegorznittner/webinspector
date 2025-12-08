package eu.nittner.webinspector.service;

import eu.nittner.webinspector.api.dto.AnalyseRequest;
import eu.nittner.webinspector.api.dto.AnalyseResponse;
import eu.nittner.webinspector.bean.WebAnalysisResultsBean;
import eu.nittner.webinspector.bean.WebContentBean;
import eu.nittner.webinspector.bean.WebsiteAIResponseBean;
import org.springframework.stereotype.Service;

@Service
public class WebInspectorService {
    private WebContentService webContentService;
    private WebAnalysisService webAnalysisService;
    private WebsiteAIService websiteAIService;

    public AnalyseResponse analyse(AnalyseRequest request) {
        if (request == null || request.getWebsiteUrl() == null || request.getWebsiteUrl().isBlank()) {
            return new AnalyseResponse(
                    "No content provided",
                    "The request body was empty or contained only whitespace."
            );
        }

        String websiteUrl = request.getWebsiteUrl();
        WebContentBean webContentBean = webContentService.fetchWebsiteData(websiteUrl);

        WebAnalysisResultsBean analysisResults = webAnalysisService.analyseWebsiteContent(webContentBean.getContent());

        WebsiteAIResponseBean aiResponse = websiteAIService.analyseWebsite(websiteUrl);

        String summary = "Content analyzed successfully";
        String details = "Content length: " + webContentBean.getSize() + " characters.";

        return new AnalyseResponse(summary, details);
    }
}
