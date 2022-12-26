package pl.alex.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.alex.cars.entity.Model;


public interface ModelRepository extends JpaRepository<Model, Long>{

}
