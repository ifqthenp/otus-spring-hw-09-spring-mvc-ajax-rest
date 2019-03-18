package com.otus.hw_09.controllers

import com.otus.hw_09.library.LibraryService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(HomeRestController)
class HomeRestControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    LibraryService service = Mock()

    def "home page should display correct count of books"() {
        given:
        def booksTotal = 10
        def authorsTotal = 15

        and:
        service.getBooksCount() >> booksTotal
        service.getAuthorsCount() >> authorsTotal

        expect:
        mockMvc.perform(MockMvcRequestBuilders.get("/library/api/totals"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath('$.authorsCount', is(authorsTotal)))
            .andExpect(jsonPath('$.booksCount', is(booksTotal)))
    }
}
