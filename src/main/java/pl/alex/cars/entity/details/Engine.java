package pl.alex.cars.entity.details;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.Modification;

@Getter
@Setter
@Entity
public class Engine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String displacement;
    private String kw;
    private String hp;
    private String torque;
    private String fuelSupply;
    private String cylinders;
    private String cylinderDiameter;
    private String ValvesInCylinders;
    private String gears;
    private String fuel;
    private String fuelCapacity;
    private String ecoStandart;

    
	@OneToOne(mappedBy = "engine")
	private Modification modification;
}
