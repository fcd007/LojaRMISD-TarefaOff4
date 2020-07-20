package modelo;

public class EletroEletronico extends Produto{

	private String voltagem;
	
	public EletroEletronico(String codigo, String nome, String preco, String voltagem) {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setTipo("EletroEletronico");
		this.setPreco(preco);
		this.setVoltagem(voltagem);
	}
	
	public EletroEletronico() {
		this.setTipo("EletroEletronico");
	}

	public String getVoltagem() {
		return voltagem;
	}

	public void setVoltagem(String voltagem) {
		this.voltagem = voltagem;
	}
	
	public String toString() {
		String objeto = getCodigo() + " - " + getNome() + ":\t" + getTipo() + "\t" + getPreco() + "\t " + getVoltagem() + " V";
		return objeto;
	}
	
}