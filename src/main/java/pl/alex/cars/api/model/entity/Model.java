package pl.alex.cars.api.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import pl.alex.cars.api.brand.data.Brand;
import pl.alex.cars.api.modification.entity.Modification;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Model {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_seq")
  @SequenceGenerator(name = "model_seq", sequenceName = "model_sequence", allocationSize = 10)
  private Long id;
  private String name;

  @ManyToOne(cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  @JoinColumn(name = "brand_id")
  private Brand brand;

  @OneToMany(mappedBy = "model", cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  @BatchSize(size = 30)
  private List<Modification> modifications;

}
