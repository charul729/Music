package com.musicapp.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.musicapp.demo.model.Song;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
}
