package kz.bitlab.logistic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSizeDTO {

    private Long id;
    private double height;
    private double width;
    private double length;
    private double weight;
}
