package com.ra.service.ipml;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.ProductRequestDTO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.repository.CategoryRepository;
import com.ra.repository.ProductRepository;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository ;
    @Autowired
    private CategoryService categoryService ;
    @Autowired
    private UserService userService ;

    public ProductServiceImpl(ProductRepository productRepository
            , UserService userService , CategoryRepository categoryRepository
    ){
        this.productRepository = productRepository ;
        this.userService = userService ;
        this.categoryRepository = categoryRepository ;

    }

    @Override
    public List<Product> findProductByCategory(String category) throws ProductException {
        return null;
    }

    @Override
    public List<Product> findAll() {

        return productRepository.findAll();
    }

    @Override
    public Product create(ProductRequestDTO productRequestDTO) {
        Category topLevel = categoryRepository.findByName(productRequestDTO.getTopLavelCategory());
        if (topLevel==null){
            Category topLavelCategory = new Category();
            topLavelCategory.setName(productRequestDTO.getTopLavelCategory());
            topLavelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLavelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParant(productRequestDTO.getSecondLavelCategory() , topLevel.getName());
        if(secondLevel==null){
            Category secondLavelCategory = new Category();
            secondLavelCategory.setName(productRequestDTO.getSecondLavelCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);
            secondLevel=categoryRepository.save(secondLavelCategory);


        }

        Category thirdLevel = categoryRepository.findByNameAndParant(productRequestDTO.getThirdLavelCategory() , secondLevel.getName());
        if(thirdLevel==null){
            Category thirdLavelCategory = new Category();
            thirdLavelCategory.setName(productRequestDTO.getThirdLavelCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);
            thirdLevel =categoryRepository.save(thirdLavelCategory);
        }

        Product product = new Product();
        product.setTitle(productRequestDTO.getTitle());
        product.setColor(productRequestDTO.getDescription());
        product.setDiscountedPrice(productRequestDTO.getDiscountedPrice());
        product.setDiscountedPersent(productRequestDTO.getDiscountedPercent());
        product.setImageUrl(productRequestDTO.getImageUrl());
        product.setBrand(productRequestDTO.getBrand());
        product.setPrice(productRequestDTO.getPrice());
        product.setSizes(productRequestDTO.getSizes());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);



        return savedProduct;
    }

    @Override
    public Product update(Long id, ProductRequestDTO productRequestDTO) throws ProductException {
        Product product = findById(id);
        if (productRequestDTO.getQuantity() != 0){
            product.setQuantity(productRequestDTO.getQuantity());

        }


        return productRepository.save(product);
    }

    @Override
    public Product save(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public Product findById(Long id) throws ProductException {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()){
            return opt.get();
        }throw new ProductException("Product not found with id" + id );
//        return productRepository.findById(id);
    }

    @Override
    public String delete(Long id) throws ProductException {
        Product product = findById(id);
        product.getSizes().clear();
        productRepository.deleteById(id);
        return "Product deleted Successfully" ;
    }

    @Override

    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber , pageSize);
        List<Product> products = productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);
        if (!colors.isEmpty()){
            products=products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }
        if(stock!=null){
            if(stock.equals("in_stock")){
                products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }

        int startIndex = (int) pageable.getOffset();

        int endIndex = Math.min(startIndex+ pageable.getPageSize() , products.size());
        List<Product> pageContent = products.subList(startIndex,endIndex);
        Page<Product> filterdContents =  new PageImpl<>(pageContent, pageable , products.size());
        return filterdContents;
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product add(ProductRequestDTO productRequest) {
//        if (productRepository.existsByTitle(productRequest.getTitle())){
//            throw new RuntimeException("Tên sản phẩm đã tồn tại!");
//        }
//
//        Category category = categoryService.findById(productRequest.getCategoryId());
//
//        if (category == null) {
//            throw new RuntimeException("Không tồn tại danh mục!");
//        }
//
//        Product product = Product.builder()
//                .sku(UUID.randomUUID().toString())
//                .title(productRequest.getTitle())
//                .description(productRequest.getDescription())
//                .price(productRequest.getPrice())
//                .quantity(productRequest.getQuantity())
//                .image(productRequest.getImageUrl())
//                .category(category)
//                .created(new java.sql.Date(new java.util.Date().getTime()))
//                .build();
//        return productRepository.save(product);
        return null ;
    }

    @Override
    public Product edit(ProductRequestDTO productRequest, Long id) {
        return null;
    }

    @Override
    public List<Product> getByNameOrDes(String name, String description) {
        return null;
    }

    @Override
    public Page<Product> getByCategoryStatus(Pageable pageable, Boolean status) {
        return null;
    }

    @Override
    public List<Product> getByCategoryId(Long id) {
        return null;
    }
}
