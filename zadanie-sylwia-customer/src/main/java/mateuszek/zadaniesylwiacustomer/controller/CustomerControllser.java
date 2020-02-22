package mateuszek.zadaniesylwiacustomer.controller;

import lombok.RequiredArgsConstructor;
import mateuszek.zadaniesylwiacustomer.entity.Customer;
import mateuszek.zadaniesylwiacustomer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerControllser {

    private final CustomerService customerService;

    @PostMapping(value = "/addcustomer")
    private void addCustomer(@RequestBody final Customer customer) {
        customerService.saveCustomer(customer);
    }

//    @GetMapping("/getallcustomers")
//    private List<Customer> getAllCustomers() {
//        List<Customer> allCustomers = customerService.showAllCustomers();
//        return allCustomers;
//    }

    @GetMapping(value = "/id={id}")
    private Customer getCustomerByCreditId(@PathVariable("id") int id) {
        Customer customerByCreditId = customerService.getCustByCreditID(id);
        return customerByCreditId;
    }
}
