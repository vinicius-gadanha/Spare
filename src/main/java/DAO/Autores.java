package DAO;
public class Autores {
	private int codigo;
	private String nome;
	private int idade;
	private String genero;
	
	public Autores() {
		this.codigo = -1;
		this.nome = "";
		this.idade = 0;
		this.genero = "";
	}
	
	public Autores(int codigo, String nome, int idade, String genero) {
		this.codigo = codigo;
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setSenha(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Autor [codigo=" + codigo + ", nome=" + nome + ", idade=" + idade + ", genero=" + genero + "]";
	}	
}

