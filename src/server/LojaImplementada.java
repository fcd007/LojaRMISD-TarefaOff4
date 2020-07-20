package server;

import java.util.Map;

import modelo.Alimento;
import modelo.Carrinho;
import modelo.EletroEletronico;
import modelo.ListProduto;
import modelo.Produto;
import modelo.Roupa;
import modelo.Usuario;

public class LojaImplementada implements LojaInterface{
	
	private Map<String, Usuario> usuarios;
	private ListProduto lista;
	private Carrinho carrinho;
	private int typeUserLogged;
	
	public LojaImplementada(Map<String, Usuario> usuarios, ListProduto lista) {
		
		this.usuarios = usuarios;
		this.lista = lista;
		this.carrinho = new Carrinho();
	}
	
	
	
	@Override
	public int login(String usuarioRec, String senhaRec) {
		
		
		for (Object key : usuarios.keySet()) { 
			
			if(key.toString().equalsIgnoreCase(usuarioRec)) {
				Usuario usuario = usuarios.get(usuarioRec);
				
				
				if(usuario.getSenha().equals(senhaRec)) {
					
					this.typeUserLogged = usuario.getPerfil();
					
					return this.typeUserLogged;
				} else {
					return -1;
				}
			}
				
		}
		
		
		return -2;
	}
	
	
	@Override
	public String funcioMenu(){
		
		
		String menu = "Entre com a opção desejada abaixo: " +
						"1- Adicionar produto\n" +
				    	"2- Apagar Produto\n" +
				    	"3- Listar Produtos\n" +
				    	"4- Pesquisar por nome\n" +
				    	"5- Pesquisar por codigo\n" +
				    	"6- Alterar Produto\n" +
				    	"7- Exibir Quantidade de produtos\n" +
				    	"8- Adicionar Produto ao Carrinho\n" +
				    	"9- Exibir Carrinho\n" +
				    	"10- Sair\n";
	
		return menu;
	}
	
	
	@Override
	public String menuUsuario(){
		
		
		String menu = 	"Entre com a opção desejada\n" +
				    	"1- Listar Produtos\n" +
				    	"2- Pesquisar por nome\n" +
				    	"3- Pesquisar por codigo\n" +
				    	"4- Exibir Quantidade de produtos\n" +
				    	"5- Adicionar Produto ao Carrinho\n" +
				    	"6- Exibir Carrinho\n" +
				    	"7- Sair\n" +
				    	">> ";
	
		return menu;
	}
	

	@Override
	public String adicionarProduto(String id, String nome, String preco, String tipo, String extra){
		
		if(!id.isEmpty() && !nome.isEmpty() && !preco.isEmpty() && !tipo.isEmpty() && !extra.isEmpty()) {
			
			Produto novo;
			
			if(tipo.equalsIgnoreCase("Alimento")) {
				novo = new Alimento(id, nome, preco, extra);
			} else if(tipo.equalsIgnoreCase("Eletronico")) {
				novo = new EletroEletronico(id, nome, preco, extra);
			} else {
				novo = new Roupa(id, nome, preco, extra);
			}
			
			this.lista.setItem(novo);
			
			return "Produto adicionado";
		}
		
		return "Todos os itens devem ser preenchidos";
	}
	
	@Override
	public String apagarProd(String nome){
		
		boolean resultado = this.lista.deleteFromList(nome);
    	String mensagem = "";
    	
    	if(resultado) {
    		mensagem = "Produto Deletado";
    	} else {
    		 mensagem = "O produto não existe.";
    	}
    	
    	return mensagem;
	}

	
	@Override
	public String listarProdutos(){
		return this.lista.getList();
	}

	
	@Override
	public String buscarNome(String nome){
		
		String cabecalho = "Id  Nome\t Tipo\t\t Valor\t Extra\n";
		Produto result = this.lista.getItem(nome);
    	
    	if(result != null)
    		return cabecalho + result.toString();
    	else
    		return "Produto não foi encontrado";
	}
	
	
	@Override
	public String buscarId(String id){
		
		String cabecalho = "Id  Nome\t Tipo\t\t Valor\t Extra\n";
		Produto result = this.lista.getItemByCod(id);
    	
    	if(result != null)
    		return cabecalho + result.toString();
    	else
    		return "Produto não foi encontrado";
	}

	
	@Override
	public String atualizar(String nomeAtual, String id, String nome, String preco, String tipo, String outra) {
		
		//verifica se temos uma opção vázia como entrada
		if(!nomeAtual.isEmpty() && !id.isEmpty() && !nome.isEmpty() && !preco.isEmpty() && !tipo.isEmpty() && !outra.isEmpty()) {
		
			Produto resultadoTipo = this.lista.getItem(nomeAtual);
			
			
			if(resultadoTipo != null) {
				
				resultadoTipo.setCodigo(id);
				resultadoTipo.setNome(nome);
				resultadoTipo.setPreco(preco);
				resultadoTipo.setTipo(tipo);
				
				if(resultadoTipo.getTipo().equalsIgnoreCase("Alimento")) {
					Alimento produto = (Alimento) resultadoTipo;
					produto.setKg(outra);
				} else if(resultadoTipo.getTipo().equalsIgnoreCase("EletroEletronico")){
					EletroEletronico produto = (EletroEletronico) resultadoTipo;
					produto.setVoltagem(outra);
				} else if(resultadoTipo.getTipo().equalsIgnoreCase("Roupa")) {
					Roupa produto = (Roupa) resultadoTipo;
					produto.setTamanho(outra);
				} else {
					return "Tipo desconhecido ou inválido";
				}
				
	    		return "Produto Atualizado\n" + resultadoTipo.toString();
			} else {
	    		return "Produto foi econtrado na base de dados";
			}
		}
		
		
		return "Todos os campos devem ser preenchidos";
	}
	
	
	@Override
	public String quantidade() {
		return "Disponível no momento:  " + this.lista.size() + " produtos.";
	}
	
	@Override
	public String addCarrinho(String nome) {
		
		Produto resultado = this.lista.getItem(nome);
    	String retorno = "Produto não existe na lista de itens";
    	
    	if(resultado != null) {
    		retorno = this.carrinho.addItem(resultado);
    		this.lista.delete(resultado.getNome());
    	}
		
		
		return retorno;
	}
	
	@Override
	public String exibirItensCarrinho() {
		return this.carrinho.getLista();
	}
	
	@Override
	public String removeCarrinho(String nome) {
		
		Produto removido = this.carrinho.removerItem(nome);
		
		if(removido != null) {
			this.lista.setItem(removido);
			
			return "Item removido do carrinho";
		}
		
		return "Item não encontrado no carrinho";
	}
	
	@Override
	public String finalizarCompra() {
		return this.carrinho.finalizarCompra();
	}
}
