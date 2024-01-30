package edu.poly.shirtpolofinal.service.Impl;

import edu.poly.shirtpolofinal.domain.PhanQuyen;
import edu.poly.shirtpolofinal.repository.PhanQuyenRepository;
import edu.poly.shirtpolofinal.service.PhanQuyenService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PhanQuyenServiceImpl implements PhanQuyenService {
    @Autowired
    private PhanQuyenRepository phanQuyenRepository;
    @Override
    public PhanQuyen findRole(int id) {
        Optional<PhanQuyen> role=phanQuyenRepository.findById(id);
        if(role.isPresent()){
            return role.get();
        }
        return null;
    }
}
