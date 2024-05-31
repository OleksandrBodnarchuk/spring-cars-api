package pl.alex.cars.api.running.repository;

import org.springframework.data.repository.CrudRepository;
import pl.alex.cars.api.running.entity.RunningFeature;

public interface RunningFeatureRepository extends CrudRepository<RunningFeature, Long>{

}
