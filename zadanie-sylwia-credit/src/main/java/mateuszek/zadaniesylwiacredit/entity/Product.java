package mateuszek.zadaniesylwiacredit.entity;

import lombok.Data;

@Data
public class Product {
    private int id;
    private int creditId;
    private int value;
    private String productName;
}
