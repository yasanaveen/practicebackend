package com.example.Product.controller;

import com.example.Product.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.Product.repository.ProductRepo;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo repo;

    @PostMapping("/addProduct")
    public ProductEntity creteProduct(@RequestBody ProductEntity prod)
    {
        return repo.save(prod);
    }

    @GetMapping("/allProducts")
    public List<ProductEntity> getProducts() {
        return repo.findAll();
    }

    @GetMapping("/productId/{id}")
    public ProductEntity getByproductId(@PathVariable int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with Id: " + id));
    }

    @PutMapping("/updateByid/{id}")
    public ProductEntity updateByid(
            @RequestBody ProductEntity product,
            @PathVariable int id) {

        ProductEntity existingProduct = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with Id: " + id
                ));

        existingProduct.setProductName(product.getProductName());
        System.out.println("Product updated successfully!");
        return repo.save(existingProduct);
    }

    @DeleteMapping("/deleteByid{id}")
    public String deleteProductByid(@PathVariable int id) {
        repo.findById(id);
        return id + " deleted successfully";
    }




}
