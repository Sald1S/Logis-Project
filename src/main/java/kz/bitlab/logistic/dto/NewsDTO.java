package kz.bitlab.logistic.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import kz.bitlab.logistic.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsDTO {

    private String id;
    private String title;
    private String topic;
    private String content;
    private String image;
}
