package com.br.wishlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.wishlist.dto.WishlistItemRequest;
import com.br.wishlist.dto.WishlistItemResponse;
import com.br.wishlist.model.WishlistItem;
import com.br.wishlist.repository.WishlistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository repository;

public Page<WishlistItemResponse> listarTodos(Pageable paginacao) {
    return repository.findAll(paginacao) // agora o return vai vim paginado e dividido em partes
            .map(WishlistItemResponse::new);
}
    public WishlistItemResponse buscarPorId(Long id) {
    return repository.findById(id)
            .map(item -> new WishlistItemResponse(item))
            .orElseThrow(EntityNotFoundException::new);
    }

    public WishlistItemResponse salvar(WishlistItemRequest dados) {
        WishlistItem novoItem = new WishlistItem();
        novoItem.setTitle(dados.title());
        novoItem.setPrice(dados.price());
        novoItem.setUrl(dados.url());
        novoItem.setIsBought(false);

        repository.save(novoItem);
        
        return new WishlistItemResponse(novoItem);
    }

    public WishlistItemResponse atualizar(Long id, WishlistItemRequest dados) {
        // 1. Busca o item ou lança erro se não existir
        WishlistItem itemExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        // 2. Atualiza apenas os campos permitidos
        itemExistente.setTitle(dados.title());
        itemExistente.setPrice(dados.price());
        itemExistente.setUrl(dados.url());

        // 3. Salva a versão atualizada
        repository.save(itemExistente);

        return new WishlistItemResponse(itemExistente);
    }

    public void deletar(Long id) {
        // Verifica se o ID existe antes de deletar
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Item com ID " + id + " não encontrado!");
        }
        repository.deleteById(id);
    }

    public WishlistItemResponse marcarComoComprado(Long id) {
        WishlistItem item = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        // Inverte o status atual
        item.setIsBought(true); 
        
        repository.save(item);
        return new WishlistItemResponse(item);
    }

    public List<WishlistItemResponse> listarNaoComprados() {
        return repository.findByIsBoughtFalse()
                .stream()
                .map(WishlistItemResponse::new)
                .toList();
    }

    public List<WishlistItemResponse> listarBaratos(Double preco) {
        return repository.buscarItensBaratosNaoComprados(preco)
                .stream()
                .map(WishlistItemResponse::new)
                .toList();
    }
}