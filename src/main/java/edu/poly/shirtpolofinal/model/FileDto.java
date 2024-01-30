package edu.poly.shirtpolofinal.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


public class FileDto implements Serializable {
    private MultipartFile file1;
    private int productId;
}
