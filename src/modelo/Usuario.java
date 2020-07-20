package modelo;

public class Usuario {

	private int perfil;
	private String usuario;
	private String senha;
	
	public Usuario(int role, String usuario, String senha) {
		this.perfil = role;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	
	public int getPerfil() {
		return perfil;
	}
	public void setPerfil(int role) {
		this.perfil = role;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
