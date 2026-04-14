package com.br.wishlist.dto;

import java.time.LocalDateTime;

import com.br.wishlist.model.WishlistItem;

public record WishlistItemResponse(
    Long id,
    String title,
    Double price,
    String url,
    Boolean isBought, 
    LocalDateTime dataCriacao
) {
    // Um construtor de conveniência para converter Entity -> DTO facilmente
    public WishlistItemResponse(WishlistItem item) {
        this(item.getId(), 
        item.getTitle(), 
        item.getPrice(), 
        item.getUrl(), 
        item.getIsBought(),
        item.getDataCriacao());
    }
}