package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 * 
 * As instâncias de CarrinhoComprasFactory são independentes entre si, ou seja, quando um carrinho 
 * para um cliente é criado em uma instância, a outra pode criar um novo carrinho para o mesmo 
 * cliente. Isso é verdade para todos os métodos.
 */
public class CarrinhoComprasFactory {
	private Map<String, CarrinhoCompras> carrinhos = new HashMap<>();

	public CarrinhoComprasFactory() {
	}

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
	public CarrinhoCompras criar(String identificacaoCliente) {
		return carrinhos.computeIfAbsent(identificacaoCliente, k -> new CarrinhoCompras());

	}

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
	public BigDecimal getValorTicketMedio() {

	    if (carrinhos.isEmpty()) {
	        return BigDecimal.ZERO;
	    }

	    BigDecimal somaTotal = carrinhos.values().stream()
	            .map(CarrinhoCompras::getValorTotal)
	            .reduce(BigDecimal.ZERO, BigDecimal::add);

	    BigDecimal ticketMedio = somaTotal.divide(BigDecimal.valueOf(carrinhos.size()), 2, RoundingMode.HALF_UP);
	    return ticketMedio.setScale(2, RoundingMode.HALF_UP);
	}

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
	public boolean invalidar(String identificacaoCliente) {
		return carrinhos.remove(identificacaoCliente) != null;

	}
}
