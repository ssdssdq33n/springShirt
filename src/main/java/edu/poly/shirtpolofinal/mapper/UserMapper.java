package edu.poly.shirtpolofinal.mapper;

import edu.poly.shirtpolofinal.domain.PhanQuyen;
import edu.poly.shirtpolofinal.domain.User;
import edu.poly.shirtpolofinal.model.UserDTO;
import edu.poly.shirtpolofinal.service.PhanQuyenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserMapper {
    public static UserDTO toMapperUserDto(User user){
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getTendangnhap(),
                user.getMatkhau(),
                user.getPhanQuyen().getId()
        );
    }

    public static User toMapperUser(UserDTO userDTO,PhanQuyen phanQuyen){
        return new User(
                userDTO.getUserId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getTendangnhap(),
                userDTO.getMatkhau(),
                phanQuyen
        );
    }
}
