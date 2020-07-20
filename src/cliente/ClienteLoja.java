package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import server.LojaInterface;


public class ClienteLoja {
	
	// perfil de usuarios
	public static final int FUNCIONARIO = 0;
	public static final int CLIENTE = 1;
	public static final int SERVIDOR_PORTA = 34001;

	private ClienteLoja() {}

    public static void main(String[] args) {
    	
    	boolean exit = false;
    	boolean exitMenu = false;
    	
    	// politícia de segurança para o RMI
    	System.setProperty("java.security.policy", "java.policy");
		
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    	
    	Scanner entrada = new Scanner(System.in);
    	
    	System.out.println("Informe o nome/endereco do servidor:");
        String host = entrada.nextLine();
        
        try {
        	
        	
        	Registry registro = LocateRegistry.getRegistry(host, SERVIDOR_PORTA);
            LojaInterface stubObjRemotoCliente = (LojaInterface) registro.lookup("Loja");
            
            
            do {
            	
	            System.out.println("Login:");
		        String usuario = entrada.nextLine();
		        System.out.println("Senha:");
		        String senha = entrada.nextLine();
	            
		        int resultado = stubObjRemotoCliente.login(usuario, senha);
		        
		        if(resultado == FUNCIONARIO) {
		        	
		        	System.out.println("Funcionário logado..!");
		        	String opcao = "";
		        	do {
		        		String menu = stubObjRemotoCliente.funcioMenu();
			        	System.out.println(menu);
			        	opcao = entrada.nextLine();
			        	
			        	try {
			        		
				        	int selecaoOpcao = Integer.parseInt(opcao);
				        	
				        	switch(selecaoOpcao) {
				        		// Adicionar um novo produto produto
					        	case 1:{
					        		System.out.print("Entre com Id do produto:");
					        		String id = entrada.nextLine();
					        		
					        		System.out.print("Entre com o nome do produto:");
					        		String nome = entrada.nextLine();
					        		
					        		System.out.print("Entre com valor do produto:");
					        		String preco = entrada.nextLine();
					        		
					        		String tipo = "";
					        		String extra = "";
					        		
				        		do {
				        			
				        			System.out.print("Qual o tipo do produto: Alimento, EletroEtronico ou Roupa: ");
					        		tipo = entrada.nextLine();
					        		extra = "";
				        			
					        		if(tipo.equalsIgnoreCase("Alimento")) {
					        			
					        			System.out.print("Digite o Kg do produto:");
						        		extra = entrada.nextLine();
						        		break;
					        			
					        		} else if(tipo.equalsIgnoreCase("EletroEletronico")) {
					        			
					        			System.out.print("Digite voltagem 110V ou 2020V do produto:");
						        		extra = entrada.nextLine();
						        		break;
					        			
					        		} else if(tipo.equalsIgnoreCase("Roupa")) {
					        			
					        			System.out.print("Digite o tamanho do produto:");
						        		extra = entrada.nextLine();
						        		break;
					        			
					        		} else {
					        			System.out.println("Tipo não é invalido");
					        		}
					        		
				        		} while (true);
					        		
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.adicionarProduto(id, nome, preco, tipo, extra));
					        		System.out.println("");
					        		
					        		
					        	}
					        		break;
					        		
					        	// Apagar um produto da lista
					        	case 2:{
					        		System.out.print("Digite o nome do produto que deseja apagar:");
					        		String nome = entrada.nextLine();
					        		System.out.println("");
					        		System.out.println(" "+stubObjRemotoCliente.apagarProd(nome));
					        		System.out.println("");
					        	}
					        		break;
					        		
					        	// Listar todos os produtos
					        	case 3:{
					        		System.out.println(" ");
					        		System.out.println(stubObjRemotoCliente.listarProdutos());
					        		System.out.println(" ");
					        	}
					        		break;
					        	
					        	// Pesquisar por nome o produto
					        	case 4:{
					        		System.out.println("Digite o nome do produto:");
					        		String nome = entrada.nextLine();
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.buscarNome(nome));
					        		System.out.println("");					        		
					        	}
					        		break;
					        		
					        	// Pesquisar por codigo do ID o produto
					        	case 5:{
					        		System.out.println("Digite o ID do produto:");
					        		String nome = entrada.nextLine();
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.buscarId(nome));
					        		System.out.println("");
					        	}
					        		break;
					        	case 6:{
					        		System.out.println("Entre com o Nome do produto que deseja atualizar:");
					        		String nomeAtual = entrada.nextLine();
					        		System.out.println("Entre com o id do produto:");
					        		String id = entrada.nextLine();
					        		System.out.println("Entre com o nome do produto:");
					        		String nome = entrada.nextLine();
					        		System.out.println("Entre com o tipo do produto:");
					        		String tipo = entrada.nextLine();
					        		System.out.println("Entre com o preco do produto:");
					        		String preco = entrada.nextLine();
					        		String extra = "";
					        		
					        		if(tipo.equalsIgnoreCase("Alimento")) {
					        			
					        			System.out.println("Digite o Kg do produto:");
						        		extra = entrada.nextLine();
					        			
					        		} else if(tipo.equalsIgnoreCase("EletroEletronico")){
					        			
					        			System.out.println("Digite a Voltagem do produto:");
						        		extra = entrada.nextLine();
										
									} else {
										
										System.out.println("Digite o Tamanho do produto:");
						        		extra = entrada.nextLine();
										
									}
					        		
					        		if(!extra.isEmpty()) {
						        		System.out.println("");
						        		System.out.println(stubObjRemotoCliente.atualizar(nomeAtual, id, nome, preco, tipo, extra));
						        		System.out.println("");
					        		} else {
					        			System.out.println("Tipo não foi definido...");
					        		}
					        	}
					        		break;
					        		
					        	// Exibir quantidade de produtos
					        	case 7: {
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.quantidade());
					        		System.out.println("");
					        	}
					        		break;
					        		
					        	// Adicionar ao carrinho para realizar uma operação
					        	case 8:{
					        		System.out.println("Entre com o nome do produto");
					        		String nome = entrada.nextLine();
					        		System.out.println(stubObjRemotoCliente.addCarrinho(nome));
					        		System.out.println("");
					        	}
					        		break;
					        		
					        	// Exibir carrinho para o usuário
					        	case 9:{
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.exibirItensCarrinho());
					        		String end = entrada.nextLine();
					        		
					        		if(end.equalsIgnoreCase("Y")) {
					        			System.out.println(stubObjRemotoCliente.finalizarCompra());
					        		} else if(end.equalsIgnoreCase("R")) {
					        			System.out.println("Digite o nome do produto:");
					        			String nome = entrada.nextLine();
					        			System.out.println(stubObjRemotoCliente.removeCarrinho(nome));
					        		}
					        	}
					        		break;
					        		
					        	// fechar ou sair do menu
					        	case 10:{
					        		exitMenu = true;
					        		exit = true;
					        		resultado = -5;
					        	}
					        		break;
				        	}
			        	
			        	}catch(NumberFormatException e) {
				    		System.out.println("Entrada inválida");
				    	}

		        	}while(!exitMenu);
		        	
		        	
		        } else if (resultado == CLIENTE) {
		        	
		        	System.out.println("Cliente logado no sistema");
		        	String opcao = "";
		        	
		        	do {
		        		String menu = stubObjRemotoCliente.menuUsuario();
			        	System.out.println(menu);
			        	opcao = entrada.nextLine();
			        	
	        			try {
			        		
				        	int digito = Integer.parseInt(opcao);
				        	
				        	switch(digito) {
				        	
				        		// Listar todos os produtos
					        	case 1:{
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.listarProdutos());
					        		System.out.println("");
					        	}
					        		break;
					        		
					        	// Pesquisar por nome os produtos
					        	case 2:{
					        		System.out.println("Digite o nome do produto:");
					        		String nome = entrada.nextLine();
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.buscarNome(nome));
					        		System.out.println("");		
					        	}
					        		break;
					        		
					        	// Pesquisar por codigo ID do produto
					        	case 3:{
					        		System.out.println("Digite o ID do produto:");
					        		String nome = entrada.nextLine();
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.buscarId(nome));
					        		System.out.println("");
					        	}
					        		break;
					        		
					        	// Exibir quantidade de produtos
					        	case 4:{
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.quantidade());
					        		System.out.println("");
					        	}
					        		break;
					        		
					        	// Adicionar ao carrinho produto
					        	case 5:{
					        		System.out.println("Digite o nome do produto");
					        		String nome = entrada.nextLine();
					        		System.out.println(stubObjRemotoCliente.addCarrinho(nome));
					        		System.out.println("");
					        	}
					        		break;
					        		
					        	// Exibir carrinho  ou mostrar o que foi adicioado a lista de produtos
					        	case 6:{
					        		System.out.println("");
					        		System.out.println(stubObjRemotoCliente.exibirItensCarrinho());
					        		String end = entrada.nextLine();
					        		
					        		if(end.equalsIgnoreCase("Y")) {
					        			System.out.println(stubObjRemotoCliente.finalizarCompra());
					        		} else if(end.equalsIgnoreCase("R")) {
					        			System.out.println("Digite o nome do produto:");
					        			String nome = entrada.nextLine();
					        			System.out.println(stubObjRemotoCliente.removeCarrinho(nome));
					        		}
					        	}
					        		break;
					        		
					        	// Saindo do menu ou fechando
					        	case 7: {
					        		exitMenu = true;
					        		exit = true;
					        		resultado = -2;
					        	}
					        		break;
				        	}
			        	
			        	}catch(NumberFormatException e) {
				    		System.out.println("Opção inválida");
				    	}
			        	
		        	}while(!exitMenu);
		        	
		        } else if (resultado == -7) {
		        	
		        	System.out.println("A senha incorreta, tente novamente");
		        	
		        } else {
		        	
		        	System.out.println("Usuario incorreto, tente novamente");
		        	
		        }
	           
		        if(resultado != -7) {
			        System.out.println("Tentar novamente?\nY ou N?");
			        String opcao = entrada.nextLine();
			        if(opcao.equalsIgnoreCase("n")) {
			        	exit = true;
			        }
		        }
	        
            }while(!exit);

            System.out.println("Encerrado o cliente...");
            
        } catch (Exception e) {
            System.err.println("Cliente com erro");
            e.printStackTrace();
        }
        entrada.close();
    }
	    
}
