package com.otus.hw_09.controllers;

import com.otus.hw_09.library.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HomeRestController {

    private final LibraryService libraryService;

    @GetMapping(path = "/library/api/totals", produces = {"application/json"})
    public ResponseEntity<Map> getLibraryTotals() {
        final long booksCount = libraryService.getBooksCount();
        final long authorsCount = libraryService.getAuthorsCount();
        final Map<String, Long> totals = new HashMap<>();
        totals.put("booksCount", booksCount);
        totals.put("authorsCount", authorsCount);
        return new  ResponseEntity<>(totals, HttpStatus.OK);
    }

}
