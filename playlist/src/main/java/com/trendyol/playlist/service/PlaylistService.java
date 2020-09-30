package com.trendyol.playlist.service;

import com.trendyol.playlist.model.Playlist;
import com.trendyol.playlist.model.Track;
import com.trendyol.playlist.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void createPlaylist(Playlist playlist) {
        this.playlistRepository.insert(playlist);
    }

    public List<Playlist> getPlaylistsByPlaylistId(String playlistId) {
        return playlistRepository.findByPlaylistId(playlistId);
    }

    public List<Playlist> getPlaylistsByUserId(String userId) {
        return playlistRepository.findByUserId(userId);
    }

    public void deletePlaylistByPlaylistId(String playlistId) {
        playlistRepository.deletePlaylistByPlaylistId(playlistId);

    }

    public void addTrackToPlaylist(String playlistId, Track track) {
        playlistRepository.insertTrackToPlaylist(playlistId, track);
    }

    public void deleteTrackFromPlaylist(String playlistId, Track track) {
        playlistRepository.deleteTrackFromPlaylist(playlistId, track);
    }
}
