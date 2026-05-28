package org.example.springecom.DTO;

import java.util.Date;

public record ProductRequest(
        Integer id,
        String name,
        String description,
        String brand,
        Integer price,
        String category,
        Date releaseDate,
        Boolean productAvailable,
        Integer productQuantity

) {
}
