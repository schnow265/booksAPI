package dev.schnow265.booksAPI.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    @Override
    Optional<ApiKey> findById(Long aLong);

    Optional<ApiKey> findByKey(String key);
}
