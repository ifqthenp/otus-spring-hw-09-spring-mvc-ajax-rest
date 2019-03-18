package com.otus.hw_09.controllers


import com.otus.hw_09.library.LibraryService
import com.otus.hw_09.repositories.dto.BookDto
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(LibraryRestController)
class LibraryRestControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    LibraryService service = Mock()

    def "can find books by title"() {
        given:
        def title = 'time'

        and:
        service.findBooksByTitleRequestParam(title) >> [
            new BookDto("The Time Machine", [], []),
            new BookDto("Love in the Time of Cholera", [], [])
        ]

        expect:
        mockMvc.perform(MockMvcRequestBuilders.get("/library/api/books/search")
            .param("title", title))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath('$[0].title', is("The Time Machine")))
            .andExpect(jsonPath('$[0].authors', is("")))
            .andExpect(jsonPath('$[1].title', is("Love in the Time of Cholera")))
            .andExpect(jsonPath('$[1].genres', is("")))
    }

    def "can save a book"() {
        given:
        def title = 'Time'
        def firstName = 'John'
        def lastName = 'Doe'
        def genre = 'Trash'
        def year = '2000'

        expect:
        mockMvc.perform(post("/library/api/books/save")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .param("title", title)
            .param("firstName", firstName)
            .param("lastName", lastName)
            .param("genre", genre)
            .param("year", year))
            .andDo(print())
            .andExpect(status().isCreated())
    }
}
