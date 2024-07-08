package ru.bykov.urlshorter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bykov.urlshorter.models.UrlModel;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Integer> {
    Optional<UrlModel> findByFullUrl(String fulllink);
    Optional<UrlModel> findByShortUrl(String shortlink);
    @Modifying
    @Transactional
    @Query("DELETE FROM UrlModel e WHERE e.time < :threshold")
    void deleteByTimeBefore(Timestamp threshold);
}