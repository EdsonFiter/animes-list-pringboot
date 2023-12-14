package com.animes.animelist.service;

import com.animes.animelist.domain.Anime;
import com.animes.animelist.exception.BadRequestException;
import com.animes.animelist.mapper.AnimeMapper;
import com.animes.animelist.repository.AnimeRepository;
import com.animes.animelist.request.AnimePostRequest;
import com.animes.animelist.request.AnimePutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }
    public Anime findById(long id){
        return animeRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Anime is taken"));
    }
    public List<Anime> findByName(String name){
        return animeRepository.findByName(name);
    }
    public List<Anime> findByLaunchDate(String launchDate){
        return animeRepository.findByLaunchDate(LocalDate.parse(launchDate));
    }
    public List<Anime> findByCreator(String creator){
        return animeRepository.findByCreator(creator);
    }
    @Transactional
    public Anime save(AnimePostRequest request){
        if(animeRepository.existsByName(request.getName())){
            throw new BadRequestException("Anime is taken");
        }
        Anime anime = AnimeMapper.INSTANCE.toAnime(request);
        return animeRepository.save(anime);
    }
    @Transactional
    public void replace(AnimePutRequest request){
        Anime anime = findById(request.getId());
        Anime animeToSave = AnimeMapper.INSTANCE.toAnime(request);
        animeToSave.setId(anime.getId());
        animeRepository.save(animeToSave);
    }
    public void remove(long id){
        animeRepository.delete(findById(id));
    }
}
