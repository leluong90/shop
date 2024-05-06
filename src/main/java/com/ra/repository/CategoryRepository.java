package com.ra.repository;

import com.ra.model.entity.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name );

    @Query("select c from Category c where c.name=:name and c.parentCategory.name=:parentCategoryName")

    Category findByNameAndParant(@Param("name") String name
            , @Param("parentCategoryName") String parentCategoryName);

    List<Category> findByStatus(boolean b);
}
