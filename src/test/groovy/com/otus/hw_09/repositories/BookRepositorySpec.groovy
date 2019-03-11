package com.otus.hw_09.repositories

import com.otus.hw_09.domain.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification
import spock.lang.Subject

@DataMongoTest
class BookRepositorySpec extends Specification {

    @Subject
    @Autowired
    BookRepository bookRepo

    @Autowired
    MongoTemplate template

    def setup() {
        def books = [
            new Book('id-1', 'Title-1', '1990', ['authorId-1'], ['genreId-1'] as Set, []),
            new Book('id-2', 'Title-2', '1990', ['authorId-2'], ['genreId-2'] as Set, []),
            new Book('id-3', 'Title-3', '1990', ['authorId-3'], ['genreId-3'] as Set, [])
        ]
        books.each { template.save(it) }
    }

    def "can find book by title, case insensitive"() {
        given:
        def title = 'titl'

        when:
        def books = bookRepo.findBooksByTitleContainingIgnoreCase(title)

        then:
        books.size() == 3
        books.get(0).title == 'Title-1'
    }

    def cleanup() {
        bookRepo = null
        template = null
    }
}
