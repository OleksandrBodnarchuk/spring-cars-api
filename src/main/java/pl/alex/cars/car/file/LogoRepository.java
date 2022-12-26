package pl.alex.cars.car.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import pl.alex.cars.car.brand.Brand;

public interface LogoRepository extends JpaRepository<Logo, Long> {

}
