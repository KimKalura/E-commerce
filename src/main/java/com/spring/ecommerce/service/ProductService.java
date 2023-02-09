package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.AddProductDTO;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.CategoryRepository;
import com.spring.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Access;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional//(readOnly = true)
public class ProductService {
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;


    public ProductService(@Autowired ProductRepository productRepository, @Autowired CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public Product addProduct(AddProductDTO productDTO, Long categoryId) {
        Product productToBeSaved = new Product();
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category was not found"));
        productToBeSaved.setCategory(foundCategory);
        productToBeSaved.setDescription(productDTO.getDescription());
        productToBeSaved.setName(productDTO.getName());
        productToBeSaved.setPrice(productDTO.getPrice());
        return productRepository.save(productToBeSaved);
    }

    //v2, salvam categoria:
   /* public void addProduct(AddProductDTO productDTO, Long categoryId) {
        Product productToBeSaved = new Product();
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the category you want to update was not found"));
       // productToBeSaved.setCategory(foundCategory);
        productToBeSaved.setDescription(productDTO.getDescription());
        productToBeSaved.setName(productDTO.getName());
        productToBeSaved.setPrice(productDTO.getPrice());

        foundCategory.getProductList().add(productToBeSaved);
        categoryRepository.save(foundCategory);

        // return productRepository.save(productToBeSaved);
    }*/

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByCategory(Long categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category does not exist"));
        return productRepository.findAllByCategory(foundedCategory);
    }


    public Product updateProduct(AddProductDTO productDTO, Long productId) {
        Product productToBeUpdated = new Product();
        Category foundedCategory = categoryRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the product you want to update was not found"));
        productToBeUpdated.setCategory(foundedCategory);
        productToBeUpdated.setDescription(productDTO.getDescription());
        productToBeUpdated.setName(productDTO.getName());
        productToBeUpdated.setPrice(productDTO.getPrice());
        return productRepository.save(productToBeUpdated);
    }

}
