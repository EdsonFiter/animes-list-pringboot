package com.animes.animelist.repository;

import com.animes.animelist.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByName(String name);
    List<Anime> findByLaunchDate(LocalDate launchDate);
    List<Anime> findByCreator(String creator);
    Boolean existsByName(String name);
}
