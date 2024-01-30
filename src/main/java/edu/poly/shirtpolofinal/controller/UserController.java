package edu.poly.shirtpolofinal.controller;

import edu.poly.shirtpolofinal.domain.PhanQuyen;
import edu.poly.shirtpolofinal.domain.User;
import edu.poly.shirtpolofinal.model.AccountUser;
import edu.poly.shirtpolofinal.model.EmailDto;
import edu.poly.shirtpolofinal.model.UserDTO;
import edu.poly.shirtpolofinal.repository.UserRepository;
import edu.poly.shirtpolofinal.service.Impl.MailSenderServiceImpl;
import edu.poly.shirtpolofinal.service.PhanQuyenService;
import edu.poly.shirtpolofinal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("accounts")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public PhanQuyenService phanQuyenService;

    @Autowired
    public MailSenderServiceImpl mailSenderService;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRepository userRepository;

    @PostMapping("email")
    public ResponseEntity<String> getEmail(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getTendangnhap());
        List<User> user=userService.findUser(userDTO.getTendangnhap());
        for(User item:user){
            if (item!=null){
                System.out.println(user);
                return ResponseEntity.ok("ton tai");
            }
        }
        Random rd=new Random();
        String s1= rd.nextInt(10)  +"";
        String s2= rd.nextInt(10)  +"";
        String s3= rd.nextInt(10)  +"";
        String s4= rd.nextInt(10)  +"";
        String s5= rd.nextInt(10)  +"";
        String s6= rd.nextInt(10)  +"";
        String soRanDom=s1+s2+s3+s4+s5+s6;
        mailSenderService.sendMail(userDTO.getEmail(), "Mã xác thực email của bạn ",soRanDom);
        return ResponseEntity.ok(soRanDom);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO ){
        System.out.println(userDTO);
        PhanQuyen phanQuyen=phanQuyenService.findRole(userDTO.getId());
        UserDTO saveUserDto=userService.createUser(userDTO,phanQuyen);
        return new ResponseEntity<>(saveUserDto, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody AccountUser accountUser){
        System.out.println(accountUser);
        List<User> user= userService.findUser(accountUser.getTendangnhap());
        for(User item:user){
            if(item!=null && bCryptPasswordEncoder.matches(accountUser.getMatkhau(), item.getMatkhau())){
                return ResponseEntity.ok("thanh cong");
            }
        }
        return ResponseEntity.ok("That bai");
    }

    @PostMapping("khoiphuc")
    public ResponseEntity<String> khoiphucUser(@RequestBody AccountUser accountUser){
        System.out.println(accountUser);
        List<User> user= userService.findUser(accountUser.getTendangnhap());
        for(User item:user){
           if(item!=null){
               item.setMatkhau(bCryptPasswordEncoder.encode(accountUser.getMatkhau()));
               userRepository.save(item);
               return ResponseEntity.ok("thanh cong");
           }
        }
        return ResponseEntity.ok("That bai");
    }

    @PostMapping("xacthuc")
    public ResponseEntity<String> xacthucEmail(@RequestBody EmailDto email){
        System.out.println(email.getEmail());
        Random rd=new Random();
        String s1= rd.nextInt(10)  +"";
        String s2= rd.nextInt(10)  +"";
        String s3= rd.nextInt(10)  +"";
        String s4= rd.nextInt(10)  +"";
        String s5= rd.nextInt(10)  +"";
        String s6= rd.nextInt(10)  +"";
        String soRanDom=s1+s2+s3+s4+s5+s6;
        mailSenderService.sendMail(email.getEmail(), "Mã xác thực email của bạn ",soRanDom);
        return ResponseEntity.ok(soRanDom);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> userDTOS=userService.getAllUser();
        return ResponseEntity.ok(userDTOS);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id ){
        System.out.println(id);
        userService.deleteUser(id);
        return ResponseEntity.ok("thanh cong");
    }
}
