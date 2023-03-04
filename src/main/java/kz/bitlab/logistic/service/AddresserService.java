package kz.bitlab.logistic.service;

import kz.bitlab.logistic.dto.AddresserDTO;
import kz.bitlab.logistic.mapper.AddresserMapper;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.model.Addresser;
import kz.bitlab.logistic.repository.AddresserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddresserService {

    @Autowired
    private AddresserRepository addresserRepository;

    private final AddresserMapper addresserMapper;

    public Addresser addAddresser(Addresser addresser){
        return addresserRepository.save(addresser);
    }

    public AddresserDTO addAddresserDto(AddresserDTO addresserDTO){
        return addresserMapper.toDto(addAddresser(addresserMapper.toEntity(addresserDTO)));
    }
}
