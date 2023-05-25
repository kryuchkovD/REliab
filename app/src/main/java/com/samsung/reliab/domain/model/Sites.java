package com.samsung.reliab.domain.model;

import androidx.annotation.NonNull;

public class Sites {
    private final long id;
    private final String name;
    private final String url;
    private final String status;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {return status;}

    public Sites(long id, String name, String url, String status) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.status = status;
    }
    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
