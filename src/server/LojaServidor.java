package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import modelo.Alimento;
import modelo.EletroEletronico;
import modelo.ListProduto;
import modelo.Roupa;
import modelo.Usuario;

public class LojaServidor {
	
	// perfil de usuarios
	public static final int FUNCIONARIO = 0;
	public static final int CLIENTE = 1;
	public static final int SERVIDOR_PORTA = 34001;
	
	public static void main(String args[]) {
		
		System.setProperty("java.security.policy", "java.policy");
		
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
	    
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		
		
		try {
			
			//USUÁRIOS DO SISTEMA
			Map<String, Usuario> usuarios = new HashMap<>();
			
			//criando um objeto usuário e funcionário, passando a senha
			Usuario funcionario1 = new Usuario(FUNCIONARIO, "victoria", "654321");
	        Usuario cliente1 = new Usuario(CLIENTE, "dantas", "654320");
	        
	        usuarios.put(funcionario1.getUsuario(), funcionario1);
	        usuarios.put(cliente1.getUsuario(), cliente1);
	        //---------------
	        
	        //Lista de produtos
	        ListProduto lista = new ListProduto();
	        
	        // criando 4 Objetos de cada tipo de produto: ID, Nome, Tipo, Preco
	        EletroEletronico eletroEletronico1 = new EletroEletronico("001", "Cafeteira", "250,00", "220V");
	        EletroEletronico eletroEletronico2 = new EletroEletronico("002", "SmartPhone S10", "650,00", "220V");
	        EletroEletronico eletroEletronico3 = new EletroEletronico("003", "Geladeira", "1250,00", "220V");
	        EletroEletronico eletroEletronico4 = new EletroEletronico("004", "Desktop HP", "1500,00", "220V");
	        
	        Alimento alimento1 = new Alimento("005", "Biscoito Maria", "3,00", "10");
	        Alimento alimento2 = new Alimento("006", "Pipoca", "2,00", "10");
	        Alimento alimento3 = new Alimento("007", "Chocolate", "12,00", "10");
	        Alimento alimento4 = new Alimento("008", "Requeijão", "3,50", "10");
	        
	        Roupa roupa1 = new Roupa("009", "Camisa Polo", "39,90", "M");
	        Roupa roupa2 = new Roupa("010", "Camisa2", "29,90", "40");
	        Roupa roupa3 = new Roupa("011", "Calça Jeans", "79,90", "40");
	        Roupa roupa4 = new Roupa("012", "Camisa Social", "89,90", "P");
	        
	        //add produto eletroEletronico
	        lista.setItem(eletroEletronico1);
	        lista.setItem(eletroEletronico2);
	        lista.setItem(eletroEletronico3);
	        lista.setItem(eletroEletronico4);
	        //add produto Alimento
	        lista.setItem(alimento1);
	        lista.setItem(alimento2);
	        lista.setItem(alimento3);
	        lista.setItem(alimento4);
	        // add produto Roupa
	        lista.setItem(roupa1);
	        lista.setItem(roupa2);
	        lista.setItem(roupa3);
	        lista.setItem(roupa4);
			
			//criar um novo objeto servidor
			LojaImplementada refObjetoRemoto = new LojaImplementada(usuarios, lista);
			
			LojaInterface skeleton = (LojaInterface) UnicastRemoteObject.exportObject(refObjetoRemoto, 0);

			
			LocateRegistry.createRegistry(SERVIDOR_PORTA); 
			
			
			Registry registro = LocateRegistry.getRegistry(SERVIDOR_PORTA);
			
			/* O método bind é então chamado no stub do registro para vincular 
			 * o stub do objeto remoto ao nome "Loja" no registro.*/
			
			registro.bind("Loja", skeleton);

			System.err.println("Servidor iniciando e rodando. ");
			
		} catch (Exception e) {
			System.err.println("Servidor com erro crítico");
			e.printStackTrace();
		}
	}
	
	
}
