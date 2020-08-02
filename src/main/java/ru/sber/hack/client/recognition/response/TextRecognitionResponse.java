package ru.sber.hack.client.recognition.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TextRecognitionResponse {
    private String path;

    @JsonCreator
    public TextRecognitionResponse(@JsonProperty("path") String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
