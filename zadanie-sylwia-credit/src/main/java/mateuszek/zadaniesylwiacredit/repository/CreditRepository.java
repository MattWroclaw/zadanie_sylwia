package mateuszek.zadaniesylwiacredit.repository;

import mateuszek.zadaniesylwiacredit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreditRepository extends JpaRepository<Credit, Integer> {

}
