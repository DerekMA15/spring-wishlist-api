package com.br.wishlist.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record WishlistItemRequest(
    @NotBlank(message = "O título é obrigatório")
    String title,
    
    @Positive(message = "O preço deve ser maior que zero")
    Double price,
    
    String url
) {}