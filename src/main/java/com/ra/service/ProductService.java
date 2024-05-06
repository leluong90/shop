package com.ra.service;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.ProductRequestDTO;
import com.ra.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findProductByCategory(String category) throws ProductException ;
    List<Product> findAll() throws ProductException;
    Product create(ProductRequestDTO productRequestDTO) throws ProductException;
    Product update(Long id , ProductRequestDTO productRequestDTO) throws ProductException ;
    Product save(ProductRequestDTO productRequestDTO) throws ProductException;
    Product findById(Long id) throws ProductException;
    String delete(Long id) throws ProductException;

    Page<Product> getAllProduct(String category , List<String> colors , List<String> sizes , Integer minPrice , Integer maxPrice , Integer minDiscount , String sort , String stock , Integer pageNumber , Integer pageSize);

    Page<Product> getAll(Pageable pageable);
    Product add(ProductRequestDTO productRequest);
    Product edit(ProductRequestDTO productRequest, Long id);


    List<Product> getByNameOrDes(String name, String description);
    Page<Product> getByCategoryStatus(Pageable pageable, Boolean status);

    List<Product> getByCategoryId(Long id);
}
