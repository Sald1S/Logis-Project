package kz.bitlab.logistic.dto;

import kz.bitlab.logistic.model.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private ProductTypeDTO type;
    private ProductSizeDTO size;
}
