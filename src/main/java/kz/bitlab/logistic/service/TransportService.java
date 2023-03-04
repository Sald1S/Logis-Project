package kz.bitlab.logistic.service;

import kz.bitlab.logistic.model.Transport;
import kz.bitlab.logistic.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    public List<Transport> getTransports(){
        return transportRepository.findAll();
    }

    public Transport getTransport(Long id){
        return transportRepository.findById(id).orElse(null);
    }
}
