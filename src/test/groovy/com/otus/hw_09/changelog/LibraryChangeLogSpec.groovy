package com.otus.hw_09.changelog

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class LibraryChangeLogSpec extends Specification {

    @Autowired
    MongoTemplate template

    void setup() {
        assert template != null
    }

    @Unroll
    def "LibraryChangeLog can insert '#collName' into library"() {
        given:
        def collection = template.getDb().getCollection(collName)

        expect:
        collection.countDocuments() == docsTotal

        where:
        collName  || docsTotal
        "books"   || 12
        "genres"  || 10
        "authors" || 11
    }


}
