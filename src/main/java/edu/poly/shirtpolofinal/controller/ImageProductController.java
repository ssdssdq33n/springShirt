package edu.poly.shirtpolofinal.controller;

import edu.poly.shirtpolofinal.config.StorageProperties;
import edu.poly.shirtpolofinal.exception.StorageException;
import edu.poly.shirtpolofinal.model.FileDto;
import edu.poly.shirtpolofinal.model.ImagesProductDTO;
import edu.poly.shirtpolofinal.service.ImageProductService;
import edu.poly.shirtpolofinal.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("images")
public class ImageProductController {
    @Autowired
    public ImageProductService imageProductService;

    @Autowired
    public StorageService storageService;

    @PostMapping(value="layra/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> layra(@RequestParam MultipartFile file,@PathVariable int id){
        System.out.println(file);
        System.out.println(id);
        return ResponseEntity.ok("thanh cong");
    }

    @GetMapping("/imagesUpload/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, HttpServletRequest request){
        Resource file=storageService.loadAsResource(filename);
        String contentType=null;
        try {
            contentType=request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        }catch (Exception exception){
            throw  new StorageException("Counld not determine file type");
        }
        if (contentType==null){
            contentType="application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

    @PostMapping(value="{productId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImagesProductDTO> createImageProduct(@RequestParam MultipartFile file,@PathVariable int productId){
        ImagesProductDTO imagesProductDTOCreate=imageProductService.createImageProduct(file,productId);
        return new ResponseEntity<>(imagesProductDTOCreate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ImagesProductDTO>> getAllImageProduct(){
        List<ImagesProductDTO> imagesProductDTOList=imageProductService.getAllImageProducts();
        return ResponseEntity.ok(imagesProductDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ImagesProductDTO> getByImageId( @PathVariable("id") int imageId){
        ImagesProductDTO imagesProductDTO=imageProductService.getImageProductId(imageId);
        return ResponseEntity.ok(imagesProductDTO);
    }

    @PutMapping(value="{imageId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImagesProductDTO> updateImageProduct(@PathVariable("imageId") int imageId,@RequestParam MultipartFile file){
        ImagesProductDTO imagesProductDTOUpdate=imageProductService.updateImageProduct(imageId,file);
        return ResponseEntity.ok(imagesProductDTOUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteImageProduct(@PathVariable("id") int imageId){
        imageProductService.deleteImageProduct(imageId);
        return ResponseEntity.ok("thanh cong");
    }

    @GetMapping("api/{productId}")
    public ResponseEntity<List<ImagesProductDTO>> getProductId(@PathVariable("productId") int productId){
        List<ImagesProductDTO> imagesProductDTOList=imageProductService.getAllProductId(productId);
        return ResponseEntity.ok(imagesProductDTOList);
    }
}
