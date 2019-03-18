package com.otus.hw_09.controllers;

import com.otus.hw_09.controllers.dto.BookFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LibraryController {

    @GetMapping("/library/books/add")
    public String bookAdd(final Model model) {
        return "book_add_new";
    }

    @GetMapping(value = "/library/books/search")
    public String bookSearchForm() {
        return "book_search_form";
    }

    @ModelAttribute("bookFormDto")
    public BookFormDto getBookFormDto() {
        return new BookFormDto();
    }

}
