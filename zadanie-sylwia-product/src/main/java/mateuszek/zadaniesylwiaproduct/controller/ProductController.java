package mateuszek.zadaniesylwiaproduct.controller;

import lombok.RequiredArgsConstructor;
import mateuszek.zadaniesylwiaproduct.entity.Product;
import mateuszek.zadaniesylwiaproduct.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/addproduct")
    private void addProduct(@RequestBody final Product product){
        productService.saveProduct(product);
    }

    @GetMapping("/id={id}")
    private Product getProductByCreditId(@PathVariable ("id") int id){
        return  productService.getPrductByCreditId(id);
    }

    @GetMapping("/all")
    private List<Product> allProducts(){
        return productService.allProducts();
    }
}
