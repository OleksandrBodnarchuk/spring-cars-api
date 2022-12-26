package pl.alex.cars.entity.model;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.Brand;

import java.util.List;

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
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String name;
	
	@ManyToOne(cascade = { CascadeType.DETACH, 
						   CascadeType.MERGE, 
						   CascadeType.PERSIST, 
						   CascadeType.REFRESH })
	@JoinColumn(name = "manufacturer_id")
	private Brand manufacturer;
	
	@OneToMany(mappedBy = "model", cascade = { CascadeType.DETACH, 
			   CascadeType.MERGE, 
			   CascadeType.PERSIST,
			   CascadeType.REFRESH })
	private List<Picture> pictures;

}
