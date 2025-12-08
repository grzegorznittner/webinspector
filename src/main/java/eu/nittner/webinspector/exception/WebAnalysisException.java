package eu.nittner.webinspector.exception;

public class WebAnalysisException extends Exception {
    public WebAnalysisException(String message) {
        super(message);
    }

    public WebAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}
