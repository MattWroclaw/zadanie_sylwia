package mateuszek.zadaniesylwiacustomer;

import mateuszek.zadaniesylwiacustomer.entity.Customer;
import mateuszek.zadaniesylwiacustomer.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CustomerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void getCustomerWithCreditId1() throws Exception {
//        given
        Customer customer1 = new Customer();
        customer1.setFirstName("name test");
        customer1.setSurname("surname test");
        customer1.setCredtiId(1);
        customer1.setPesel("0000");

        customerRepository.save(customer1);
//        when

        mockMvc.perform(
                get("/customer/id=1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.pesel", is("0000")));
    }

    @Test
    public void createCustomer() throws Exception {
//        given
        String toReplace = "test first name";
        String customerJson = ("{\"firstName\":\"{name}\"}  ")
                .replace("{name}", toReplace);

        mockMvc.perform(
                post("/customer/addcustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk());

    }

}
