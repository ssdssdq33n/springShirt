package edu.poly.shirtpolofinal.repository;

import edu.poly.shirtpolofinal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByTendangnhapContaining(String tendangnhap);

    @Query(value = "SELECT u1_0 FROM User u1_0 WHERE u1_0.tendangnhap= ?1 AND u1_0.matkhau = ?2")
    User findAccountUser(String tendangnhap,String matkhau);
}
