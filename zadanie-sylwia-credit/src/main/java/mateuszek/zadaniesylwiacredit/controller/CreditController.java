package mateuszek.zadaniesylwiacredit.controller;

import lombok.RequiredArgsConstructor;
import mateuszek.zadaniesylwiacredit.entity.Credit;
import mateuszek.zadaniesylwiacredit.entity.Customer;
import mateuszek.zadaniesylwiacredit.entity.Product;
import mateuszek.zadaniesylwiacredit.repository.CreditRepository;
import mateuszek.zadaniesylwiacredit.service.CreditService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/credit")
@RequiredArgsConstructor
public class CreditController {
    private final RestTemplate restTemplate;
    private final CreditRepository repository;
    private final CreditService creditService;

//    @PostMapping("/createcredit")
//    public int createCredit(@RequestBody final Credit credit) {
//
//        Credit savedCredit = creditService.saveCredit(credit);
//        int creditId = savedCredit.getId(); // get creditId from newly saved object credit
//        savedCredit.getCustomer().setCredtiId(creditId); // from the credit get the customer, set the field creditId with creditId taken from above
//        restTemplate.postForObject("http://localhost:8300/customer/addcustomer",
//                credit.getCustomer(), Customer.class); // pass customer to other service where it will be saved id DB
//
//        savedCredit.getProduct().setCreditId(creditId);
//        restTemplate.postForObject("http://localhost:8302/product/addproduct",
//                credit.getProduct(), Product.class);
//        return creditId;
//    }

    @PostMapping("/createcredit")
    public int createCredit(@RequestBody final Credit credit) {

        Credit savedCredit = creditService.saveCredit(credit);
        int creditId = savedCredit.getId(); // get creditId from newly saved object credit
        savedCredit.getCustomer().setCredtiId(creditId); // from the credit get the customer, set the field creditId with creditId taken from above
        restTemplate.postForObject("http://customer:8300/customer/addcustomer",
                credit.getCustomer(), Customer.class); // pass customer to other service where it will be saved id DB

        savedCredit.getProduct().setCreditId(creditId);
        restTemplate.postForObject("http://product:8302/product/addproduct",
                credit.getProduct(), Product.class);
        return creditId;
    }

//
    @GetMapping("/getcredits")
    public List<Credit> getCredits() {
        List<Credit> creditList = repository.findAll(); // we take all the credits
        creditList.forEach(e -> {
            Customer customer = restTemplate.exchange("http://customer:8300/customer/id=" + e.getId(),
                    HttpMethod.GET, null, Customer.class).getBody();
            Product product = restTemplate.exchange("http://product:8302/product/id=" + e.getId(),
                    HttpMethod.GET, null, Product.class).getBody();
            e.setCustomer(customer);
            e.setProduct(product);
        });
        return creditList;
    }

//    @GetMapping("/getcredits")
//    public List<Credit> getCredits() {
//        List<Credit> creditList = repository.findAll(); // we take all the credits
//        creditList.forEach(e -> {
//            Customer customer = restTemplate.exchange("http://localhost:8300/customer/id=" + e.getId(),
//                    HttpMethod.GET, null, Customer.class).getBody();
//            Product product = restTemplate.exchange("http://localhost:8302/product/id=" + e.getId(),
//                    HttpMethod.GET, null, Product.class).getBody();
//            e.setCustomer(customer);
//            e.setProduct(product);
//        });
//        return creditList;
//    }


}
