package eu.nittner.webinspector.service;

import eu.nittner.webinspector.bean.WebAnalysisResultsBean;
import org.springframework.stereotype.Service;

@Service
public class WebAnalysisService {

    public WebAnalysisResultsBean analyseWebsiteContent(String websiteContent) {
        return new WebAnalysisResultsBean();
    }
}
