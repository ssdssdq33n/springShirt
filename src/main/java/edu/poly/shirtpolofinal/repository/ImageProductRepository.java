package edu.poly.shirtpolofinal.repository;

import edu.poly.shirtpolofinal.domain.ImagesProduct;
import edu.poly.shirtpolofinal.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageProductRepository extends JpaRepository<ImagesProduct,Integer> {
    @Query(value = "SELECT i1_0 FROM ImagesProduct i1_0 WHERE i1_0.product= ?1")
    List<ImagesProduct> findByProduct(Product product);
}
