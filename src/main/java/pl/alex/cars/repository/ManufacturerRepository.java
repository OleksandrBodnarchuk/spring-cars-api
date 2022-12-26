package pl.alex.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.alex.cars.entity.Brand;

public interface ManufacturerRepository extends JpaRepository<Brand, Long> {

}
