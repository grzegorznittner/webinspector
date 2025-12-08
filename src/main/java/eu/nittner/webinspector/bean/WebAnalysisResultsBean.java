package eu.nittner.webinspector.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class WebAnalysisResultsBean {
    private String title;
    private long wordCount;
    private Map<String, Integer> wordFrequencies = new HashMap<>();
    private List<String> links = new ArrayList<>();
    private List<String> headers = new ArrayList<>();
}
