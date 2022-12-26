package pl.alex.cars.entity.details;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.entity.Modification;

@Getter
@Setter
@Entity
public class Body {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String length;
    private String width;
    private String height;
    private String wheelBase;
    private String weight;
    private String maxWidth;
    private String bootCapacity;
    
	@OneToOne(mappedBy = "body")
	private Modification modification;
}
