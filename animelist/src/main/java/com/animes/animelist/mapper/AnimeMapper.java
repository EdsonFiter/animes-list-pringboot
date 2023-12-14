package com.animes.animelist.mapper;

import com.animes.animelist.domain.Anime;
import com.animes.animelist.request.AnimePostRequest;
import com.animes.animelist.request.AnimePutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public Anime toAnime(AnimePostRequest request){
        return Anime.builder()
                .name(request.getName())
                .episode(request.getEpisode())
                .creator(request.getCreator())
                .launchDate(request.getLaunchDate())
                .build();
    }
    public Anime toAnime(AnimePutRequest request){
        return Anime.builder()
                .id(request.getId())
                .name(request.getName())
                .episode(request.getEpisode())
                .creator(request.getCreator())
                .launchDate(request.getLaunchDate())
                .build();
    }
}
