package eu.nittner.webinspector.service;

import eu.nittner.webinspector.bean.WebContentBean;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class WebContentService {

    @SneakyThrows
    public WebContentBean fetchWebsiteData(String websiteUrl) {
        long startTime = System.currentTimeMillis();
        String content = "";
        long size = 0;
        String contentType = "";

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(websiteUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        content = response.body();
        size = content.length();
        contentType = response.headers().firstValue("content-type").orElse("text/plain");

        long loadTime = System.currentTimeMillis() - startTime;

        return new WebContentBean(content, size, contentType, loadTime);
    }
}
