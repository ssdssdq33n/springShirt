package edu.poly.shirtpolofinal.repository;

import edu.poly.shirtpolofinal.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
