package pl.alex.cars.entity;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.alex.cars.entity.details.Body;
import pl.alex.cars.entity.details.Chasis;
import pl.alex.cars.entity.details.Engine;
import pl.alex.cars.entity.details.GeneralInfo;
import pl.alex.cars.entity.details.RunningFeature;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Modification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private GeneralInfo generalInfo;
	private Body body;
	private Engine engine;
	private Chasis chasis;
	private RunningFeature runningFeature;
	
	@ManyToOne(cascade = { CascadeType.DETACH, 
			   CascadeType.MERGE, 
			   CascadeType.PERSIST, 
			   CascadeType.REFRESH })
	@JoinColumn(name = "submodel_id")
	private SubModel submodel;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "picture_id")
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
