package br.com.improving.carrinho;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoComprasTest {
    private CarrinhoCompras carrinho;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        carrinho = new CarrinhoCompras();
        produto1 = new Produto(1L, "Produto 1");
        produto2 = new Produto(2L, "Produto 2");
    }

    @Test
    void adicionarItem() {
        carrinho.adicionarItem(produto1, BigDecimal.valueOf(10.00), 2);
        carrinho.adicionarItem(produto1, BigDecimal.valueOf(10.00), 3);

        assertEquals(1, carrinho.getItens().size());
        assertEquals(5, carrinho.getItens().iterator().next().getQuantidade());
    }

    @Test
    void adicionarItemComValorDiferente() {
        carrinho.adicionarItem(produto1, BigDecimal.valueOf(10.00), 2);
        carrinho.adicionarItem(produto1, BigDecimal.valueOf(12.00), 3);

        Item item = carrinho.getItens().iterator().next();
        assertEquals(5, item.getQuantidade());
        assertEquals(BigDecimal.valueOf(12.00), item.getValorUnitario());
    }

    @Test
    void removerItemPorProduto() {
        carrinho.adicionarItem(produto1, BigDecimal.valueOf(10.00), 2);
        carrinho.adicionarItem(produto2, BigDecimal.valueOf(20.00), 1);

        assertTrue(carrinho.removerItem(produto1));
        assertFalse(carrinho.removerItem(produto1));
        assertEquals(1, carrinho.getItens().size());
    }

    @Test
    void removerItemPorPosicao() {
        carrinho.adicionarItem(produto1, BigDecimal.valueOf(10.00), 2);
        carrinho.adicionarItem(produto2, BigDecimal.valueOf(20.00), 1);

        assertTrue(carrinho.removerItem(0));
        assertFalse(carrinho.removerItem(1));
        assertEquals(1, carrinho.getItens().size());
    }

    @Test
    void getValorTotal() {
        carrinho.adicionarItem(produto1, BigDecimal.valueOf(10.00), 2);
        carrinho.adicionarItem(produto2, BigDecimal.valueOf(20.00), 1);

        assertEquals(BigDecimal.valueOf(40.00), carrinho.getValorTotal());
    }
}
