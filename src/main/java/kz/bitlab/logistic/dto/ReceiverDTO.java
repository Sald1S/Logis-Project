package kz.bitlab.logistic.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiverDTO {

    private Long id;
    private String Receiver_name;
    private String Receiver_address;
    private String Receiver_city;
    private int Receiver_postalCode;
    private String Receiver_email;
    private String Receiver_phoneNumber;
}
