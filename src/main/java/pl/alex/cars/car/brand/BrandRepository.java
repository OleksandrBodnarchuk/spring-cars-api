package pl.alex.cars.car.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
	
	Optional<Brand> findByNameEquals(String name);

	List<Brand> findBrandByNameIn(List<String> names);
}
