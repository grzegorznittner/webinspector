package eu.nittner.webinspector.service;

import eu.nittner.webinspector.bean.WebsiteAIResponseBean;
import org.springframework.stereotype.Service;

@Service
public class WebsiteAIService {

    public WebsiteAIResponseBean analyseWebsite(String websiteUrl) {
        return new WebsiteAIResponseBean();
    }
}
