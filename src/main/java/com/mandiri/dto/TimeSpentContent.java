package com.mandiri.dto;

public class TimeSpentContent<T> {
    private final T content;
    private final String timeSpent;

    public TimeSpentContent(T t, String timeSpent) {
        this.content = t;
        this.timeSpent = timeSpent;
    }

    public T getContent() {
        return content;
    }

    public String getTimeSpent() {
        return timeSpent;
    }
}
