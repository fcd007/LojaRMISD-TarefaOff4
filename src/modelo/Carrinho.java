package modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Carrinho {

	private Map<String, Produto> carrinho;
	
	public Carrinho() {
		carrinho = new HashMap<>();
	}
	
	public String addItem(Produto novo) {
		
		this.carrinho.put(novo.getNome().toLowerCase(), novo);
		
		return "Item adicionado ao carrinho";
	}
	
	public Produto removerItem(String nome) {
		
		if(checkExist(nome)) {
			
			Produto removido = this.carrinho.get(nome);
			this.carrinho.remove(nome.toLowerCase());
			
			return removido;
			
		} else {
			return null;
		}
		
	}
	
	public String getLista() {
		
		String listString = "Produtos no carrinho\n";
		listString += "\nId  Nome\t Tipo\t\t Valor\t Extra\n";
		
		SortedSet<String> keys = new TreeSet<>(carrinho.keySet());
		
		if(keys.size() > 0) {
			for (Object key : keys) { 
				Produto objeto = carrinho.get(key); 
				listString += objeto.toString() + "\n";
			}
			
			listString += "\nEntre com uma das opções abaixo \n"
					+ "Finalizar compra (Y)"
					+ "Continuar comprando (N) "
					+ "Remover produto do carrinho (R)?";
		} else {
			listString += "Não há produtos no carrinho, "
					+ "pressione (N) para continuar";
		}
		
		return listString;
	}
	
	public String finalizarCompra() {
		
		this.carrinho.clear();
		return "Compra finalizada";
		
	}
	
	public boolean checkExist(String nome) {
		
		boolean exist = false;
		
		for (Object key : carrinho.keySet()) { 
			
			if(key.toString().equalsIgnoreCase(nome))
				exist = true;
			
		}
		
		return exist;
	}
	
}
