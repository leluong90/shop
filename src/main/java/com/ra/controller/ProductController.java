package com.ra.controller;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.ProductRequestDTO;
import com.ra.model.entity.Product;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService ;
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(
            @RequestParam String category ,
            @RequestParam List<String> color ,
            @RequestParam List<String> size ,
            @RequestParam Integer minPrice ,
            @RequestParam Integer maxPrice ,
            @RequestParam Integer minDiscount ,
            @RequestParam String sort ,
            @RequestParam String stock ,
            @RequestParam Integer pageNumber ,
            @RequestParam Integer pagesize
    ){
        Page<Product> res = productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pagesize);
        System.out.println("complete product");
        return new ResponseEntity<>(res , HttpStatus.OK);

    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId)throws ProductException{
        Product product = productService.findById(productId);
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "name", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order
    ) {
        Pageable pageable;
        if (order.equals("asc")) {
            pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        }
        Page<Product> products = productService.getAll(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) throws ProductException {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>("Sản phẩm không tồn tại", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDTO productRequest) {
        Product product = productService.add(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(
            @PathVariable("id") Long id,
            @RequestBody ProductRequestDTO productRequest
    ) {
        Product product = productService.edit(productRequest, id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) throws ProductException {
        productService.delete(id);
        return new ResponseEntity<>("Xóa sản phẩm thành công",HttpStatus.OK);
    }

//    @GetMapping("/products/search")
//    public ResponseEntity<List<Product>>searchProductHandler(@RequestParam String q){
//        List<Product> products = productService.searchProduct(q);
//    }
}
