package mateuszek.zadaniesylwiacredit.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Testowanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;
}
