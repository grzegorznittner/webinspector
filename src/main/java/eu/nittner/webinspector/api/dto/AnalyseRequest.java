package eu.nittner.webinspector.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseRequest {
    /**
     * Raw content to analyze, e.g. HTML, URL, or free text.
     */
    private String websiteUrl;
}
