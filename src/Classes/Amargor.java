package Classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amargor implements EntityBase{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String amargor;

	public Amargor() {}
	
	public Amargor(Integer id, String amargor) {
		this.id = id;
		this.amargor = amargor;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAmargor() {
		return amargor;
	}

	public void setAmargor(String amargor) {
		this.amargor = amargor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amargor == null) ? 0 : amargor.hashCode());
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
		Amargor other = (Amargor) obj;
		if (amargor == null) {
			if (other.amargor != null)
				return false;
		} else if (!amargor.equals(other.amargor))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return amargor;
	}
	
	
}
