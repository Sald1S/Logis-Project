package kz.bitlab.logistic.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;

    private String full_name;

    private String country;

    private String company;

    private String address;

    private String phoneNumber;
}
