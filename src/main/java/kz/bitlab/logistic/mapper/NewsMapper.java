package kz.bitlab.logistic.mapper;

import kz.bitlab.logistic.dto.*;
import kz.bitlab.logistic.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE , componentModel = "spring")
public interface NewsMapper {

    News toEntity(NewsDTO newsDTO);
    NewsDTO toDto(News news);

    List<News> toEntityListNews(List<NewsDTO> newsDTOList);
    List<NewsDTO> toDtoListNews(List<News> newsList);
}
