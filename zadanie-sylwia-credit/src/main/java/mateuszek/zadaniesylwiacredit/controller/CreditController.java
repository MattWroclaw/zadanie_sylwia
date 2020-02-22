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

    @PostMapping("/createcredit")
    public int createCredit(@RequestBody final Credit credit) {

        Credit savedCredit = creditService.saveCredit(credit);
        int creditId = savedCredit.getId(); // get creditId from newly saved object credit
        savedCredit.getCustomer().setCredtiId(creditId); // from the credit get the customer, set the field creditId with creditId taken from above
        restTemplate.postForObject("http://localhost:8300/customer/addcustomer",
                credit.getCustomer(), Customer.class); // pass customer to other service where it will be saved id DB

        savedCredit.getProduct().setCreditId(creditId);
        restTemplate.postForObject("http://localhost:8302/product/addproduct",
                credit.getProduct(), Product.class);
        return creditId;
    }

    @GetMapping("/getcredits")
    public List<Credit> getCredits() {
        List<Credit> creditList = repository.findAll(); // we take all the credits
        List<Credit> returnedList = new ArrayList<>(); // this list will be used to store required objects
        for (int i = 0; i < creditList.size(); i++) { // we iterate over the list of credits..
            Credit singleCredit = creditList.get(i); // ..for each iteration take a single credit..
            int creditId = singleCredit.getId(); // ... from single credit we get to know the id of it


//            **********************************************************************************************
//            Customer customer = restTemplate.exchange("http://localhost:8300/customer/id=" + creditId,
//                    HttpMethod.GET, null, new ParameterizedTypeReference<Customer>() {
//                    }).getBody(); // we pass query to other service to find that (here) customer by credit id
//            singleCredit.setCustomer(customer); // set received object (here customer) to appropriate credit..
//
//            // We do the same to product
//            Product product = restTemplate.exchange("http://localhost:8302/product/id=" + creditId,
//                    HttpMethod.GET, null, new ParameterizedTypeReference<Product>() {
//                    }).getBody();
//            singleCredit.setProduct(product);
//      *************************************************************************************************

            Customer customer = restTemplate.exchange("http://customer:8300/customer/id=" + creditId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<Customer>() {
                    }).getBody(); // we pass query to other service to find that (here) customer by credit id
            singleCredit.setCustomer(customer); // set received object (here customer) to appropriate credit..

            // We do the same to product
            Product product = restTemplate.exchange("http://product:8302/product/id=" + creditId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<Product>() {
                    }).getBody();
            singleCredit.setProduct(product);

//            ********************************************************************

            returnedList.add(singleCredit); // add to the collective list that will be returned
        }
        return creditList;
    }
}
