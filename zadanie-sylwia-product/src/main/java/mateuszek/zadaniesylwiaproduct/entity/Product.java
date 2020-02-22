package mateuszek.zadaniesylwiaproduct.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int creditId;
    private int value;
    private String productName;
}
