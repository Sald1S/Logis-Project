package kz.bitlab.logistic.service;

import kz.bitlab.logistic.dto.NewsDTO;
import kz.bitlab.logistic.mapper.NewsMapper;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.model.News;
import kz.bitlab.logistic.model.User;
import kz.bitlab.logistic.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final UserService userService;

    private final NewsService newsService;

    private final RequestMapper requestMapper;

    private final NewsMapper newsMapper;

    private final NewsRepository newsRepository;

    public void uploadPicToNews(MultipartFile file){
        try {
            NewsDTO newsDTO = newsService.getOneNews(newsRepository.findLast().getId());
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
                byte bytes[] = file.getBytes();
                String filename = DigestUtils.sha1Hex("NewsPhoto_"+newsDTO.getId())+".jpg";
                Path path = Paths.get("build/resources/main/static/newsPhoto/"+filename);
                Files.write(path,bytes);
                newsDTO.setImage(filename);
                newsService.addNews(newsMapper.toEntity(newsDTO));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePicture(MultipartFile file,Long id){
        try {
            NewsDTO newsDTO = newsService.getOneNews(id);
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
                byte bytes[] = file.getBytes();
                String filename = DigestUtils.sha1Hex("NewsPhoto_"+newsDTO.getId())+".jpg";
                Path path = Paths.get("build/resources/main/static/newsPhoto/"+filename);
                Files.write(path,bytes);
                newsDTO.setImage(filename);
                newsService.addNews(newsMapper.toEntity(newsDTO));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean uploadPic(MultipartFile file){
        try {
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
                byte bytes[] = file.getBytes();
                String filename = DigestUtils.sha1Hex("Avatar_"+userService.getUser().getId())+".jpg";
                Path path = Paths.get("build/resources/main/static/profile/"+filename);
                Files.write(path,bytes);

                User currentUser = userService.getUser();
                currentUser.setPicture(filename);
                userService.updatePic(currentUser);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
