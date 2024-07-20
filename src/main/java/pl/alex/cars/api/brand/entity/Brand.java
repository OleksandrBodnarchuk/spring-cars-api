package pl.alex.cars.api.brand.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import pl.alex.cars.api.model.entity.Model;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_seq")
  @SequenceGenerator(name = "brand_seq", sequenceName = "brand_sequence", allocationSize = 10)
  private Long id;
  private String name;

  @OneToMany(mappedBy = "brand", cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  @BatchSize(size = 30)
  private List<Model> models;

}
