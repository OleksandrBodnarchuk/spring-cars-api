package pl.alex.cars.api.modification.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.alex.cars.api.body.Body;
import pl.alex.cars.api.chasis.Chasis;
import pl.alex.cars.api.engine.Engine;
import pl.alex.cars.api.info.GeneralInfo;
import pl.alex.cars.api.model.entity.Model;
import pl.alex.cars.api.running.entity.RunningFeature;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Modification {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modification_seq")
  @SequenceGenerator(name = "modification_seq", sequenceName = "modification_sequence", allocationSize = 10)
  private Long id;

  private String name;

  @OneToOne(cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  @JoinColumn(name = "general_info_id", referencedColumnName = "id")
  private GeneralInfo generalInfo;

  @OneToOne(cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  @JoinColumn(name = "body_id", referencedColumnName = "id")
  private Body body;

  @OneToOne(cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  @JoinColumn(name = "engine_id", referencedColumnName = "id")
  private Engine engine;

  @OneToOne(cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})

  @JoinColumn(name = "chassis_id", referencedColumnName = "id")
  private Chasis chasis;

  @OneToOne(cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  @JoinColumn(name = "running_feature_id", referencedColumnName = "id")
  private RunningFeature runningFeature;

  @ManyToOne(cascade = {CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  @JoinColumn(name = "model_id")
  private Model model;
}
