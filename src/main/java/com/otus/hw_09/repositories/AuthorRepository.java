package com.otus.hw_09.repositories;

import com.otus.hw_09.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findAuthorsByFirstNameContainingIgnoreCase(String firstName);

}
