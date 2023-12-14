package com.animes.animelist.controller;

import com.animes.animelist.domain.Anime;
import com.animes.animelist.request.AnimePostRequest;
import com.animes.animelist.request.AnimePutRequest;
import com.animes.animelist.service.AnimeService;
import com.animes.animelist.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
@Log4j2
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Page<Anime>> listAll(Pageable pageable){
        log.info(dateUtil.formatDateToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/find-by-id/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        log.info(dateUtil.formatDateToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
        log.info(dateUtil.formatDateToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/findByLaunchDate")
    public ResponseEntity<List<Anime>> findByLaunchDate(@RequestParam String launchDate){
        log.info(dateUtil.formatDateToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.findByLaunchDate(launchDate), HttpStatus.OK);
    }
    @GetMapping("/findByCreator")
    public ResponseEntity<List<Anime>> findByCreator(@RequestParam String creator){
        log.info(dateUtil.formatDateToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.findByCreator(creator), HttpStatus.OK);
    }

    @PostMapping("/registry")
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequest request){
        log.info(dateUtil.formatDateToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> replace(@RequestBody @Valid AnimePutRequest request){
        log.info(dateUtil.formatDateToDatabaseStyle(LocalDateTime.now()));
        animeService.replace(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id){
        log.info(dateUtil.formatDateToDatabaseStyle(LocalDateTime.now()));
        animeService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
