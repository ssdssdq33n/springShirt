package edu.poly.shirtpolofinal.service;

import edu.poly.shirtpolofinal.model.ImagesProductDTO;
import edu.poly.shirtpolofinal.model.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageProductService {
    ImagesProductDTO createImageProduct(MultipartFile file,int productId);
    ImagesProductDTO getImageProductId(int imageId);
    List<ImagesProductDTO> getAllImageProducts();
    ImagesProductDTO updateImageProduct(int imageId,MultipartFile file);
    void deleteImageProduct(int imageId);
    List<ImagesProductDTO> getAllProductId(int productId);
}
