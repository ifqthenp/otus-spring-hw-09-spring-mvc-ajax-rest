package com.otus.hw_09.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.MongoClient;
import com.otus.hw_09.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonArray;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Slf4j
@ChangeLog
public class LibraryChangeLog {

    private static final String DATABASE = "library";
    private static final String AUTHORS = "authors";
    private static final String GENRES = "genres";
    private static final String BOOKS = "books";

    private static CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
        fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    @ChangeSet(author = "user", id = "dropDb", order = "001", runAlways = true)
    public void dropDb(final MongoTemplate template) {
        template.getDb().drop();
        log.info("Dropped db: {}", DATABASE);
    }

    @ChangeSet(author = "admin", id = "createInitialAuthors", order = "010")
    public void initializeAuthorsCollection(final MongoTemplate template) {
        final var collection = template.getDb().getCollection(AUTHORS);
        final var authors = new ArrayList<Document>() {{
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3d4"))
                .append("firstName", "Lewis")
                .append("lastName", "Carrol"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3d5"))
                .append("firstName", "Charlotte")
                .append("lastName", "Bronte"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3d6"))
                .append("firstName", "Miguel")
                .append("lastName", "de Cervantes"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3d7"))
                .append("firstName", "Herbert")
                .append("lastName", "Wells"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3d8"))
                .append("firstName", "Leo")
                .append("lastName", "Tolstoy"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3d9"))
                .append("firstName", "Jane")
                .append("lastName", "Austen"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3da"))
                .append("firstName", "Gabriel")
                .append("lastName", "García Márquez"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3db"))
                .append("firstName", "Gabriel")
                .append("firstName", "Leonardo")
                .append("lastName", "Fibonacci"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3dc"))
                .append("firstName", "Ilya")
                .append("lastName", "Ilf"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3dd"))
                .append("firstName", "Yevgeni")
                .append("lastName", "Petrov"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3de"))
                .append("firstName", new BsonString("Charles"))
                .append("lastName", new BsonString("Dickens")));
        }};

        collection.insertMany(authors);
        log.info("Created {} documents in '{}'", authors.size(), AUTHORS);
    }

    @ChangeSet(author = "admin", id = "createInitialGenres", order = "020")
    public void initializeGenresCollection(final MongoTemplate template) {
        final var collection = template.getDb().getCollection(GENRES);
        final var genres = new ArrayList<Document>() {{
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3e2"))
                .append("genreName", "Autobiography"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3e3"))
                .append("genreName", "Children's Literature"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3e4"))
                .append("genreName", "Fantasy"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3e5"))
                .append("genreName", "Literary realism"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3e6"))
                .append("genreName", "Mathematics"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3e7"))
                .append("genreName", "Novel"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3e8"))
                .append("genreName", "Romance"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3e9"))
                .append("genreName", "Satire"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3ea"))
                .append("genreName", "Science-fiction"));
            add(new Document()
                .append("_id", new ObjectId("5c6c5beb4c8518fde52fc3eb"))
                .append("genreName", "Trash"));
        }};

        collection.insertMany(genres);
        log.info("Created {} documents in '{}'", genres.size(), GENRES);
    }


    @ChangeSet(author = "admin", id = "createInitialBooks", order = "030")
    public void initializeBooksCollection(final MongoTemplate template) {
        final var collection = template.getDb()
            .withCodecRegistry(codecRegistry).getCollection(BOOKS);
        final var books = new ArrayList<Document>() {{
            add(new Document()
                .append("_id", new ObjectId("5c857854402f51169241931f"))
                .append("title", "Alice in Wonderland")
                .append("year", "1865")
                .append("authors", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3d4")))
                .append("genres", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e3"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e4")))
                .append("comments", Arrays.asList(
                    new Comment(1, "excellent"), new Comment(2, "awesome")))
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419320"))
                .append("title", "Jane Eyre")
                .append("year", "1847")
                .append("authors", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3d5")))
                .append("genres", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e7"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e2")))
                .append("comments", singletonList(
                    new Comment(1, "enjoyable reading")))
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419321"))
                .append("title", "Don Quixote")
                .append("year", new BsonString("1615"))
                .append("authors", new BsonArray(singletonList(
                    new BsonObjectId(new ObjectId("5c6c5beb4c8518fde52fc3d6")))))
                .append("genres", new BsonArray(singletonList(
                    new BsonObjectId(new ObjectId("5c6c5beb4c8518fde52fc3e7")))))
                .append("comments", singletonList(
                    new Comment(1, "classics")))
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419322"))
                .append("title", "The Time Machine")
                .append("year", "1895")
                .append("authors", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3d7")))
                .append("genres", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3ea")))
                .append("comments", new ArrayList<>())
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419323"))
                .append("title", "Anna Karenina")
                .append("year", "1878")
                .append("authors", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3d8")))
                .append("genres", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e5"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e7")))
                .append("comments", new ArrayList<>())
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419324"))
                .append("title", "Pride and Prejudice")
                .append("year", "1813")
                .append("authors", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3d9")))
                .append("genres", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e8"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e7")))
                .append("comments", new ArrayList<>())
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419325"))
                .append("title", "Childhood")
                .append("year", "1852")
                .append("authors", singletonList(
                    new BsonObjectId(new ObjectId("5c6c5beb4c8518fde52fc3d8"))))
                .append("genres", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e2"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e5"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e7")))
                .append("comments", new ArrayList<>())
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419326"))
                .append("title", "Boyhood")
                .append("year", "1854")
                .append("authors", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3d8")))
                .append("genres", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e2"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e5"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e7")))
                .append("comments", new ArrayList<>())
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419327"))
                .append("title", "Love in the Time of Cholera")
                .append("year", "1985")
                .append("authors", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3da")))
                .append("genres", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e7")))
                .append("comments", new ArrayList<>())
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419328"))
                .append("title", "The Book of Calculation")
                .append("year", "1202")
                .append("authors", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3db")))
                .append("genres", singletonList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e6")))
                .append("comments", new ArrayList<>())
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f511692419329"))
                .append("title", "The Twelve Chairs")
                .append("year", "1928")
                .append("authors", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3dd"),
                    new ObjectId("5c6c5beb4c8518fde52fc3dc")))
                .append("genres", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e9"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e7")))
                .append("comments", new ArrayList<>())
            );
            add(new Document()
                .append("_id", new ObjectId("5c857854402f51169241932a"))
                .append("title", "The Little Golden Calf")
                .append("year", "1931")
                .append("authors", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3dd"),
                    new ObjectId("5c6c5beb4c8518fde52fc3dc")))
                .append("genres", Arrays.asList(
                    new ObjectId("5c6c5beb4c8518fde52fc3e9"),
                    new ObjectId("5c6c5beb4c8518fde52fc3e7")))
                .append("comments", new ArrayList<>())
            );
        }};

        collection.insertMany(books);
        log.info("Created {} documents in '{}'", books.size(), BOOKS);
    }

}
