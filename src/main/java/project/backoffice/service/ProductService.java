package project.backoffice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.backoffice.dto.ProductDTO;
import project.backoffice.entity.Product;
import project.backoffice.mapper.FirmwareMapper;
import project.backoffice.mapper.ProductMapper;
import project.backoffice.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private FirmwareMapper firmwareMapper;

    public List<ProductDTO> getAllProducts() throws JsonProcessingException {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> list =  products.stream()
                .map(product -> productMapper.toDTO(product))
                .collect(Collectors.toList());
        return list;
    }
}

