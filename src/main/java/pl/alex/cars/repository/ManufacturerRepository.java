package pl.alex.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.alex.cars.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
