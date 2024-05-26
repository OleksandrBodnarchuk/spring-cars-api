package pl.alex.cars.api.body;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import pl.alex.cars.api.modification.Modification;

@Getter
@Setter
@Entity
public class Body {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
