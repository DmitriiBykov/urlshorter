package ru.bykov.urlshorter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqids.Sqids;
import ru.bykov.urlshorter.models.UrlModel;
import ru.bykov.urlshorter.repositories.UrlRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    private UrlRepository urlRepository;

    public String save(String fullUrl) {
        Optional<UrlModel> model = urlRepository.findByFullUrl(fullUrl);
        if (model.isPresent()) {
            return model.get().getShortUrl();
        }
        String shortUrl = makeShorter(fullUrl);
        UrlModel urlModel = new UrlModel();
        urlModel.setFullUrl(fullUrl);
        urlModel.setShortUrl(shortUrl);
        urlModel.setTime(Timestamp.valueOf(LocalDateTime.now()));
        urlRepository.save(urlModel);
        deleteExpired();
        return shortUrl;
    }

    public String get(String shortlink) {
        Optional<UrlModel> model = urlRepository.findByShortUrl(shortlink);
        return model.map(UrlModel::getFullUrl).orElse(null);
    }

    public void deleteExpired() {
        urlRepository.deleteByTimeBefore(Timestamp.valueOf(LocalDateTime.now().minusMinutes(30)));
    }

    private String makeShorter(String fulllink) {
        Sqids sqids = Sqids.builder().build();
        return sqids.encode(List.of((long) Math.abs(fulllink.hashCode())));
    }

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlService() {
    }

    public UrlRepository getUrlRepository() {
        return urlRepository;
    }

    public void setUrlRepository(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
}
