package at.schnow265.booksAPI.jpa;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book", schema = "public")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection
    @CollectionTable(name = "author_names", joinColumns = @JoinColumn(name = "book_id"))
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

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(List<String> authorName) {
        this.authorName = authorName;
    }

    public int getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(int firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public int getCoverI() {
        return coverI;
    }

    public void setCoverI(int coverI) {
        this.coverI = coverI;
    }

    public boolean isHasFulltext() {
        return hasFulltext;
    }

    public void setHasFulltext(boolean hasFulltext) {
        this.hasFulltext = hasFulltext;
    }

    public int getEditionCount() {
        return editionCount;
    }

    public void setEditionCount(int editionCount) {
        this.editionCount = editionCount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getIa() {
        return ia;
    }

    public void setIa(List<String> ia) {
        this.ia = ia;
    }

    public List<String> getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(List<String> authorKey) {
        this.authorKey = authorKey;
    }

    public boolean isPublicScanB() {
        return publicScanB;
    }

    public void setPublicScanB(boolean publicScanB) {
        this.publicScanB = publicScanB;
    }
}
