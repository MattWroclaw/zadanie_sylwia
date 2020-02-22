package mateuszek.zadaniesylwiacredit.entity;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String firstName;
    private String surname;
    private String pesel;
    private int credtiId;

}
