package mateuszek.zadaniesylwiacustomer.service;

import lombok.RequiredArgsConstructor;
import mateuszek.zadaniesylwiacustomer.entity.Customer;
import mateuszek.zadaniesylwiacustomer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public void saveCustomer(Customer customer) {
        repository.save(customer);
    }

    public Customer getCustByCreditID(int id) {
        return repository.getCustomerByCredtiId(id);
    }

    public List<Customer> showAllCustomers() {
        return repository.findAll();
    }
}
