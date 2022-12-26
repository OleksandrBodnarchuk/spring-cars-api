package pl.alex.cars.entity.details;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.Modification;

@Getter
@Setter
@Entity
@Table(name = "running_feature")
public class RunningFeature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String maxSpeed;
	private String acceleration;
	private String fuelTown;
	private String fuelRoad;
	private String fuelAverage;
	
	@OneToOne(mappedBy = "runningFeature")
	private Modification modification;
}