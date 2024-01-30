package edu.poly.shirtpolofinal.repository;

import edu.poly.shirtpolofinal.domain.PhanQuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen,Integer> {
}
