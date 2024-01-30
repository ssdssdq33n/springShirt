package edu.poly.shirtpolofinal.service;

import edu.poly.shirtpolofinal.domain.PhanQuyen;
import edu.poly.shirtpolofinal.domain.User;
import edu.poly.shirtpolofinal.model.AccountUser;
import edu.poly.shirtpolofinal.model.UserDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface UserService {
     UserDTO createUser(UserDTO userDTO, PhanQuyen phanQuyen);

     List<User> findUser(String tendangnhap);

     List<UserDTO> getAllUser();

     void deleteUser(int id);

     User findAccount(AccountUser accountUser);
}
