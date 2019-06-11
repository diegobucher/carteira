package br.com.uniasselvi.carteira.entidade;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Entidade implements Serializable {
	private static final long serialVersionUID = 6220637461477064654L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Entidade) {
			if (obj.getClass() == this.getClass()) {
				if (getId() != null && ((Entidade) obj).getId().equals(getId()))
					return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Entidade [id=" + id + "]";
	}

}
