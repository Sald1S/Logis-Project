package kz.bitlab.logistic.api;

import kz.bitlab.logistic.model.Price;
import kz.bitlab.logistic.model.ProductSize;
import kz.bitlab.logistic.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {

    private final RequestService requestService;

    @PostMapping(value="/{typeId}/{transportId}/{distance}")
    public double getPrice(@PathVariable(name = "typeId") Long typeId,
                           @PathVariable(name = "transportId") Long transportId,
                           @PathVariable(name = "distance") Long distance,
                           @RequestBody ProductSize productSize) {
        Price price = requestService.calculate(productSize,typeId,transportId,distance);
        return (int)price.getRequestPrice();
    }
}
