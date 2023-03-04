package kz.bitlab.logistic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import kz.bitlab.logistic.constrants.RequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Request extends BaseEntity {

    @OneToOne
    private Addresser addresser;
    @OneToOne
    private Receiver receiver;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Transport transport;
    @ManyToOne
    private User user;
    private RequestStatus requestStatus;
    @OneToOne
    private Price price;
    @ManyToMany
    private List<Manager> manager;

}
