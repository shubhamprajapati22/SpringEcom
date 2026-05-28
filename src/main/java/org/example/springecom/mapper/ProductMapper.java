package org.example.springecom.mapper;

import org.example.springecom.DTO.ProductRequest;
import org.example.springecom.DTO.ProductResponse;
import org.example.springecom.model.Product;


public class ProductMapper {

    public static ProductResponse toResDTO(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getCategory(),
                product.getReleaseDate(),
                product.getProductAvailable(),
                product.getProductQuantity(),
                product.getImageName(),
                product.getImageType(),
                product.getImageUrl()
        );

    }
    public static ProductRequest toReqDTO(Product product) {
        return new ProductRequest(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getCategory(),
                product.getReleaseDate(),
                product.getProductAvailable(),
                product.getProductQuantity()

        );

    }
}
