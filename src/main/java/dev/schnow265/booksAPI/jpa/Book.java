package dev.schnow265.booksAPI.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private List<String> authorName;

    @Column(name = "first_publish_year")
    private int firstPublishYear;

    @Column(name = "cover_i")
    private int coverI;

    @Column(name = "has_fulltext")
    private boolean hasFulltext;

    @Column(name = "edition_count")
    private int editionCount;

    @Column(name = "key")
    private String key;

    @ElementCollection
    @CollectionTable(name = "ia_ids", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "ia_id")
    private List<String> ia;

    @Column(name = "public_scan_b")
    private boolean publicScanB;

    @Column(name = "author_key")
    private List<String> authorKey;
}
