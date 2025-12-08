package eu.nittner.webinspector.service;

import eu.nittner.webinspector.bean.WebAnalysisResultsBean;
import eu.nittner.webinspector.exception.WebAnalysisException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class WebAnalysisService {

    public WebAnalysisResultsBean analyseWebsiteContent(String websiteUrl, String websiteContent) throws WebAnalysisException {
        try {
            WebAnalysisResultsBean resultsBean = new WebAnalysisResultsBean();
            InputStream inputStream = new ByteArrayInputStream(websiteContent.getBytes(StandardCharsets.UTF_8));
            Document doc = Jsoup.parse(inputStream, "UTF-8", websiteUrl);

            getLinks(resultsBean, doc);
            getHeadings(resultsBean, doc);

            return resultsBean;
        } catch (Exception e) {
            throw new WebAnalysisException("Error analyzing website content", e);
        }
    }

    private void getHeadings(WebAnalysisResultsBean resultsBean, Document doc) {
        Elements imports = doc.select("h1, h2, h3");
        for (Element header : imports) {
            String linkHref = header.text();
            resultsBean.getHeaders().add(linkHref);
        }
    }

    private void getLinks(WebAnalysisResultsBean resultsBean, Document doc) {
        Elements imports = doc.select("link[href]");
        for (Element link : imports) {
            String linkHref = link.attr("abs:href");
            resultsBean.getLinks().add(linkHref);
        }
    }
}
