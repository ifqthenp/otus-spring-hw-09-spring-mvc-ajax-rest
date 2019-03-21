package com.otus.hw_09.controllers.dto;

import com.otus.hw_09.domain.Author;
import com.otus.hw_09.domain.Book;
import com.otus.hw_09.domain.Genre;
import lombok.Data;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

@Data
public class BookFormDto {

    @NotBlank
    private String title;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String genre;

    @NotBlank
    @Pattern(
        regexp = "\\d{4}",
        message = "Invalid year format. Must be 4 digits"
    )
    private String year;

    public Book toEntity(final BookFormDto bookFormDto) {
        final Author author = new Author();
        author.setFirstName(bookFormDto.getFirstName());
        author.setLastName(bookFormDto.getLastName());
        final List<Author> authors = new ArrayList<>();
        authors.add(author);

        final Genre genre = new Genre();
        genre.setGenreName(bookFormDto.getGenre());
        final Set<Genre> genres = new HashSet<>();
        genres.add(genre);

        final Book book = new Book();
        book.setId(new ObjectId());
        book.setTitle(bookFormDto.getTitle());
        book.setYear(bookFormDto.getYear());

        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

}
