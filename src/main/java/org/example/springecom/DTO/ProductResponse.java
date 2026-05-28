package org.example.springecom.DTO;

import java.util.Date;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        String brand,
        Integer price,
        String category,
        Date releaseDate,
        Boolean productAvailable,
        Integer productQuantity,
        String imageName,
        String imageType,
        String imageUrl
) {
}
