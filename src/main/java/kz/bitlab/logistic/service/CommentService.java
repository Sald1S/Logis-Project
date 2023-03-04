package kz.bitlab.logistic.service;

import kz.bitlab.logistic.dto.CommentDTO;
import kz.bitlab.logistic.mapper.CommentMapper;
import kz.bitlab.logistic.mapper.NewsMapper;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.model.Comment;
import kz.bitlab.logistic.model.News;
import kz.bitlab.logistic.model.User;
import kz.bitlab.logistic.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final RequestMapper requestMapper;

    private final UserService userService;

    private final NewsService newsService;

    private final CommentMapper commentMapper;

    private final NewsMapper newsMapper;

    public List<CommentDTO> getComments(Long id){
        return commentMapper.toDtoListComments(commentRepository.findAllByNews(id));
    }

    public CommentDTO addComment(Comment comment,Long newsId){
        News news = newsMapper.toEntity(newsService.getOneNews(newsId));
        comment.setNews(news);
        comment.setUser(userService.getUser());
        comment.setTime(LocalDateTime.now());
        return commentMapper.toDto(commentRepository.save(comment));
    }
}
