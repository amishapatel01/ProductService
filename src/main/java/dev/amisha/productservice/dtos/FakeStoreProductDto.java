package dev.amisha.productservice.dtos;

import dev.amisha.productservice.models.Category;
import dev.amisha.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public Product toProduct() {
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setImageUrl(getImage());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);

        return product;
    }
}



