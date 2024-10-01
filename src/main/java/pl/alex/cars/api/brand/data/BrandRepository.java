package pl.alex.cars.api.brand.data;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Optional<Brand> findByNameEquals(String name);

	List<Brand> findBrandByNameIn(List<String> names);
}
