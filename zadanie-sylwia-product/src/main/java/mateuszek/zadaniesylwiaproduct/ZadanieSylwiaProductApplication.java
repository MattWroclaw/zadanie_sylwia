package mateuszek.zadaniesylwiaproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ZadanieSylwiaProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZadanieSylwiaProductApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}
}
