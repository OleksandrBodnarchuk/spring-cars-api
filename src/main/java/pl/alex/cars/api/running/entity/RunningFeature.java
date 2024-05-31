package pl.alex.cars.api.running.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.alex.cars.api.modification.entity.Modification;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RunningFeature {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "running_feature_seq")
  @SequenceGenerator(name = "running_feature_seq", sequenceName = "running_feature_sequence", allocationSize = 10)
  private Long id;
  private Integer maxSpeed;
  private Double acceleration;
  private Double fuelTown;
  private Double fuelRoad;
  private Double fuelAverage;

  @OneToOne(mappedBy = "runningFeature")
  private Modification modification;
}
