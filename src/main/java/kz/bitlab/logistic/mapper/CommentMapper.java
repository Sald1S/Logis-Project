package kz.bitlab.logistic.mapper;

import kz.bitlab.logistic.dto.*;
import kz.bitlab.logistic.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE , componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentDTO commentDTO);
    CommentDTO toDto(Comment comment);

    List<Comment> toEntityListComments(List<CommentDTO> commentDTOList);
    List<CommentDTO> toDtoListComments(List<Comment> commentsList);

}
