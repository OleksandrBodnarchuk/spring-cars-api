package pl.alex.cars.api.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface ModelRepository extends CrudRepository<Model, Long> {

  Page<ModelResponse> findAllByBrandName(String name, Pageable pageable);
}
