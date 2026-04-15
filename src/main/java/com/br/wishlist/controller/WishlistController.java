package com.br.wishlist.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.br.wishlist.dto.WishlistItemRequest;
import com.br.wishlist.dto.WishlistItemResponse;
import com.br.wishlist.service.WishlistService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService service;

    @GetMapping
    public Page<WishlistItemResponse> listar(@PageableDefault(size = 10, sort = {"title"}) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @GetMapping("/pendentes")
    public List<WishlistItemResponse> listarPendentes() {
        return service.listarNaoComprados();
    }

    @GetMapping("/economia")
    public List<WishlistItemResponse> filtrarPorPreco(@RequestParam Double max) {
        return service.listarBaratos(max);
    }

    @GetMapping("/{id}")
    public WishlistItemResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public WishlistItemResponse criar(@RequestBody @Valid WishlistItemRequest dados) {
        return service.salvar(dados);
    }

    @PutMapping("/{id}")
    public WishlistItemResponse atualizar(@PathVariable Long id, @RequestBody @Valid WishlistItemRequest dados) {
        return service.atualizar(id, dados);
    }

    @PatchMapping("/{id}/comprar")
    public WishlistItemResponse comprar(@PathVariable Long id) {
        return service.marcarComoComprado(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna status 204 
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}