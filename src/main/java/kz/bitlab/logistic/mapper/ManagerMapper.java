package kz.bitlab.logistic.mapper;

import kz.bitlab.logistic.dto.*;
import kz.bitlab.logistic.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE , componentModel = "spring")
public interface ManagerMapper {

    Manager toEntity(ManagerDTO managerDTO);
    ManagerDTO toDto(Manager manager);

    List<Manager> toEntityListOfManagers(List<ManagerDTO> managerDTOList);
    List<ManagerDTO> toDtoListOfManagers(List<Manager> managerList);
}
