package kz.bitlab.logistic.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Addresser extends BaseEntity{

    private String Addresser_name;
    private String Addresser_address;
    private String Addresser_state;
    private String Addresser_city;
    private int Addresser_postalCode;
    private String Addresser_email;
    private String Addresser_phoneNumber;

}
