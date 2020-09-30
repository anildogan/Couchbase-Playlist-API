package com.trendyol.playlist.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Data
public class Playlist {
    private String id;
    private String name;
    private String description;
    private int followersCount;
    private List<Track> tracks;
    private int trackCount;
    private String userId;

    public Playlist() {
        this.id = UUID.randomUUID().toString();
    }

    public void setTrackCount() {
        this.trackCount = this.getTracks().size();
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }
    public void removeTrack(Track track) {
        this.tracks.remove(track);
    }
}
