package pl.alex.cars.car.brand;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.car.model.Model;

@Getter
@Setter
@Entity
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "brand", cascade = { CascadeType.DETACH, 
								   CascadeType.MERGE, 
								   CascadeType.PERSIST,
								   CascadeType.REFRESH })
	private List<Model> models;
	
	@Transient
	private String url; 
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "logo_id")
//	private Logo logo;
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		return Objects.equals(id, other.id);
	}
}
