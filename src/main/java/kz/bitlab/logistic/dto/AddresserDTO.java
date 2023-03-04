package kz.bitlab.logistic.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddresserDTO {

    private Long id;
    private String Addresser_name;
    private String Addresser_address;
    private String Addresser_state;
    private String Addresser_city;
    private int Addresser_postalCode;
    private String Addresser_email;
    private String Addresser_phoneNumber;
}
