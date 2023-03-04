package kz.bitlab.logistic.service;


import kz.bitlab.logistic.dto.ProductDTO;
import kz.bitlab.logistic.dto.ProductSizeDTO;
import kz.bitlab.logistic.mapper.ProductMapper;
import kz.bitlab.logistic.mapper.ProductSizeMapper;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.model.Product;
import kz.bitlab.logistic.model.ProductSize;
import kz.bitlab.logistic.model.ProductType;
import kz.bitlab.logistic.repository.ProductRepository;
import kz.bitlab.logistic.repository.ProductSizeRepository;
import kz.bitlab.logistic.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    private final ProductSizeRepository productSizeRepository;

    private final ProductTypeRepository productTypeRepository;

    private final RequestMapper requestMapper;

    private final ProductSizeMapper productSizeMapper;

    private final ProductMapper productMapper;

    public ProductType getType(Long id){
        return productTypeRepository.findById(id).orElse(null);
    }

    public ProductSize addSize(ProductSize productSize) {
        return productSizeRepository.save(productSize);
    }

    public ProductSizeDTO addSizeDto(ProductSizeDTO productSizeDTO) {
        return productSizeMapper.toDto(addSize(productSizeMapper.toEntity(productSizeDTO)));
    }

    public List<ProductType> getAllType(){
        return productTypeRepository.findAll();
    }

//    public Product addProduct(Product product, Long id, ProductSize productSize) {
//        product.setSize(addSize(productSize));
//        product.setType(getType(id));
//        productRepository.save(product);
//        return product;
//    }
    public Product addProduct(Product product) {
       return productRepository.save(product);

    }

    public ProductDTO addProductDto(ProductDTO productDTO, Long id, ProductSizeDTO productSizeDTO) {
        Product product = productMapper.toEntity(productDTO);
        product.setSize(productSizeMapper.toEntity(addSizeDto(productSizeDTO)));
        product.setType(getType(id));
        product = addProduct(product);
        return productMapper.toDto(product);
    }
}
