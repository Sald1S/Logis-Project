package kz.bitlab.logistic.service;

import kz.bitlab.logistic.constrants.RequestStatus;
import kz.bitlab.logistic.dto.*;
import kz.bitlab.logistic.mapper.*;
import kz.bitlab.logistic.model.*;
import kz.bitlab.logistic.repository.ManagerRepository;
import kz.bitlab.logistic.repository.PriceRepository;
import kz.bitlab.logistic.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private UserService userService;

    private final ReceiverService receiverService;

    private final AddresserService addresserService;

    private final ProductService productService;

    private final TransportService transportService;

    private final RequestMapper requestMapper;

    private final ManagerRepository managerRepository;

    private final AddresserMapper addresserMapper;

    private final ReceiverMapper receiverMapper;

    private final ProductMapper productMapper;

    private final ProductSizeMapper productSizeMapper;

    private final TransportMapper transportMapper;

    private final PriceMapper priceMapper;

    private final UserMapper userMapper;

    private final ManagerMapper managerMapper;

    public int count(){
        List<RequestDTO> requestDTOList = requestMapper.toDtoList(requestRepository.findAll());
        return requestDTOList.size();
    }

    public Price calculate(ProductSize productSize,Long typeId,Long transportId, Long distance){
    double volume = productSize.getHeight()* productSize.getWidth()*productSize.getLength();
    double price1 = 0;
    if(typeId == 1){//1 is Document
        if(transportId == 1){//1 is Ship
            price1 = ((volume * 300) + distance * 4.5) * productSize.getWeight() * 1;
        }
        if(transportId == 2){//2 is Plane
            price1 = ((volume * 300) + distance * 6)  * productSize.getWeight() * 1;
        }
        if(transportId == 3){//1 is Train
            price1 = ((volume * 300) + distance * 4)  * productSize.getWeight() * 1;
        }
    }
    if(typeId == 3){//3 is Tech
        if(transportId == 1){//1 is Ship
            price1 = ((volume * 500) + distance * 4.5) * productSize.getWeight() * 1;
        }
        if(transportId == 2){//2 is Plane
            price1 = ((volume * 500) + distance * 6)  * productSize.getWeight() * 1;
        }
        if(transportId == 3){//1 is Train
            price1 = ((volume * 500) + distance * 4)  * productSize.getWeight() * 1;
        }
    }
    if(typeId == 4){//4 is Other
        if(transportId == 1){//1 is Ship
            price1 = ((volume * 450) + distance * 4.5) * productSize.getWeight() * 1;
        }
        if(transportId == 2){//2 is Plane
            price1 = ((volume * 450) + distance * 6) * productSize.getWeight() * 1;
        }
        if(transportId == 3){//1 is Train
            price1 = ((volume * 450) + distance * 4) * productSize.getWeight() * 1;
        }
    }
        Price requestPrice = new Price();
        requestPrice.setRequestPrice(price1);
        return  priceRepository.save(requestPrice);
    }
    public PriceDTO calculateDto(ProductSizeDTO productSizeDto,Long typeId,Long transportId, Long distance){
        double volume = productSizeDto.getHeight()* productSizeDto.getWidth()*productSizeDto.getLength();
        double price1 = 0;
        if(typeId == 1){//1 is Document
            if(transportId == 1){//1 is Ship
                price1 = ((volume * 300) + distance * 4.5) * productSizeDto.getWeight() * 1;
            }
            if(transportId == 2){//2 is Plane
                price1 = ((volume * 300) + distance * 6)  * productSizeDto.getWeight() * 1;
            }
            if(transportId == 3){//1 is Train
                price1 = ((volume * 300) + distance * 4)  * productSizeDto.getWeight() * 1;
            }
        }
        if(typeId == 3){//3 is Tech
            if(transportId == 1){//1 is Ship
                price1 = ((volume * 500) + distance * 4.5) * productSizeDto.getWeight() * 1;
            }
            if(transportId == 2){//2 is Plane
                price1 = ((volume * 500) + distance * 6)  * productSizeDto.getWeight() * 1;
            }
            if(transportId == 3){//1 is Train
                price1 = ((volume * 500) + distance * 4)  * productSizeDto.getWeight() * 1;
            }
        }
        if(typeId == 4){//4 is Other
            if(transportId == 1){//1 is Ship
                price1 = ((volume * 450) + distance * 4.5) * productSizeDto.getWeight() * 1;
            }
            if(transportId == 2){//2 is Plane
                price1 = ((volume * 450) + distance * 6) * productSizeDto.getWeight() * 1;
            }
            if(transportId == 3){//1 is Train
                price1 = ((volume * 450) + distance * 4) * productSizeDto.getWeight() * 1;
            }
        }
        Price requestPrice = new Price();
        requestPrice.setRequestPrice(price1);
        return priceMapper.toDto((calculate(productSizeMapper.toEntity(productSizeDto),typeId,transportId,distance)));
    }

    public double calc(double height, double width, double length, double weight,Long typeId,Long transportId, Long distance) {
        double volume = height*width*length;
        double price1 = 0;
        if(typeId == 1){//1 is Document
            if(transportId == 1){//1 is Ship
                price1 = ((volume * 300) + distance * 4.5) * weight * 1;
            }
            if(transportId == 2){//2 is Plane
                price1 = ((volume * 300) + distance * 6)  * weight * 0.03;
            }
            if(transportId == 3){//1 is Train
                price1 = ((volume * 300) + distance * 4)  * weight * 0.03;
            }
        }
        if(typeId == 3){//3 is Tech
            if(transportId == 1){//1 is Ship
                price1 = ((volume * 500) + distance * 4.5) * weight * 0.03;
            }
            if(transportId == 2){//2 is Plane
                price1 = ((volume * 500) + distance * 6)  * weight * 0.03;
            }
            if(transportId == 3){//1 is Train
                price1 = ((volume * 500) + distance * 4)  * weight * 0.03;
            }
        }
        if(typeId == 4){//4 is Other
            if(transportId == 1){//1 is Ship
                price1 = ((volume * 450) + distance * 4.5) * weight * 0.03;
            }
            if(transportId == 2){//2 is Plane
                price1 = ((volume * 450) + distance * 6) * weight * 0.03;
            }
            if(transportId == 3){//1 is Train
                price1 = ((volume * 450) + distance * 4) * weight * 0.03;
            }
        }
        return price1;
    }

    public Request addRequest(Request request) {
        return requestRepository.save(request);
    }

    public RequestDTO addRequestDto(RequestDTO requestDTO,Long typeId,Long transportId, Long distance,
                                    AddresserDTO addresserDTO,
                                    ReceiverDTO receiverDTO,
                                    ProductDTO productDTO,
                                    ProductSizeDTO productSizeDTO){
        Request request = requestMapper.toEntity(requestDTO); 
        request.setAddresser(addresserMapper.toEntity(addresserService.addAddresserDto(addresserDTO)));
        request.setReceiver(receiverMapper.toEntity(receiverService.addReceiverDto(receiverDTO)));
        request.setProduct(productMapper.toEntity(productService.addProductDto(productDTO,typeId,productSizeDTO)));
        request.setTransport(transportService.getTransport(transportId));
        request.setUser(userMapper.toEntity(userService.getUserDto()));
        request.setPrice(priceMapper.toEntity(calculateDto(productSizeDTO,typeId,transportId,distance)));
        request.setRequestStatus(RequestStatus.INPROCESS);
        request = addRequest(request);
        return requestMapper.toDto(request);
    }

    public List<Request> getAllRequests() {
            User user = userService.getUser();
            return requestRepository.findAllByUser(user.getId());
    }

    public List<Request> getRequests() {
        return requestRepository.findAll();
    }

    public List<RequestDTO> getRequestsDto(){
        return requestMapper.toDtoList(getRequests());
    }

    public Request getRequest(Long id){
        return requestRepository.findById(id).orElse(null);
    }

    public RequestDTO getRequestDto(Long id) {
        return requestMapper.toDto(getRequest(id));
    }

    public Request updateRequest(Request request){
        return requestRepository.save(request);
    }

    public RequestDTO updateRequestDto(RequestDTO requestDTO, Long id){
        RequestDTO requestDTO1 = getRequestDto(id);
        Request request = requestMapper.toEntity(requestDTO1);
        if(requestDTO.getRequestStatus().equalsIgnoreCase("DELIVERED")){
            request.setRequestStatus(RequestStatus.DELIVERED);
        }
        if(requestDTO.getRequestStatus().equalsIgnoreCase("SENT")){
            request.setRequestStatus(RequestStatus.SENT);
        }
        if(requestDTO.getRequestStatus().equalsIgnoreCase("INPROCESS")){
            request.setRequestStatus(RequestStatus.INPROCESS);
        }
        request = updateRequest(request);
        return requestMapper.toDto(request);
    }

    public List<Manager> getManagers() {
        return managerRepository.findAll();
    }

    public List<ManagerDTO> getManagersDto(){
        return managerMapper.toDtoListOfManagers(getManagers());
    }

    public List<ManagerDTO> getManagersDto(Long id) {
        Request request = requestMapper.toEntity(getRequestDto(id));
        System.out.println(request.getManager());
        List<Manager> managerList = new ArrayList<>();
        if(request!=null) {
            managerList = managerMapper.toEntityListOfManagers(getManagersDto());
            System.out.println(managerList);
            managerList.removeAll(request.getManager());
            System.out.println(managerList);
        }
        return managerMapper.toDtoListOfManagers(managerList);
    }

    public void assignManager(Long reqId, Long managerId){
        RequestDTO requestDTO = requestMapper.toDto(getRequest(reqId));
        if(requestDTO!=null) {
            ManagerDTO managerDTO = managerMapper.toDto(managerRepository.findById(managerId).orElse(null));
            if(managerDTO!=null){
                List<ManagerDTO> managerDTOList = requestDTO.getManager();
                if(managerDTOList!=null) {
                    managerDTOList = new ArrayList<>();
                }
                managerDTOList.add(managerDTO);
                requestDTO.setManager(managerDTOList);
                requestRepository.save(requestMapper.toEntity(requestDTO));
            }
        }
    }

    public void unAssignManager(Long reqId, Long managerId) {
        RequestDTO requestDTO = requestMapper.toDto(getRequest(reqId));
        if(requestDTO != null) {
            ManagerDTO managerDTO = managerMapper.toDto(managerRepository.findById(managerId).orElse(null));
            if(managerDTO != null) {
                List<ManagerDTO> managerDTOList = requestDTO.getManager();
                if(managerDTOList!=null) {
                    managerDTOList = new ArrayList<>();
                }
                managerDTOList.remove(managerDTO);
                requestDTO.setManager(managerDTOList);
                requestRepository.save(requestMapper.toEntity(requestDTO));
            }
        }
    }
}


