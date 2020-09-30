package com.trendyol.playlist.controller;

import com.trendyol.playlist.model.Playlist;
import com.trendyol.playlist.model.Track;
import com.trendyol.playlist.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Void> createPlaylist(@RequestBody Playlist playlist) {
        playlist.setTrackCount();
        playlistService.createPlaylist(playlist);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Playlist>> findById(@PathVariable ("id") String playlistId) {
        List<Playlist> response = playlistService.getPlaylistsByPlaylistId(playlistId);
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    public ResponseEntity<List<Playlist>> findAllByUserId(@RequestParam String userId){
        List<Playlist> response = playlistService.getPlaylistsByUserId(userId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") String playlistId) {
        playlistService.deletePlaylistByPlaylistId(playlistId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> addTrackToPlaylist(@PathVariable ("id") String playlistId, @RequestBody Track track) {
        playlistService.addTrackToPlaylist(playlistId, track);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{playlistId}/track/{trackkId}")
    public ResponseEntity<Void> delete(@PathVariable ("playlistId") String playlistId, @RequestBody Track track) {
        playlistService.deleteTrackFromPlaylist(playlistId, track);
        return ResponseEntity.noContent().build();
    }


}
