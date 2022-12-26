package pl.alex.cars.car.modification;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.car.body.Body;
import pl.alex.cars.car.chasis.Chasis;
import pl.alex.cars.car.engine.Engine;
import pl.alex.cars.car.file.Picture;
import pl.alex.cars.car.info.GeneralInfo;
import pl.alex.cars.car.running.RunningFeature;
import pl.alex.cars.car.submodel.SubModel;

@Entity
@Getter
@Setter
@Table(name = "modification")
public class Modification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
	
	@ManyToOne(cascade = { CascadeType.DETACH, 
			   CascadeType.MERGE, 
			   CascadeType.PERSIST, 
			   CascadeType.REFRESH })
	@JoinColumn(name = "submodel_id")
	private SubModel submodel;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "picture_id", referencedColumnName = "id")
	private Picture picture;
	
	@Transient
	private String url;
	@Transient
	private String imageLink;
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modification other = (Modification) obj;
		return Objects.equals(id, other.id);
	}
}
