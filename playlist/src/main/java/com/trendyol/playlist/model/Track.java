package com.trendyol.playlist.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Track {

    private String name;
    private String id;
    private String length;
    private String artist;

    public Track() {
        this.id = UUID.randomUUID().toString();
    }
}