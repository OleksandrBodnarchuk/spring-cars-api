package pl.alex.cars.entity.model;

import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.Logo;
import pl.alex.cars.entity.Manufacturer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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
	@JoinColumn(name = "manufactirer_id")
	private Manufacturer manufacturer;
	
	@OneToOne(cascade = { CascadeType.DETACH, 
			   			  CascadeType.MERGE,
			   			  CascadeType.PERSIST,
			   			  CascadeType.REFRESH })
	@JoinColumn(name = "logo_id")
	private Logo logo;

}
