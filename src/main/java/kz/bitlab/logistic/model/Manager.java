package kz.bitlab.logistic.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manager extends BaseEntity{

    private String fullName;
}
