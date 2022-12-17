package pl.alex.cars.repository;

import org.springframework.data.repository.CrudRepository;

import pl.alex.cars.entity.Manufacturer;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {

}
