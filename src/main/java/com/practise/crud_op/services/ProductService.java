package com.practise.crud_op.services;

import com.practise.crud_op.data_component.ProductComponent;
import com.practise.crud_op.repositries.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductComponent> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductComponent> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public ProductComponent addProduct(ProductComponent productComponent) {
        try {
            ProductComponent newProduct = new ProductComponent();
            newProduct = productRepository.save(productComponent);
            return newProduct;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
//        return productRepository.save(productComponent);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Optional<ProductComponent> getProductById(String id) {
        Integer productId = Integer.parseInt(id);
        try{
            return productRepository.findById(productId);
        }catch (Exception err){
            throw new RuntimeException(err);
        }
    }

    public Boolean updateProduct(String id, ProductComponent newProductData) {
        Integer productId = Integer.parseInt(id);
        try {
            Optional<ProductComponent> IsExistingProduct = productRepository.findById(productId);
            if (IsExistingProduct.isPresent()) {
                ProductComponent oldProductData = IsExistingProduct.get();
                oldProductData.setProductName(newProductData.getProductName());
                oldProductData.setProductDescription(newProductData.getProductDescription());
                oldProductData.setImg(newProductData.getImg());
                oldProductData.setBrand(newProductData.getBrand());
                oldProductData.setCategory(newProductData.getCategory());
                oldProductData.setPrice(newProductData.getPrice());
                ProductComponent updatedProduct = productRepository.save(oldProductData);
                return true;
            } else {
                return false;
            }
        } catch (Error err) {
            throw new RuntimeException("Error updating product", err);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Product ID");
        }
    }
}
