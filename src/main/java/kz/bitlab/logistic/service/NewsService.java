package kz.bitlab.logistic.service;

import kz.bitlab.logistic.dto.NewsDTO;
import kz.bitlab.logistic.mapper.NewsMapper;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.model.News;
import kz.bitlab.logistic.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    private final RequestMapper requestMapper;

    private final NewsMapper newsMapper;

    public List<NewsDTO> getNews(){
        return newsMapper.toDtoListNews(newsRepository.findAll());
    }

    public NewsDTO getOneNews(Long id){
        return newsMapper.toDto(newsRepository.findById(id).orElse(null));
    }

    public NewsDTO addNews(News news){
        return newsMapper.toDto(newsRepository.save(news));
    }

}
