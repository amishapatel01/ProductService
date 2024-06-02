package dev.amisha.productservice.services;

import com.fasterxml.jackson.annotation.JacksonInject;
import dev.amisha.productservice.dtos.FakeStoreProductDto;
import dev.amisha.productservice.models.Category;
import dev.amisha.productservice.models.Product;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private final RestOperations restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

@Override
    public Product getSingleProduct(Long id){
    // RestTemplate restTemplate = new RestTemplate();
    // FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
    //       "https://fakestoreapi.com/products/" + id,
    //              FakeS toreProductDto.class);

            ResponseEntity<FakeStoreProductDto> responseEntity =  restTemplate.getForEntity(
                    "https://fakestoreapi.com/products/" + id,
                                FakeStoreProductDto.class);

            FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

    Product product = new Product();
    product.setId(fakeStoreProductDto.getId());
    product.setTitle(fakeStoreProductDto.getTitle());
    product.setDescription(fakeStoreProductDto.getDescription());
    product.setImageUrl(fakeStoreProductDto.getImage());

    Category category = new Category();
    category.setTitle(fakeStoreProductDto.getCategory());
    product.setCategory(category);

    return product;
    }

    @Override
    public Product createProduct(String title, String description, String image,
                                 String category, double price) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setTitle(title);




        FakeStoreProductDto response = restTemplate
                .postForObject("https://fakestoreapi.com/products",
                        fakeStoreProductDto,
                        FakeStoreProductDto.class);

        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] response =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: response) {
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }
}
