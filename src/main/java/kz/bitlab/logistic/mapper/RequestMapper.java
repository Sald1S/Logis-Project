package kz.bitlab.logistic.mapper;

import kz.bitlab.logistic.dto.*;
import kz.bitlab.logistic.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE , componentModel = "spring")
public interface RequestMapper {

    Request toEntity(RequestDTO requestDTO);
    RequestDTO toDto(Request request);

    List<Request> toEntityList(List<RequestDTO> requestDTOList);
    List<RequestDTO> toDtoList(List<Request> requestList);
}
