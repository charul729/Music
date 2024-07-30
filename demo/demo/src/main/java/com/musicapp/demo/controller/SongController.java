package com.musicapp.demo.controller;

import com.musicapp.demo.model.Song;
import com.musicapp.demo.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private StorageService storageService;

        @GetMapping
    public List<Song> getAllSongs() {
        return storageService.getAllSongs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable String id) {
        Optional<Song> song = storageService.getSongById(id);
        return song.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return storageService.saveSong(song);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable String id, @RequestBody Song songDetails) {
        Optional<Song> songOptional = storageService.getSongById(id);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            song.setFileName(songDetails.getFileName());
            song.setTitle(songDetails.getTitle());
            song.setArtist(songDetails.getArtist());
            song.setFavorited(songDetails.isFavorited());
            return ResponseEntity.ok(storageService.saveSong(song));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable String id) {
        storageService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
