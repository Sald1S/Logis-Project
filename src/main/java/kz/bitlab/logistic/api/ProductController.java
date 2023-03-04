package kz.bitlab.logistic.api;

import kz.bitlab.logistic.dto.ProductDTO;
import kz.bitlab.logistic.dto.ProductSizeDTO;
import kz.bitlab.logistic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/{id}")
    public ProductDTO addProductDto(@RequestBody ProductDTO productDTO,
                                    @PathVariable(name = "id") Long id){
        return productService.addProductDto(productDTO,id, productDTO.getSize());
    }
}
