package com.ra.repository;

import com.ra.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p"
            + " where (p.category.name =: category or :category='')"
            + " and ((:minPrice is null and :maxPrice is null ) or (p.discountedPrice between :minPrice and :maxPrice))"
            + " and (:minDiscount is null or p.discountedPersent >= :minDiscount )"
            + "order by "


            + "case when :sort='price_low' then p.discountedPrice end asc ,"
            + "case when :sort='price_high' then p.discountedPrice end desc "
            )
    public List<Product> filterProducts(@Param("category" ) String category
    ,@Param("minPrice") Integer minPrice
    ,@Param("maxPrice") Integer maxPrice
    ,@Param("minDiscount") Integer minDisCount
    ,@Param("sort") String sort) ;

    @Query("SELECT pro from Product pro WHERE pro.title like %?1% ")
    List<Product> findByNameOrDescription(String title, String description);
    boolean existsByTitle(String title);
    @Query("select p from Product p where p.category.status = :status")
    Page<Product> findByCategoryStatus(Pageable pageable, Boolean status);
    @Query("select p from Product p where p.category.id = :id")
    List<Product> findByCategoryId(Long id);
}

