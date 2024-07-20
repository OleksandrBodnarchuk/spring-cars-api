package pl.alex.cars.api.chasis;

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
public class Chasis {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chasis_seq")
  @SequenceGenerator(name = "chasis_seq", sequenceName = "chasis_sequence", allocationSize = 10)
  private Long id;
  private String abs;
  private String frontBrakes;
  private String rearBrakes;
  private String tires;
  private String wheels;

  @OneToOne(mappedBy = "chasis")
  private Modification modification;
}
