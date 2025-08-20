package org.example.sbecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.sbecommerce.model.Cart;
import org.example.sbecommerce.payload.CartDTO;
import org.example.sbecommerce.service.CartRepository;
import org.example.sbecommerce.service.CartService;
import org.example.sbecommerce.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Cart APIs", description = "APIs for managing carts and cart items")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthUtil authUtil;

    @Operation(summary = "Add product to cart", description = "Add a product to the logged-in user's cart with specified quantity")
    @ApiResponse(responseCode = "201", description = "Product added to cart")
    @ApiResponse(responseCode = "400", description = "Invalid product or quantity")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long productId,
                                                    @PathVariable Integer quantity) {
        CartDTO cartDTO = cartService.addProductToCart(productId, quantity);
        return new ResponseEntity<>(cartDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all carts", description = "Retrieve all carts (typically admin use)")
    @ApiResponse(responseCode = "200", description = "Carts retrieved successfully")
    @GetMapping("/carts")
    public ResponseEntity<List<CartDTO>> getCarts() {
        List<CartDTO> cartDTOS = cartService.getAllCarts();
        return new ResponseEntity<>(cartDTOS, HttpStatus.OK);
    }

    @Operation(summary = "Get current user's cart", description = "Retrieve the cart for the logged-in user")
    @ApiResponse(responseCode = "200", description = "Cart retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Cart not found for user")
    @GetMapping("/carts/users/cart")
    public ResponseEntity<CartDTO> getCardId() {
        String emailId = authUtil.loggedInEmail();
        Cart cart = cartRepository.findCartByEmail(emailId);
        Long cartId = cart.getCartId();
        CartDTO cartDTO = cartService.getCart(emailId, cartId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @Operation(summary = "Update cart item quantity", description = "Increment or decrement quantity of a product in the cart; use 'delete' to decrement")
    @ApiResponse(responseCode = "200", description = "Cart updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid operation or product")
    @ApiResponse(responseCode = "404", description = "Cart or product not found")
    @PutMapping("/cart/products/{productId}/quantity/{operation}")
    public ResponseEntity<CartDTO> updateCartProduct(@PathVariable Long productId,
                                                     @PathVariable String operation) {
        CartDTO cartDTO = cartService.updateProductQuantityInCart(
                productId,
                operation.equalsIgnoreCase("delete") ? -1 : 1
        );
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @Operation(summary = "Remove product from cart", description = "Delete a specific product from a cart by cartId and productId")
    @ApiResponse(responseCode = "200", description = "Product removed from cart")
    @ApiResponse(responseCode = "404", description = "Cart or product not found")
    @DeleteMapping("/carts/{cartId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId,
                                                        @PathVariable Long productId) {
        String status = cartService.deleteProductFromCart(cartId, productId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}