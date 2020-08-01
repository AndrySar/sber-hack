package ru.sber.hack.domain.dto;

public class RecordDTO {
    private final String id;
    private final String text;
    private final String fileName;

    public RecordDTO(String id, String text, String fileName) {
        this.id = id;
        this.text = text;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getFileName() {
        return fileName;
    }
}
