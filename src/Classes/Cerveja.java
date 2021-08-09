package Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
public class Cerveja implements EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nomeCerveja;
	private Double teorAlcoolico;
	private String descricaoCerveja;
	private String path;
	private String status;
	
	@ManyToMany(mappedBy= "cervejas")
	private List<Usuario> usuarios;
	
	@ManyToMany
	@JoinTable(name = "TB_CERVEJA_INGREDIENTES",  // nome da tabela relacional no BD		
	// lado dominante/lado forte
	joinColumns = {
			@JoinColumn(name = "cerveja_id")
			
	},
	//lado dominado/lado fraco
	inverseJoinColumns = {
			@JoinColumn(name = "ingrediente_id")
	}	
)
	private List<Ingrediente> ingredientes;
	
	@ManyToMany
	@JoinTable(name = "TB_USUARIO_NOTA",  // nome da tabela relacional no BD		
	// lado dominante/lado forte
	joinColumns = {
			@JoinColumn(name = "cerveja_id")
			
	},
	//lado dominado/lado fraco
	inverseJoinColumns = {
			@JoinColumn(name = "cervejanota_id")
	}	
)
	private List<CervejaNota> notas;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Administrador administrador;
	
	@ManyToOne
	private Pais pais;
	
	@ManyToOne
	private EstiloCerveja estilo;

	@ManyToOne
	private Amargor amargor;
	
	@ManyToOne
	private Coloracao coloracao;
	
	public Cerveja() {	
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCerveja() {
		return nomeCerveja;
	}

	public void setNomeCerveja(String nomeCerveja) {
		this.nomeCerveja = nomeCerveja;
	}

	public Double getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(Double teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public String getDescricaoCerveja() {
		return descricaoCerveja;
	}

	public void setDescricaoCerveja(String descricaoCerveja) {
		this.descricaoCerveja = descricaoCerveja;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public EstiloCerveja getEstilo() {
		return estilo;
	}

	public void setEstilo(EstiloCerveja estilo) {
		this.estilo = estilo;
	}

	public Amargor getAmargor() {
		return amargor;
	}

	public void setAmargor(Amargor amargor) {
		this.amargor = amargor;
	}

	public Coloracao getColoracao() {
		return coloracao;
	}

	public void setColoracao(Coloracao coloracao) {
		this.coloracao = coloracao;
	}

	public void adicionaUsuario(Usuario p) {
		usuarios.add(p);
	}
	public void removeUsuario(Usuario p) {
		usuarios.remove(p);
	}
	public Usuario getUsuario(int posicao) {
		return usuarios.get(posicao);
	}
	
	public void adicionaIngredientes(Ingrediente i) {
		ingredientes.add(i);
	}
	public void removerIngrediente(Ingrediente i) {
		ingredientes.remove(i);
	}
	public Ingrediente getIngredientes(int posicao) {
		return ingredientes.get(posicao);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cerveja(int id, String nomeCerveja, Double teorAlcoolico, String descricaoCerveja, String path,
			Usuario usuario, Pais pais, EstiloCerveja estilo, Amargor amargor,Administrador administrador, Coloracao coloracao, String status) {
		this.id = id;
		this.nomeCerveja = nomeCerveja;
		this.teorAlcoolico = teorAlcoolico;
		this.descricaoCerveja = descricaoCerveja;
		this.path = path;
		this.usuario = usuario;
		this.pais = pais;
		this.estilo = estilo;
		this.amargor = amargor;
		this.coloracao = coloracao;
		this.status = status;
		this.administrador = administrador;
		usuarios = new ArrayList<Usuario>();
		ingredientes = new ArrayList<Ingrediente>();
		notas = new ArrayList<CervejaNota>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amargor == null) ? 0 : amargor.hashCode());
		result = prime * result + ((coloracao == null) ? 0 : coloracao.hashCode());
		result = prime * result + ((descricaoCerveja == null) ? 0 : descricaoCerveja.hashCode());
		result = prime * result + ((estilo == null) ? 0 : estilo.hashCode());
		result = prime * result + id;
		result = prime * result + ((ingredientes == null) ? 0 : ingredientes.hashCode());
		result = prime * result + ((nomeCerveja == null) ? 0 : nomeCerveja.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((teorAlcoolico == null) ? 0 : teorAlcoolico.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (amargor == null) {
			if (other.amargor != null)
				return false;
		} else if (!amargor.equals(other.amargor))
			return false;
		if (coloracao == null) {
			if (other.coloracao != null)
				return false;
		} else if (!coloracao.equals(other.coloracao))
			return false;
		if (descricaoCerveja == null) {
			if (other.descricaoCerveja != null)
				return false;
		} else if (!descricaoCerveja.equals(other.descricaoCerveja))
			return false;
		if (estilo == null) {
			if (other.estilo != null)
				return false;
		} else if (!estilo.equals(other.estilo))
			return false;
		if (id != other.id)
			return false;
		if (ingredientes == null) {
			if (other.ingredientes != null)
				return false;
		} else if (!ingredientes.equals(other.ingredientes))
			return false;
		if (nomeCerveja == null) {
			if (other.nomeCerveja != null)
				return false;
		} else if (!nomeCerveja.equals(other.nomeCerveja))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (teorAlcoolico == null) {
			if (other.teorAlcoolico != null)
				return false;
		} else if (!teorAlcoolico.equals(other.teorAlcoolico))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cerveja [id=" + id + ", nomeCerveja=" + nomeCerveja + ", teorAlcoolico=" + teorAlcoolico
				+ ", descricaoCerveja=" + descricaoCerveja + ", path=" + path + ", usuarios=" + usuarios
				+ ", ingredientes=" + ingredientes + ", usuario=" + usuario + ", pais=" + pais + ", estilo=" + estilo
				+ ", amargor=" + amargor + ", coloracao=" + coloracao + "]";
	}
	
	
	
}
