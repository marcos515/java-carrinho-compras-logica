package br.com.improving.carrinho;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoComprasFactoryTest {
    private CarrinhoComprasFactory factory;

    @BeforeEach
    void setUp() {
        factory = new CarrinhoComprasFactory();
    }

    @Test
    void criarNovoCarrinho() {
        CarrinhoCompras carrinho1 = factory.criar("cliente1");
        assertNotNull(carrinho1);

        CarrinhoCompras carrinho2 = factory.criar("cliente1");
        assertSame(carrinho1, carrinho2);
    }

    @Test
    void getValorTicketMedio() {
        CarrinhoCompras carrinho1 = factory.criar("cliente1");
        CarrinhoCompras carrinho2 = factory.criar("cliente2");

        carrinho1.adicionarItem(new Produto(1L, "Produto 1"), BigDecimal.valueOf(15.39), 2);
        carrinho2.adicionarItem(new Produto(2L, "Produto 2"), BigDecimal.valueOf(21.99), 1);

        assertEquals(BigDecimal.valueOf(26.39).setScale(2, BigDecimal.ROUND_HALF_UP), factory.getValorTicketMedio());
    }

    @Test
    void invalidarCarrinho() {
        factory.criar("cliente1");

        assertTrue(factory.invalidar("cliente1"));
        assertFalse(factory.invalidar("cliente1"));
    }
}
