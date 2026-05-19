package org.example.session5_bai5.service;

import org.example.session5_bai5.entity.Product;
import org.example.session5_bai5.repo .ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET PRODUCT BY ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // CREATE PRODUCT
    public Product createProduct(Product product) {

        // tránh lỗi update nhầm
        product.setId(null);

        return productRepository.save(product);
    }

    // UPDATE PRODUCT (PUT)
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        return productRepository.save(existingProduct);
    }

    // PATCH PRODUCT
    public Product patchProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            return null;
        }

        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }

        // double mặc định = 0
        if (product.getPrice() != 0) {
            existingProduct.setPrice(product.getPrice());
        }

        return productRepository.save(existingProduct);
    }

    // DELETE PRODUCT
    public boolean deleteProduct(Long id) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            return false;
        }

        productRepository.deleteById(id);

        return true;
    }
}