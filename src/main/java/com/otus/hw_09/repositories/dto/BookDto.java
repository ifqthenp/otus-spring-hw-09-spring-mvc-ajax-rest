package com.otus.hw_09.repositories.dto;

import com.otus.hw_09.domain.Author;
import com.otus.hw_09.domain.Genre;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class BookDto {

    String title;
    List<Author> authors;
    List<Genre> genres;

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors.stream()
            .map(a -> a.getFirstName() + " " + a.getLastName())
            .collect(Collectors.joining(", "));
    }

    public String getGenres() {
        return genres.stream()
            .map(Genre::getGenreName)
            .collect(Collectors.joining(", "));
    }

}
