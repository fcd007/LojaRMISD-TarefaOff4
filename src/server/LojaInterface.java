package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LojaInterface extends Remote {  
	

	   int login(String usuario, String senha) throws RemoteException;
	   
	   String funcioMenu() throws RemoteException;
	   String menuUsuario() throws RemoteException;
	
	   String adicionarProduto(String id, String nome, String preco, String tipo, String extra) throws RemoteException;  
	   
	   String apagarProd(String nome) throws RemoteException;
	   
	   String listarProdutos() throws RemoteException;
	   
	   String buscarNome(String nome) throws RemoteException;
	   
	   String buscarId(String id) throws RemoteException;
	   
	   String atualizar(String nomeAtual, String id, String nome, String preco, String tipo, String extra) throws RemoteException;
	   
	   String quantidade() throws RemoteException;
	   
	   String addCarrinho(String nome) throws RemoteException;
	   
	   String exibirItensCarrinho() throws RemoteException;
	   
	   String removeCarrinho(String nome) throws RemoteException;
	   
	   String finalizarCompra() throws RemoteException;
	   
} 