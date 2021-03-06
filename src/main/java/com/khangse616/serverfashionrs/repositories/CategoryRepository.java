package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where (c.id=:id1 and c.level = 1) or ( c.id = :id2 and c.level=2) or (c.id=:id3 and c.level=3)")
    List<Category> findAllCategoryByProduct(@Param("id1") int id1, @Param("id2") int id2, @Param("id3") int id3);

//    @Query("select c.products from Category c where c.categoryPath=:path")
//    List<Product> findNProduct(@Param("path") String path, Pageable pageable);

    List<Category> findByLevel(int level);

    @Query(value = "select * from categories where parent_id=:parentId", nativeQuery = true)
    Set<Category> findByParentId(@Param("parentId") int parentId);

    @Query("select c.name from Category c where c.level<>0")
    List<String> getAllName();

    Category findCategoryById(int id);

    @Query(value = "select * from categories order by id asc", nativeQuery = true)
    List<Category> getAllCategoryOrderById();

    @Query(value="select c.path from categories c where c.id = :id", nativeQuery = true)
    String findPathCategory(@Param("id") int id);

    @Query("select c from Category c where c.name like %:search% order by c.level")
    Page<Category> findAllCategoriesOrderByLevel(@Param("search") String search, Pageable pageable);

    @Query("select count(c) from Category c where c.name like %:search% order by c.level")
    int countCategories(@Param("search") String search);
}