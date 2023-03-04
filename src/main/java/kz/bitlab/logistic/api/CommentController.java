package kz.bitlab.logistic.api;

import kz.bitlab.logistic.dto.CommentDTO;
import kz.bitlab.logistic.model.Comment;
import kz.bitlab.logistic.model.User;
import kz.bitlab.logistic.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public List<CommentDTO> getComments(@PathVariable("id") Long id){
        return commentService.getComments(id);
    }

    @PostMapping("{id}")
    public CommentDTO addComment(@RequestBody Comment comment,
                                 @PathVariable("id") Long id){
        return commentService.addComment(comment,id);
    }
}
