package eu.nittner.webinspector.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WebAnalysisResultsBean {
    private String title;
    private long wordCount;
    private Map<String, Integer> wordFrequencies = Map.of();
    private List<String> links = List.of();
    private List<String> headers = List.of();
}
