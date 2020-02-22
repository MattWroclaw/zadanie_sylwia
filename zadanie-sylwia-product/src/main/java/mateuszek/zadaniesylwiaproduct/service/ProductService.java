package mateuszek.zadaniesylwiaproduct.service;

import lombok.RequiredArgsConstructor;
import mateuszek.zadaniesylwiaproduct.entity.Product;
import mateuszek.zadaniesylwiaproduct.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getPrductByCreditId(int id){
       return productRepository.findByCreditId(id);
    }

    public List<Product> allProducts(){
        return productRepository.findAll();
    }

}
