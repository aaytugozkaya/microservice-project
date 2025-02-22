package com.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product mapToProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .availableQuantity(productRequest.availableQuantity())
                .price(productRequest.price())
                .category(Category.builder().id(productRequest.categoryId()).build())
                .build();
    }

    public ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse mapToProductPurchaseResponse(Product storedProduct,  Double quantity) {
    return  new ProductPurchaseResponse(
            storedProduct.getId(),
            storedProduct.getName(),
            storedProduct.getDescription(),
            storedProduct.getPrice(),
            quantity
    );
    }
}
