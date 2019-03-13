package com.otus.hw_09.configuration;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import com.mongodb.MongoClient;
import com.otus.hw_09.changelog.LibraryChangeLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AppConfig {

    private static final String LIBRARY = "library";
    private static final String LIBRARY_TEST = "library-test";

    @Bean
    @ConditionalOnProperty(prefix = "app.config", name = "dbType", havingValue = "mongo-docker")
    public Mongock mongock(final ApplicationContext ac, final MongoClient mongoClient) {
        log.info("invoked mongock(ApplicationContext, MongoClient)");
        return new SpringBootMongockBuilder(mongoClient, LIBRARY,
            LibraryChangeLog.class.getPackage().getName())
            .setApplicationContext(ac)
            .setLockQuickConfig()
            .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.config", name = "dbType", havingValue = "mongo-embedded")
    public Mongock mongockTest(final ApplicationContext ac, final MongoClient mongoClient) {
        log.info("invoked mongockTest(ApplicationContext, MongoClient)");
        return new SpringBootMongockBuilder(mongoClient, LIBRARY_TEST,
            LibraryChangeLog.class.getPackage().getName())
            .setApplicationContext(ac)
            .setLockQuickConfig()
            .build();
    }

    @Bean
    public GenreCascadeSaveMongoEventListener genreCascadeSaveMongoEventListener() {
        return new GenreCascadeSaveMongoEventListener();
    }

    @Bean
    public AuthorCascadeSaveMongoEventListener authorCascadeSaveMongoEventListener() {
        return new AuthorCascadeSaveMongoEventListener();
    }

}
