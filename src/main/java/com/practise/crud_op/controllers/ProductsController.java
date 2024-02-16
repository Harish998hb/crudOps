package com.practise.crud_op.controllers;

import com.practise.crud_op.data_component.ProductComponent;
import com.practise.crud_op.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductComponent>> getProducts() {
//        productService.getAllProducts();
        List<ProductComponent> productList = productService.getAllProducts();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductComponent>> getProductsByCategory(@PathVariable String category) {
        List<ProductComponent> productsByCategory = new ArrayList<>();
        productService.getProductsByCategory(category).forEach(productsByCategory::add);
        System.out.println(productsByCategory);
        if (productsByCategory.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductComponent>> getProductById(@PathVariable String id) {
        try {
            Optional<ProductComponent> specificProduct = productService.getProductById(id);
            if (!specificProduct.isEmpty()) {
                return new ResponseEntity<>(specificProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ProductComponent> addProduct(@RequestBody ProductComponent productComponent) {
        ProductComponent newProduct = productService.addProduct(productComponent);
        if (newProduct.getId() != null) {
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody ProductComponent productComponent) {
        try {
            Boolean productIsValid = productService.updateProduct(id, productComponent);
            if (productIsValid) return new ResponseEntity("Updated Successfully", HttpStatus.OK);
            else return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<>("Error in Updating", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Record deleted", HttpStatus.OK);
        } catch (Error err) {
            return new ResponseEntity<>("Error in Deleting", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
