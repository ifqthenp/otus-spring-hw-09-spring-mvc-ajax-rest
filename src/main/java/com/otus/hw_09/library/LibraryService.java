package com.otus.hw_09.library;

import com.otus.hw_09.domain.Book;
import com.otus.hw_09.repositories.AuthorRepository;
import com.otus.hw_09.repositories.BookRepository;
import com.otus.hw_09.repositories.GenreRepository;
import com.otus.hw_09.repositories.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public long getAuthorsCount() {
        return authorRepository.count();
    }

    public long getBooksCount() {
        return bookRepository.count();
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public List<BookDto> findBooksByTitleRequestParam(final String title) {
        return bookRepository.findBooksByTitleContainingIgnoreCase(title);
    }

}
