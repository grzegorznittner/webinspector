package eu.nittner.webinspector.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseResponse {
    /**
     * High‑level textual analysis of the input content.
     */
    private String summary;

    /**
     * Optional structured detail (e.g. issues found, metadata).
     */
    private String details;
}
