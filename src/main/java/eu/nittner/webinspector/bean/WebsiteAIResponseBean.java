package eu.nittner.webinspector.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WebsiteAIResponseBean {
    private String summary;
    private String category;
    private int accessibilityScore;
}
