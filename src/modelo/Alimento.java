package modelo;

public class Alimento extends Produto {

	private String kg;
	
	public Alimento(String codigo, String nome, String preco, String kg) {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setTipo("Alimento");
		this.setPreco(preco);
		this.setKg(kg);
	}

	public Alimento() {
		this.setTipo("Alimento");
	}

	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}
	
	public String toString() {
		// codigo : nome: tipo: preço: Kg:
		String string = 
		  getCodigo() + " : " 
		+ getNome() + ":    " 
		+ getTipo() + "    " 
		+ getPreco() + "\t " 
		+ getKg() + " Kg";
		
		return string;
	}
}
