package pl.alex.cars.api.brand.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.alex.cars.api.brand.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Optional<Brand> findByNameEquals(String name);

	List<Brand> findBrandByNameIn(List<String> names);
}
