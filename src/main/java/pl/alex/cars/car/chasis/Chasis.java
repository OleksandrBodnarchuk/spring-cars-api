package pl.alex.cars.car.chasis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.car.modification.Modification;

@Getter
@Setter
@Entity
public class Chasis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String abs;
	private String frontBrakes;
	private String rearBrakes;
	private String tires;
	private String wheels;
    
	@OneToOne(mappedBy = "chasis")
	private Modification modification;
}
