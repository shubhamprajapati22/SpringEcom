package org.example.springecom.DTO;

import java.util.Collections;
import java.util.List;

public record UsersResponse(Integer id,
                            String name,
                            String email,
                            List<Integer> OrderIds,
                            Integer cartId,
                            String role) {
    public UsersResponse {
        OrderIds = OrderIds == null ? Collections.emptyList() : OrderIds;
    }
}
