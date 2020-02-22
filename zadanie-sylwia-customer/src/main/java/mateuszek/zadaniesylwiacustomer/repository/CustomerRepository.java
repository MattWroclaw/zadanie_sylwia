package mateuszek.zadaniesylwiacustomer.repository;

import mateuszek.zadaniesylwiacustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer getCustomerByCredtiId(int id);
}
