package com.example.FarkUrlShorter.repository;

import com.example.FarkUrlShorter.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long>{

    Optional<ShortUrl> findAllByCode(String code);

}
