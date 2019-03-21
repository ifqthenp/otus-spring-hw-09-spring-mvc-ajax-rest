package com.otus.hw_09.repositories;

import com.otus.hw_09.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {

    @Query("{'genreName': { $regex: ?0, $options: 'i' }}")
    Set<Genre> findGenresByName(String genre);

}
