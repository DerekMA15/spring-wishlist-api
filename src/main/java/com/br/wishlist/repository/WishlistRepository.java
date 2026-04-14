package com.br.wishlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.wishlist.model.WishlistItem;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {
// 1. Query Method: O Spring entende o "FindByIsBought" e gera o SQL
    List<WishlistItem> findByIsBoughtFalse();

    // 2. @Query (JPQL): Para quando você quer ter controle total ou queries complexas
    @Query("SELECT w FROM WishlistItem w WHERE w.price <= :precoMaximo AND w.isBought = false")
    List<WishlistItem> buscarItensBaratosNaoComprados(Double precoMaximo);

}