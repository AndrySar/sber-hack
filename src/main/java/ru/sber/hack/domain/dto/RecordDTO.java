package ru.sber.hack.domain.dto;

public class RecordDTO {
    private final String id;
    private final String title;
    private final String path;

    public RecordDTO(String id, String title, String path) {
        this.id = id;
        this.title = title;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }
}
