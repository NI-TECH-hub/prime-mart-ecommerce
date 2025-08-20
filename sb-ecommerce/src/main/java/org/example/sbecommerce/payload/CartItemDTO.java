package org.example.sbecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sbecommerce.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long cartItemId;
    private CartDTO cart;
    private Product productDTO;
    private Integer quantity;
    private Double discount;
    private Double productPrice;

}
