package Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario implements EntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String login;
	private String senha;
	private Integer idade;
	private String telefone;
	private String CPF;
	private String email;
	
	@ManyToMany
	@JoinTable(name = "TB_USUARIO_CERVEJA",  // nome da tabela relacional no BD		
	// lado dominante/lado forte
	joinColumns = {
			@JoinColumn(name = "usuario_id")
			
	},
	//lado dominado/lado fraco
	inverseJoinColumns = {
			@JoinColumn(name = "cerveja_id")
	}	
)
	private List<Cerveja> cervejas;
	
	@ManyToMany
	@JoinTable(name = "TB_USUARIO_NOTA",  // nome da tabela relacional no BD		
	// lado dominante/lado forte
	joinColumns = {
			@JoinColumn(name = "usuario_id")
			
	},
	//lado dominado/lado fraco
	inverseJoinColumns = {
			@JoinColumn(name = "cervejanota_id")
	}	
)
	private List<CervejaNota> notas;
	
	public Usuario() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void adicionaCerveja(Cerveja p) {
		cervejas.add(p);
	}
	public void removeCerveja(Cerveja p) {
		cervejas.remove(p);
	}
	public Cerveja getCerveja(int posicao) {
		return cervejas.get(posicao);
	}

	public void adicionaNota(CervejaNota p) {
		notas.add(p);
	}
	public void removeNota(CervejaNota p) {
		notas.remove(p);
	}
	public CervejaNota getNota(int posicao) {
		return notas.get(posicao);
	}
	public Usuario(int id, String nome, String login, String senha, Integer idade, String telefone, String cPF,
			String email) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.idade = idade;
		this.telefone = telefone;
		CPF = cPF;
		this.email = email;
		cervejas = new ArrayList<Cerveja>();
		notas = new ArrayList<CervejaNota>();
	}




}
