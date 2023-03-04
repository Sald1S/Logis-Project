package kz.bitlab.logistic.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseEntity{

    private String name;
    private String description;
    @OneToOne
    private ProductType type;
    @OneToOne
    private ProductSize size;

}
