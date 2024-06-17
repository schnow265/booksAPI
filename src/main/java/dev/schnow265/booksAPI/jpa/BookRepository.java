package dev.schnow265.booksAPI.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthorName(List<String> authorName); // TODO: Add feature to find books by author name
}
