package com.trendyol.playlist.repository;

import com.couchbase.client.java.query.QueryResult;
import com.trendyol.playlist.model.Playlist;
import com.trendyol.playlist.model.Track;
import org.springframework.stereotype.Repository;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;

import java.util.List;

@Repository
public class PlaylistRepository {

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public void insert(Playlist playlist) {
        playlistCollection.insert(playlist.getId(),playlist);
    }

    public List<Playlist> findByPlaylistId(String playlistId) {
        String statement = String.format("SELECT `Playlist`.* FROM Playlist WHERE id = '%s'", playlistId);
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Playlist.class);
    }

    public List<Playlist> findByUserId(String userId) {
        String statement = String.format("SELECT `Playlist`.* FROM Playlist WHERE userId = '%s'", userId);
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Playlist.class);
    }

    public void deletePlaylistByPlaylistId(String playlistId) {
        playlistCollection.remove(playlistId);
    }

    public void insertTrackToPlaylist(String playlistId, Track track) {
        String statement = String.format("SELECT `Playlist`.* FROM Playlist WHERE id = '%s'", playlistId);
        QueryResult query = couchbaseCluster.query(statement);
        Playlist currentPlaylist =  query.rowsAs(Playlist.class).get(0);
        currentPlaylist.addTrack(track);
        currentPlaylist.setTrackCount();
        playlistCollection.upsert(playlistId, currentPlaylist);
    }

    public void deleteTrackFromPlaylist(String playlistId, Track track) {
        String statement = String.format("SELECT `Playlist`.* FROM Playlist WHERE id = '%s'", playlistId);
        QueryResult query = couchbaseCluster.query(statement);
        Playlist currentPlaylist =  query.rowsAs(Playlist.class).get(0);
        currentPlaylist.removeTrack(track);
        currentPlaylist.setTrackCount();
        playlistCollection.upsert(playlistId, currentPlaylist);
    }
}
