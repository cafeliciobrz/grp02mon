package br.com.itaumon.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Serve para informar ao SpringBoot que esta classe tem uma tabela no banco de dados (BD).
@Table(name = "TB_USUARIO") // Indica a tabela que será utilizada no banco de dados, pois em um banco pode ter varias tabelas.
public class Usuario {
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id // Para indicar que este atributo faz ligação com a PK (chave primaria) da tabela usuária. O nome Id que indica isso, não tem relação com o nome do atributo.
	@Column(name = "id")
	private int codigo;
		
	@Column (name = "nome", length = 40)
	private String nome;
	
	@Column (name = "email", length = 70)
	private String email;
	
	@Column (name = "senha", length = 20)
	private String senha;
	
	@Column (name = "foto", length = 100)
	private String foto;
	
	@Column (name = "racf", length = 7)
	private String racf;
	
	
	public Usuario() {
		super();
	}
	
	public Usuario(int codigo, String nome, String email, String senha, String foto, String racf) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
		this.racf = racf;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome.toUpperCase();
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	public String getEmail() {
		return email.toLowerCase();
	}
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getRacf() {
		return racf.toLowerCase();
	}
	public void setRacf(String racf) {
		this.racf = racf.toLowerCase();
	}


}
