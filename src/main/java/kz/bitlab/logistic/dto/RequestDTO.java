package kz.bitlab.logistic.dto;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import kz.bitlab.logistic.constrants.RequestStatus;
import kz.bitlab.logistic.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDTO {

    private Long id;
    private AddresserDTO addresser;
    private ReceiverDTO receiver;
    private ProductDTO product;
    private TransportDTO transport;
    private UserDTO user;
    private String requestStatus;
    private PriceDTO price;
    private List<ManagerDTO> manager;
}
