package pl.alex.cars.api.running;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.api.modification.Modification;

@Getter
@Setter
@Entity
@Table(name = "running_feature")
public class RunningFeature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer maxSpeed;
	private Double acceleration;
	private Double fuelTown;
	private Double fuelRoad;
	private Double fuelAverage;
	
	@OneToOne(mappedBy = "runningFeature")
	private Modification modification;
}
