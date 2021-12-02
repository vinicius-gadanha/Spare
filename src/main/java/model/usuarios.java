package model;
public class usuarios{
    private String nome;
    private String sobrenome;
    private String nomeusuario;
    private String email;
    private String senha;
    private double saldo;
    private int pacoteassinaturaid;
    private int metaid;
	private int operacaoid;
	
    public usuarios(String nome, String sobrenome, String nomeusuario, String email, String senha, double saldo, int pacoteassinaturaid, int metaid, int operacaoid) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nomeusuario = nomeusuario;
		this.email = email;
		this.senha = senha;
		this.saldo = saldo;
		this.pacoteassinaturaid = pacoteassinaturaid;
		this.metaid = metaid;
		this.operacaoid = operacaoid;
	}
    public usuarios(String nome, String sobrenome, String nomeusuario, String email, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nomeusuario = nomeusuario;
		this.email = email;
		this.senha = senha;
	}
    
	public usuarios(String nome, String sobrenome, String nomeusuario, String email, String senha, int op_id) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nomeusuario = nomeusuario;
		this.email = email;
		this.senha = senha;
		this.operacaoid=op_id;
	}
	public String getNome() {
        return nome;
	}
    
	public void setNome(String nome) {
        this.nome = nome;
	}

    public String getSobrenome() {
        return sobrenome;
	}
    
	public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
	}

    public String getNomeUsuario() {
        return nomeusuario;
	}
    
	public void setNomeUsuario(String nomeuruario) {
        this.nomeusuario = nomeuruario;
	}

    public String getEmail() {
        return email;
	}
    
	public void setEmail(String email) {
        this.email = email;
	}

    public String getSenha() {
        return senha;
	}
    
	public void setSenha(String senha) {
        this.senha = senha;
	}
    
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

	public int getPacoteassinaturaid() {
		return pacoteassinaturaid;
	}

	public void setPacoteassinaturaid(int pacoteassinaturaid) {
		this.pacoteassinaturaid = pacoteassinaturaid;
	}

    public int getMetaid() {
		return metaid;
	}

	public void setMetaid(int metaid) {
		this.metaid = metaid;
	}

    public int getOperacaoid() {
		return operacaoid;
	}

	public void setOperacaoid(int operacaoid) {
		this.operacaoid = operacaoid;
	}
	public String toString() {
        return "Usuario [nome=" + nome + ", nomeusuario=" + nomeusuario + ", sobrenome=" + sobrenome + ", email= "+email+", senha="+senha+"]";
    }
}
