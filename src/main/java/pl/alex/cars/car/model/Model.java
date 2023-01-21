package pl.alex.cars.car.model;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.car.brand.Brand;
import pl.alex.cars.car.submodel.SubModel;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Getter
@Setter
@Entity
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
	
	@Transient
	private String url;
	
//	@OneToMany(mappedBy = "model", cascade = { CascadeType.DETACH, 
//			   CascadeType.MERGE, 
//			   CascadeType.PERSIST,
//			   CascadeType.REFRESH })
//	private List<Picture> pictures;

}
