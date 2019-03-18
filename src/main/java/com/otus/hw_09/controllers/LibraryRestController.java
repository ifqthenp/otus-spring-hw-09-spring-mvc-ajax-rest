package com.otus.hw_09.controllers;

import com.otus.hw_09.controllers.dto.BookFormDto;
import com.otus.hw_09.domain.Book;
import com.otus.hw_09.library.LibraryService;
import com.otus.hw_09.repositories.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LibraryRestController {

    private final LibraryService libraryService;

    @GetMapping(value = "/library/api/books/search", produces = "application/json")
    public ResponseEntity<List<BookDto>> quickSearch(@RequestParam(value = "title", required = false) final String title) {
        List<BookDto> books = new ArrayList<>();
        if (!title.isBlank()) {
            books = libraryService.findBooksByTitleRequestParam(title);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(
        value = "/library/api/books/save",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<Book> saveBook(@Validated final BookFormDto bookFormDto,
                                         final BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        final Book savedBook = libraryService.saveBook(bookFormDto.toEntity(bookFormDto));
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

}
