package dev.amisha.productservice.controller;

import dev.amisha.productservice.dtos.CreateProductRequestDto;
import dev.amisha.productservice.models.Product;
import dev.amisha.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController{
    ProductService productService;

    public ProductController(ProductService productService){

        this.productService = productService;
    }


    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto) {
        System.out.println("....request:"+ productRequestDto.getTitle());


        return productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){

       return productService.getSingleProduct(id);

    }
    public void deleteProduct(Long id){

    }
}