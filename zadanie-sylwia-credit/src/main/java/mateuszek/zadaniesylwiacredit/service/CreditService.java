package mateuszek.zadaniesylwiacredit.service;

import lombok.RequiredArgsConstructor;
import mateuszek.zadaniesylwiacredit.entity.Credit;
import mateuszek.zadaniesylwiacredit.repository.CreditRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository repository;

    @Transactional
    public Credit saveCredit(Credit credit){
        repository.saveAndFlush(credit);
        return credit;
    }

    public List<Credit> creditList(){
        return repository.findAll();
    }

}
