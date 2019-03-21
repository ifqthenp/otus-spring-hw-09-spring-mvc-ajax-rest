package com.otus.hw_09.repositories

import com.otus.hw_09.domain.Author
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification
import spock.lang.Subject

@DataMongoTest
class AuthorRepositorySpec extends Specification {

    @Subject
    @Autowired
    AuthorRepository authorRepo

    @Autowired
    MongoTemplate template

    def setup() {
        def authors = [
            new Author(id: new ObjectId(), firstName: 'Leo', lastName: 'Tolstoy'),
            new Author(id: new ObjectId(), firstName: 'Leonardo', lastName: 'Fibonacci'),
            new Author(id: new ObjectId(), firstName: 'Lewis', lastName: 'Carrol')
        ]
        authors.each { template.save(it) }
    }

    def "can find authors by their full name, case insensitive"() {
        given:
        def name = 'leo'

        when:
        def authors = authorRepo.findAuthorsByFirstNameContainingIgnoreCase(name)

        then:
        authors.size() == 2
        authors.get(0).firstName == 'Leo'
    }

    def cleanup() {
        template = null
        authorRepo = null
    }
}
