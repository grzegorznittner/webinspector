package eu.nittner.webinspector.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WebContentBean {
    private String content;
    private long size;
    private String contentType;
    private long loadTime;
}
