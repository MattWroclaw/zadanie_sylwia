package mateuszek.zadaniesylwiaproduct;

import mateuszek.zadaniesylwiaproduct.entity.Product;
import mateuszek.zadaniesylwiaproduct.repository.ProductRepository;
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

public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void getProductWithCreditId_1() throws Exception {
//        given
        Product pr1 = new Product();
        pr1.setCreditId(1);
        pr1.setProductName("Product eins");
        pr1.setValue(100);

        productRepository.save(pr1);
//        when
        mockMvc.perform(
                get("/product/id=1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is("Product eins")));
    }

    @Test
    public void createProduct() throws Exception {
//        given
        String productName = "test  name";
        int value = 100;

        String productJson = ("{\"creditId\":1,\"value\":100,\"productName\":\"{name}\"   }")
                .replace("{name}", productName);
        mockMvc.perform(
                post("/product/addproduct").contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk());

    }
}
