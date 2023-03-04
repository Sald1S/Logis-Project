package kz.bitlab.logistic.service;

import groovyjarjarasm.asm.commons.Remapper;
import kz.bitlab.logistic.dto.ReceiverDTO;
import kz.bitlab.logistic.mapper.ReceiverMapper;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.model.Receiver;
import kz.bitlab.logistic.repository.ReceiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiverService {

    @Autowired
    private ReceiverRepository receiverRepository;

    private final RequestMapper requestMapper;

    private final ReceiverMapper receiverMapper;

    public Receiver addReceiver(Receiver receiver){
        return receiverRepository.save(receiver);
    }

    public ReceiverDTO addReceiverDto(ReceiverDTO receiverDTO){
        return receiverMapper.toDto(addReceiver(receiverMapper.toEntity(receiverDTO)));
    }
}
