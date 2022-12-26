package pl.alex.cars.car.info;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.car.modification.Modification;

@Getter
@Setter
@Entity
@Table(name = "general_info")
public class GeneralInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String yearOfCreation;
	private String brand; 
	private String engine;
	private String bodyType;
	private String model;
	private String doors;
	private String seats;
	
	@OneToOne(mappedBy = "generalInfo")
	private Modification modification;
}
