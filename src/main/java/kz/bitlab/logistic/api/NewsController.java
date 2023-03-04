package kz.bitlab.logistic.api;

import kz.bitlab.logistic.dto.NewsDTO;
import kz.bitlab.logistic.mapper.NewsMapper;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.model.News;
import kz.bitlab.logistic.service.FileUploadService;
import kz.bitlab.logistic.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final FileUploadService fileUploadService;

    private final NewsMapper newsMapper;

    @GetMapping
    public List<NewsDTO> getNews(){
        return newsService.getNews();
    }

    @GetMapping(value = "{id}")
    public NewsDTO getNews(@PathVariable(name = "id") Long id){
        return newsService.getOneNews(id);
    }

    @PutMapping
    public void uploadPicture(@RequestParam(name = "file")MultipartFile file){
        fileUploadService.uploadPicToNews(file);
    }

    @PostMapping
    public NewsDTO addNews(@RequestBody NewsDTO newsDTO){
        return newsService.addNews(newsMapper.toEntity(newsDTO));
    }

    @PutMapping("{id}")
    public void updatePicture(@PathVariable("id") Long id,
                              @RequestParam(name = "file")MultipartFile file){
         fileUploadService.updatePicture(file, id);
    }
}
