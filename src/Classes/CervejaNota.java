package Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class CervejaNota implements EntityBase{

	@Id
	private Integer id;
	
	private Double nota;
	private Double total;
	private Integer nusuario;
	private Double media;

	@OneToOne
	private Cerveja cerveja;
	
	@ManyToMany(mappedBy ="notas")
	private List<Cerveja> cervejas;
	
	@ManyToMany(mappedBy= "notas")
	private List<Usuario> usuarios;

	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	public Integer getNusuario() {
		return nusuario;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public void setNusuario(Integer nusuario) {
		this.nusuario = nusuario;
	}


	public Double getMedia() {
		return media;
	}


	public void setMedia(Double media) {
		this.media = media;
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

	public void adicionaCerveja(Cerveja p) {
		cervejas.add(p);
	}
	public void removeCerveja(Cerveja p) {
		cervejas.remove(p);
	}
	public Cerveja getCerveja(int posicao) {
		return cervejas.get(posicao);
	}
	
	public Cerveja getC() {
		return cerveja;
	}

	public void setC(Cerveja cerveja) {
		this.cerveja = cerveja;
	}

	public CervejaNota() {}

	public CervejaNota(Integer id, Double nota, Double total, Integer nusuario, Double media, Cerveja cerveja,
			Usuario usuario, Cerveja c) {
		this.id = id;
		this.nota = nota;
		this.total = total;
		this.nusuario = nusuario;
		this.media = media;
		cervejas = new ArrayList<Cerveja>();
		usuarios = new ArrayList<Usuario>();
		this.cerveja = c;
		}

	@Override
	public String toString() {
		return "NOTA: " + media;
	}

	
}
