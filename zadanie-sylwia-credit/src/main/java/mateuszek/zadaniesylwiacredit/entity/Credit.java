package mateuszek.zadaniesylwiacredit.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String creditName;

    @Transient
    private Customer customer;

    @Transient
    private Product product;
}
