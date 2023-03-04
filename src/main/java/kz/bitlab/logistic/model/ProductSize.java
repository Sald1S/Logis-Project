package kz.bitlab.logistic.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductSize extends BaseEntity {

    private double height;
    private double width;
    private double length;
    private double weight;
}
