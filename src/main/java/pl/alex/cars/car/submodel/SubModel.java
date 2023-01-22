package pl.alex.cars.car.submodel;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.car.model.Model;
import pl.alex.cars.car.modification.Modification;

@Getter
@Setter
@Entity
@Table(name = "sub_model")
public class SubModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToOne(cascade = { CascadeType.DETACH, 
						   CascadeType.MERGE, 
						   CascadeType.PERSIST, 
						   CascadeType.REFRESH })
	@JoinColumn(name = "model_id")
	private Model model;

	@OneToMany(mappedBy = "submodel", cascade = { CascadeType.DETACH, 
			CascadeType.MERGE, 
			CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Modification> modifications;

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubModel other = (SubModel) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	

	
}
