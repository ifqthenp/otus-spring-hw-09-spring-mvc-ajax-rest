package com.otus.hw_09.repositories

import com.otus.hw_09.domain.Book
import org.bson.types.ObjectId
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
            new Book(id: new ObjectId(), title: 'Title-1', year: '1990', authors: [], genres: [] as Set, comments: []),
            new Book(id: new ObjectId(), title: 'Title-2', year: '1990', authors: [], genres: [] as Set, comments: []),
            new Book(id: new ObjectId(), title: 'Title-3', year: '1990', authors: [], genres: [] as Set, comments: [])
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
