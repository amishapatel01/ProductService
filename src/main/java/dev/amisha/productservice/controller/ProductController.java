package dev.amisha.productservice.controller;

import dev.amisha.productservice.dtos.CreateProductRequestDto;
import dev.amisha.productservice.dtos.ErrorDto;
import dev.amisha.productservice.models.Product;
import dev.amisha.productservice.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> responseDate = productService.getAllProducts();

        List<Product> responseData = null;
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(
                null,HttpStatusCode.valueOf(202));
        return responseEntity;
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){

       return productService.getSingleProduct(id);

    }
    public void deleteProduct(Long id){

    }
//        @ExceptionHandler(NullPointerException.class)
//        public ResponseEntity<ErrorDto> handleNullPointerException() {
//            ErrorDto errorDto = new ErrorDto();
//            errorDto.setMessage("Something went wrong.Please try after sometime");
//
//            return new ResponseEntity<>(errorDto,
//                    HttpStatusCode.valueOf(404));
//        }
}