package com.otus.hw_09.configuration;

import com.otus.hw_09.domain.Author;
import com.otus.hw_09.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.List;

@Slf4j
public class AuthorCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if ((source instanceof Book) && ((Book) source).getAuthors() != null) {
            final List<Author> authors = ((Book) source).getAuthors();
            for (final Author author : authors) {
                try {
                    mongoOperations.insert(author);
                } catch (DuplicateKeyException e) {
                    log.warn("Author already exists: {}", author);
                }
            }
        }
    }

}
