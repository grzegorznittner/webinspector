package eu.nittner.webinspector.api;

import eu.nittner.webinspector.api.dto.AnalyseRequest;
import eu.nittner.webinspector.api.dto.AnalyseResponse;
import eu.nittner.webinspector.service.WebInspectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webinspector")
public class WebInspectorController {

    private final WebInspectorService webInspectorService;

    public WebInspectorController(WebInspectorService webInspectorService) {
        this.webInspectorService = webInspectorService;
    }

    @PostMapping("/analyse")
    public ResponseEntity<AnalyseResponse> analyse(
            @RequestBody AnalyseRequest request) {

        AnalyseResponse response = webInspectorService.analyse(request);
        return ResponseEntity.ok(response);
    }
}
