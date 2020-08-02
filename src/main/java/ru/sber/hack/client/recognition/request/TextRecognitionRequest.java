package ru.sber.hack.client.recognition.request;

public class TextRecognitionRequest {
    private String text;

    public TextRecognitionRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
