package com.otus.hw_09.configuration;

import com.otus.hw_09.domain.Book;
import com.otus.hw_09.domain.Genre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.Set;

@Slf4j
public class GenreCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if ((source instanceof Book) && ((Book) source).getGenres() != null) {
            final Set<Genre> genres = ((Book) source).getGenres();
            for (final Genre genre : genres) {
                try {
                    mongoOperations.insert(genre);
                } catch (DuplicateKeyException e) {
                    log.warn("Genre already exists: {}", genre);
                }
            }
        }
    }

}
