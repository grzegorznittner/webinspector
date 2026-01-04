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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class WebAnalysisService {

    public WebAnalysisResultsBean analyseWebsiteContent(String websiteUrl, String websiteContent) throws WebAnalysisException {
        try {
            WebAnalysisResultsBean resultsBean = new WebAnalysisResultsBean();
            InputStream inputStream = new ByteArrayInputStream(websiteContent.getBytes(StandardCharsets.UTF_8));
            Document doc = Jsoup.parse(inputStream, "UTF-8", websiteUrl);

            resultsBean.setTitle(doc.title());
            resultsBean.setWordCount(getWordCount(doc.text()));

            getLinks(resultsBean, doc);
            getHeadings(resultsBean, doc);
            calculateWordFrequencies(resultsBean, doc);

            return resultsBean;
        } catch (Exception e) {
            throw new WebAnalysisException("Error analyzing website content", e);
        }
    }

    private void calculateWordFrequencies(WebAnalysisResultsBean resultsBean, Document doc) {
        String text = doc.text().toLowerCase();

        Set<String> bannedWords = Set.of(
                "a", "an", "the", "and", "or", "i", "am", "you", "are",
                "he", "she", "it", "is", "we", "they", "do", "does", "did",
                "will", "would", "can", "could", "has", "had", "to", "your",
                "for", "on", "by", "with", "in", "all", "of", "from", "more",
                "ago", "were", "was", "not"
        );

        Map<String, Integer> wordFrequencies = new HashMap<>();

        String[] words = text.split("\\W+");
        for (String word : words) {
            word = word.trim();

            if (bannedWords.contains(word)) {
                continue;
            }

            if (wordFrequencies.containsKey(word)) {
                wordFrequencies.put(word, wordFrequencies.get(word) + 1);
            } else {
                wordFrequencies.put(word, 1);
            }
        }

        resultsBean.setWordFrequencies(wordFrequencies);
    }

    private long getWordCount(String text) {
        return text.split("\\s+").length;
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
