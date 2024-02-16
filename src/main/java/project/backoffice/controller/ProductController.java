package project.backoffice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backoffice.dto.ProductDTO;
import project.backoffice.service.ProductService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws Exception {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}