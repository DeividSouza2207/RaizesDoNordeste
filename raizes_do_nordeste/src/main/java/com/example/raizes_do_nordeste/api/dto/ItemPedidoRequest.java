package com.example.raizes_do_nordeste.api.dto;

import jakarta.validation.constraints.Positive;

public class ItemPedidoRequest {

    private Long produtoId;
    
    @Positive
    private Integer quantidade;

    public ItemPedidoRequest() {
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}