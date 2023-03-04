package kz.bitlab.logistic.mapper;

import kz.bitlab.logistic.dto.*;
import kz.bitlab.logistic.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE , componentModel = "spring")
public interface ProductTypeMapper {

    ProductType toEntity(ProductTypeDTO productTypeDTO);
    ProductTypeDTO toDto(ProductType productType);
}
