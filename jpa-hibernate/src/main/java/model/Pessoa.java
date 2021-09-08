package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({

	@NamedQuery(name = "Pessoa.findAll", query =  "select p from Pessoa p"),
	@NamedQuery(name = "Pessoa.findByName", query =  "select p from Pessoa p where p.nome = :nome")
	
})
public class Pessoa {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;	
	
	private String nome;
	private String sobrenome;
	private String login;
	private String email;
	private String senha;
	private int idade;
	
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
	private List<TelefonePessoa> telefoneUsers;

	public Pessoa(String nome,String sobrenome,String login,String email,String senha,int idade) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.idade = idade;
		
	}
	
	public Pessoa() {
		
	}
	
	public List<TelefonePessoa> getTelefoneUsers() {
		return telefoneUsers;
	}

	public void setTelefoneUsers(List<TelefonePessoa> telefoneUsers) {
		this.telefoneUsers = telefoneUsers;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", login=" + login + ", email="
				+ email + ", senha=" + senha + ", idade=" + idade + "]";
	}
	
	
	
}
