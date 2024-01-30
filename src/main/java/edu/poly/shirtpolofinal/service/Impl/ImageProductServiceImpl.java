package edu.poly.shirtpolofinal.service.Impl;

import edu.poly.shirtpolofinal.domain.ImagesProduct;
import edu.poly.shirtpolofinal.domain.Product;
import edu.poly.shirtpolofinal.exception.ResoureNotFoundException;
import edu.poly.shirtpolofinal.mapper.ImageProductMapper;
import edu.poly.shirtpolofinal.mapper.ProductMapper;
import edu.poly.shirtpolofinal.model.ImagesProductDTO;
import edu.poly.shirtpolofinal.model.ProductDTO;
import edu.poly.shirtpolofinal.repository.ImageProductRepository;
import edu.poly.shirtpolofinal.service.ImageProductService;
import edu.poly.shirtpolofinal.service.ProductService;
import edu.poly.shirtpolofinal.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ImageProductServiceImpl implements ImageProductService {
    @Autowired
    private ImageProductRepository imageProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;
    @Override
    public ImagesProductDTO createImageProduct(MultipartFile file,int productId) {
        ProductDTO productDTO=productService.getProductId(productId);
        Product product= ProductMapper.toMapperProduct(productDTO,null);
        ImagesProduct imagesProduct=new ImagesProduct();
        imagesProduct.setProduct(product);
        if(!file.isEmpty()){
            UUID uuid=UUID.randomUUID();
            String uuString=uuid.toString();

            imagesProduct.setImageProduct(storageService.getStoredFilename(file,uuString));
            storageService.store(file,imagesProduct.getImageProduct());
        }
        ImagesProduct imagesProductSave=imageProductRepository.save(imagesProduct);
        return ImageProductMapper.toImageProductDTO(imagesProductSave);
    }

    @Override
    public ImagesProductDTO getImageProductId(int imageId) {
        ImagesProduct imagesProduct=imageProductRepository.findById(imageId).orElseThrow(()->new ResoureNotFoundException("ImageProduct is not exisst with given id"));
        return ImageProductMapper.toImageProductDTO(imagesProduct);
    }

    @Override
    public List<ImagesProductDTO> getAllImageProducts() {
        List<ImagesProduct> imagesProducts=imageProductRepository.findAll();
        return imagesProducts.stream().map(item->ImageProductMapper.toImageProductDTO(item)).collect(Collectors.toList());
    }

    @Override
    public ImagesProductDTO updateImageProduct(int imageId,MultipartFile file) {
        ImagesProduct imagesProduct=imageProductRepository.findById(imageId).orElseThrow(()->new ResoureNotFoundException("ImageProduct is not exisst with given id"));
        if(!file.isEmpty()){
            UUID uuid=UUID.randomUUID();
            String uuString=uuid.toString();

            imagesProduct.setImageProduct(storageService.getStoredFilename(file,uuString));
            storageService.store(file,imagesProduct.getImageProduct());
        }
        ImagesProduct imagesProductSave=imageProductRepository.save(imagesProduct);
        return ImageProductMapper.toImageProductDTO(imagesProductSave);
    }

    @Override
    public void deleteImageProduct(int imageId) {
        ImagesProduct imagesProduct=imageProductRepository.findById(imageId).orElseThrow(()->new ResoureNotFoundException("ImageProduct is not exisst with given id"));
        imageProductRepository.delete(imagesProduct);
    }

    @Override
    public List<ImagesProductDTO> getAllProductId(int productId) {
        ProductDTO productDTO=productService.getProductId(productId);
        Product product= ProductMapper.toMapperProduct(productDTO,null);
        List<ImagesProduct> imagesProducts=imageProductRepository.findByProduct(product);
        return imagesProducts.stream().map(item->ImageProductMapper.toImageProductDTO(item)).collect(Collectors.toList());
    }
}
