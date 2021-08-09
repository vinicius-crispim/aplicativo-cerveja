package Classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coloracao implements EntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String coloracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getColoracao() {
		return coloracao;
	}

	public void setColoracao(String coloracao) {
		this.coloracao = coloracao;
	}
	
	public Coloracao() {}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coloracao == null) ? 0 : coloracao.hashCode());
		result = prime * result + id;
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
		Coloracao other = (Coloracao) obj;
		if (coloracao == null) {
			if (other.coloracao != null)
				return false;
		} else if (!coloracao.equals(other.coloracao))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	public Coloracao(Integer id, String coloracao) {
		this.id = id;
		this.coloracao = coloracao;
	}
	
	@Override
	public String toString() {
		return coloracao;
	}
}
