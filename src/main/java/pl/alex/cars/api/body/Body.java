package pl.alex.cars.api.body;

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
public class Body {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_seq")
  @SequenceGenerator(name = "body_seq", sequenceName = "body_sequence", allocationSize = 10)
  private Long id;
  private Integer length;
  private Integer width;
  private Integer height;
  private String wheelBase;
  private Integer weight;
  private Integer maxWidth;
  private String bootCapacity;

  @OneToOne(mappedBy = "body")
  private Modification modification;
}
