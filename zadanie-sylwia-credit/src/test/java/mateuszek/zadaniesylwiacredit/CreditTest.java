package mateuszek.zadaniesylwiacredit;

import mateuszek.zadaniesylwiacredit.entity.Credit;
import mateuszek.zadaniesylwiacredit.repository.CreditRepository;
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
public class CreditTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreditRepository creditRepository;

    @AfterEach
    public void tearDown() {
        creditRepository.deleteAll();
    }

    @Test
    public void getCredit_Test() throws Exception {
//        given
        Credit credit1 = new Credit();
        credit1.setCreditName("test1 name");

        Credit credit2 = new Credit();
        credit2.setCreditName("test2 name");

        creditRepository.save(credit1);
        creditRepository.save(credit2);
//        when
        mockMvc.perform(
                get("/credit/getcredits")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].creditName", is("test1 name")))
                .andExpect(jsonPath("$[1].creditName", is("test2 name")));

    }

    @Test
    public void createCredit() throws Exception {
//        given
        String creditName = "this id test name";
        String creditJson = ("{\"creditName\":\"{creditName}\", \"customer\": " +
                "{ \"id\":1}, \"product\": {\"id\":1}}")
                .replace("{creditName}", creditName);
//        when
        mockMvc.perform(
                post("/credit/createcredit").contentType(MediaType.APPLICATION_JSON)
                        .content(creditJson))
                .andExpect(status().isOk());
//

    }

}
