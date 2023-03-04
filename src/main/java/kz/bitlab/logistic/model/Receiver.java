package kz.bitlab.logistic.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Receiver extends BaseEntity {

    private String Receiver_name;
    private String Receiver_address;
    private String Receiver_city;
    private int Receiver_postalCode;
    private String Receiver_email;
    private String Receiver_phoneNumber;
}
