package pl.alex.cars.api.engine;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Engine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String displacement;
  private Integer kw;
  private Integer hp;
  private String torque;
  private String fuelSupply;
  private Integer cylinders;
  private Double cylinderDiameter;
  private Integer valvesInCylinders;
  private Integer gears;
  private String fuel;
  private Integer fuelCapacity;
  private String ecoStandart;


  @OneToOne(mappedBy = "engine")
  private Modification modification;
}
