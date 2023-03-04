package kz.bitlab.logistic.api;

import kz.bitlab.logistic.dto.RequestDTO;
import kz.bitlab.logistic.model.*;
import kz.bitlab.logistic.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {


    private final ReceiverService receiverService;

    private final AddresserService addresserService;

    private final RequestService requestService;

    private final ProductService productService;

    private final TransportService transportService;

    @GetMapping
    public List<RequestDTO> getRequests(){
        return requestService.getRequestsDto();
    }

    @PostMapping(value="/{typeId}/{transportId}/{distance}")
    public RequestDTO addRequest(@RequestBody RequestDTO requestDTO,
                                 @PathVariable(name = "typeId") Long typeId,
                                 @PathVariable(name = "transportId") Long transportId,
                                 @PathVariable(name = "distance") Long distance) {
        return requestService.addRequestDto(requestDTO,typeId,transportId,distance,requestDTO.getAddresser(),requestDTO.getReceiver(),requestDTO.getProduct(),requestDTO.getProduct().getSize());
    }

    @GetMapping(value = "/{id}")
    public RequestDTO getRequest(@PathVariable(name = "id") Long id){
        return requestService.getRequestDto(id);
    }



    @PutMapping(value = "{id}")
    public RequestDTO editRequest(@RequestBody RequestDTO requestDTO,
                                  @PathVariable(name = "id") Long id){
        return requestService.updateRequestDto(requestDTO,id);
    }
}
