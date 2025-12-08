package eu.nittner.webinspector.service;

import eu.nittner.webinspector.bean.WebContentBean;
import org.springframework.stereotype.Service;

@Service
public class WebContentService {

    public WebContentBean fetchWebsiteData(String websiteUrl) {

        return new WebContentBean();
    }
}
