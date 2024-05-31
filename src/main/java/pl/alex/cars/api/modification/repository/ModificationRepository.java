package pl.alex.cars.api.modification.repository;

import org.springframework.data.repository.CrudRepository;
import pl.alex.cars.api.modification.entity.Modification;

public interface ModificationRepository extends CrudRepository<Modification, Long>{

}
