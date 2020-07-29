package com.saturnitservices.eventframework.model;

import lombok.Data;

@Data
public class Event<T> {

    private String name, txId;
    private int count;
    private T data;

    public void incrementCount() {
        ++count;
    }
}
