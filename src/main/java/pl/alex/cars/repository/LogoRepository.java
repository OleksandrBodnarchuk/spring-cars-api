package pl.alex.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import pl.alex.cars.entity.Logo;
import pl.alex.cars.entity.Manufacturer;

public interface LogoRepository extends JpaRepository<Logo, Long> {

}
