package com.ra.controller;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.CreateProductRequest;
import com.ra.model.dto.request.ProductRequestDTO;
import com.ra.model.dto.response.ApiResponse;
import com.ra.model.entity.Product;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService ;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct (@RequestBody ProductRequestDTO productRequestDTO) throws ProductException{
        Product product = productService.create(productRequestDTO);
        return new ResponseEntity<Product>(product , HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId ) throws ProductException {
        productService.delete(productId);
        ApiResponse res = new ApiResponse();

        res.setMessage("Order deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>>findAllProduct() throws ProductException {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products , HttpStatus.OK);
    }
    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequestDTO productRequestDTO , @PathVariable Long productId) throws ProductException {
        Product product = productService.update(productId, productRequestDTO);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody ProductRequestDTO[] req) throws ProductException {
        try {
            for (ProductRequestDTO product:req ){
                productService.create(product);

            }
            ApiResponse res = new ApiResponse();
            res.setMessage("Product created successfully");
            res.setStatus(true);
            return new ResponseEntity<>(res,HttpStatus.CREATED);

        }catch (Exception exception){
            System.err.println(exception.getMessage());
            return null ;
        }

    }
}

