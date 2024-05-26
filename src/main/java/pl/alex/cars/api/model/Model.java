package pl.alex.cars.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.submodel.SubModel;

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

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String name;
	
	@ManyToOne(cascade = { CascadeType.DETACH, 
						   CascadeType.MERGE, 
						   CascadeType.PERSIST, 
						   CascadeType.REFRESH })
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "model", cascade = { CascadeType.DETACH, 
			   CascadeType.MERGE, 
			   CascadeType.PERSIST,
			   CascadeType.REFRESH })
	private List<SubModel> submodels;
	
	@Override
	public int hashCode() {
		return Objects.hash(brand, id, name, submodels);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(submodels, other.submodels);
	}
	
//	@OneToMany(mappedBy = "model", cascade = { CascadeType.DETACH, 
//			   CascadeType.MERGE, 
//			   CascadeType.PERSIST,
//			   CascadeType.REFRESH })
//	private List<Picture> pictures;
	
	

}
