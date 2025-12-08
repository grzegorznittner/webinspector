package eu.nittner.webinspector.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WebsiteAIResponseBean {
    private String summary;
    private String category;
    private int accessibilityScore;
}
