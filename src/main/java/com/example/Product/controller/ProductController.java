package com.example.Product.controller;
import com.example.Product.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Product.repository.ProductRepo;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo repo;

    @GetMapping("/allProducts")
    public List<ProductEntity> getProducts()
    {
       return repo.findAll();
    }

    @GetMapping("/productId/{id}")
    public ProductEntity getByproductId(@PathVariable int id) {
        return repo.findById(id).orElseThrow(() ->
                new RuntimeException("product not found with Id" + id));
    }



}
