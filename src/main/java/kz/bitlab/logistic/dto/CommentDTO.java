package kz.bitlab.logistic.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {

    private Long id;
    private String comment;
    private LocalDateTime time;
    private UserDTO user;
}
