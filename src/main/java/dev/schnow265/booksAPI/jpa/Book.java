package dev.schnow265.booksAPI.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "book", schema = "public")
public class Book {
    // TODO: Find ISBN and store it in the DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "first_publish_year")
    private int firstPublishYear;

    @Column(name = "cover_i")
    private int coverI;

    @Column(name = "has_fulltext")
    private boolean hasFulltext;

    @Column(name = "edition_count")
    private int editionCount;

    @Column(name = "book_key")
    private String key;

    @ElementCollection
    @CollectionTable(name = "ia_ids", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "ia_id")
    private List<String> ia;

    @Column(name = "public_scan_b")
    private boolean publicScanB;

    // Wenn authorKey auch eine Sammlung ist, sollte es als @ElementCollection definiert werden.
    @ElementCollection
    @CollectionTable(name = "author_key", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "author_key")
    private List<String> authorKey;


    // Crap for Unit Testing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return firstPublishYear == book.firstPublishYear &&
                coverI == book.coverI &&
                hasFulltext == book.hasFulltext &&
                editionCount == book.editionCount &&
                publicScanB == book.publicScanB &&
                Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(authorName, book.authorName) &&
                Objects.equals(key, book.key) &&
                Objects.equals(ia, book.ia) &&
                Objects.equals(authorKey, book.authorKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorName, firstPublishYear, coverI, hasFulltext, editionCount, key, ia, publicScanB, authorKey);
    }
}
