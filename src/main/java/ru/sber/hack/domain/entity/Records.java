package ru.sber.hack.domain.entity;

public class Records {
    private String id;
    private String audioFile;
    private String text;

    public Records(String id, String audioFile, String text) {
        this.id = id;
        this.audioFile = audioFile;
        this.text = text;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
}
