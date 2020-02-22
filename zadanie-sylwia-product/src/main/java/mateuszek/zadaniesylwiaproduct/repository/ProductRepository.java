package mateuszek.zadaniesylwiaproduct.repository;

import mateuszek.zadaniesylwiaproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer > {
    Product findByCreditId(int id);
}
