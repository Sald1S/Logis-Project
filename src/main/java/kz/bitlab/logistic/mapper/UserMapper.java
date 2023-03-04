package kz.bitlab.logistic.mapper;

import kz.bitlab.logistic.dto.*;
import kz.bitlab.logistic.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE , componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);
    UserDTO toDto(User user);

    List<User> toDtoList(List<UserDTO> userDTOList);
    List<UserDTO> toEntityList(List<User> userList);
}
