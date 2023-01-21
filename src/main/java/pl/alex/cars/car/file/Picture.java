package pl.alex.cars.car.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.car.modification.Modification;

@Getter
@Setter
@Entity
@Table(name = "picture")
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Lob
	@Column(name = "data", columnDefinition = "MEDIUMBLOB")
	private byte[] data;
	private boolean isMainPicture;

	@OneToOne(mappedBy = "picture")
	private Modification modification;

}
