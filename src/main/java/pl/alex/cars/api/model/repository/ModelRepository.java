package pl.alex.cars.api.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.alex.cars.api.model.dto.ModelResponse;
import pl.alex.cars.api.model.entity.Model;


public interface ModelRepository extends CrudRepository<Model, Long> {

  Page<ModelResponse> findAllByBrandName(String name, Pageable pageable);
}
