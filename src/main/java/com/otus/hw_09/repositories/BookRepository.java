package com.otus.hw_09.repositories;

import com.otus.hw_09.domain.Book;
import com.otus.hw_09.repositories.dto.BookDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<BookDto> findBooksByTitleContainingIgnoreCase(String text);

}
