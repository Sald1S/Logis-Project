package kz.bitlab.logistic.api;

import kz.bitlab.logistic.dto.ManagerDTO;
import kz.bitlab.logistic.dto.RequestDTO;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.model.Manager;
import kz.bitlab.logistic.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final RequestService requestService;
    private final RequestMapper requestMapper;

    @GetMapping("/{id}")
    public List<ManagerDTO> getManagers(@PathVariable(name = "id") Long id){
        return requestService.getManagersDto(id);
    }

    @PostMapping("{reqId}/{managerId}")
    public void assignManager(@PathVariable(name = "reqId") Long reqId,
                              @PathVariable(name = "managerId") Long managerId){
        requestService.assignManager(reqId,managerId);
    }
}
