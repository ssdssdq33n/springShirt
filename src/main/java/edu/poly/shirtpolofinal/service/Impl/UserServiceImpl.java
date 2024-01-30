package edu.poly.shirtpolofinal.service.Impl;

import edu.poly.shirtpolofinal.domain.PhanQuyen;
import edu.poly.shirtpolofinal.domain.User;
import edu.poly.shirtpolofinal.exception.ResoureNotFoundException;
import edu.poly.shirtpolofinal.mapper.UserMapper;
import edu.poly.shirtpolofinal.model.AccountUser;
import edu.poly.shirtpolofinal.model.UserDTO;
import edu.poly.shirtpolofinal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements edu.poly.shirtpolofinal.service.UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO, PhanQuyen phanQuyen) {
        User user= UserMapper.toMapperUser(userDTO,phanQuyen);
        user.setMatkhau(bCryptPasswordEncoder.encode(user.getMatkhau()));
        User saveUser=userRepository.save(user);
        return UserMapper.toMapperUserDto(saveUser);
    }

    @Override
    public List<User> findUser(String tendangnhap) {
        List<User> user=userRepository.findByTendangnhapContaining(tendangnhap);
        return user;
    }

    @Override
    public User findAccount(AccountUser accountUser) {
        accountUser.setMatkhau(bCryptPasswordEncoder.encode(accountUser.getMatkhau()));
        System.out.println(accountUser.getMatkhau());
        User user=userRepository.findAccountUser(accountUser.getTendangnhap(),accountUser.getMatkhau());
        return user;
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users=userRepository.findAll();
        List<User> userAll=new ArrayList<>();
        for(User item:users){
            if(item.getPhanQuyen().getId()==2){
                userAll.add(item);
            }
        }
        return userAll.stream().map(user->UserMapper.toMapperUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(int id) {
        User user=userRepository.findById(id).orElseThrow(()->new ResoureNotFoundException("Employee is not exisst with given id"));
        userRepository.delete(user);
    }
}
