package com.ecommerce.product;

import com.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest request) {
        var product = productMapper.mapToProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests) {
        var productIds = requests.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (storedProducts.size() != productIds.size()) {
            throw new ProductPurchaseException("One or more products not found");
        }
        var storedRequest = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var storedProduct = storedProducts.get(i);
            var request = storedRequest.get(i);
            if (storedProduct.getAvailableQuantity() < request.quantity()) {
                throw new ProductPurchaseException("Not enough quantity for product with id: " + storedProduct.getId());
            }
            storedProduct.setAvailableQuantity(storedProduct.getAvailableQuantity() - request.quantity());
            productRepository.save(storedProduct);
            purchasedProducts.add(productMapper.mapToProductPurchaseResponse(storedProduct, request.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse getProduct(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::mapToProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToProductResponse)
                .toList();
    }
}
