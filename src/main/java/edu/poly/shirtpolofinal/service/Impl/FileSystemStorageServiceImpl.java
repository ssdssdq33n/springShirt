package edu.poly.shirtpolofinal.service.Impl;

import edu.poly.shirtpolofinal.config.StorageProperties;
import edu.poly.shirtpolofinal.exception.StorageException;
import edu.poly.shirtpolofinal.exception.StorageFileNotFoundException;
import edu.poly.shirtpolofinal.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageServiceImpl implements StorageService {
    private final Path rootLocation;

    @Override
    public String getStoredFilename(MultipartFile file, String id){
        String ext= FilenameUtils.getExtension(file.getOriginalFilename());
        return "p" + id +"."+ext;
    }

    public FileSystemStorageServiceImpl(StorageProperties properties){
        this.rootLocation= Paths.get(properties.getLocation());
    }
    @Override
    public void store(MultipartFile file, String storedFilename){
        try {
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file");
            }
            Path destinationFile=this.rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
            if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())){
                throw new StorageException("Cannot store file outside current directory");
            }
            try(InputStream inputStream=file.getInputStream()) {
                Files.copy(inputStream,destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (Exception e){
            throw new StorageException("Failed to store file ",e);
        }
    }
    @Override
    public org.springframework.core.io.Resource loadAsResource(String filename){
        try {
            Path file=load(filename);
            Resource resource=new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
            throw new StorageFileNotFoundException("Could not read file "+filename);
        }catch (Exception e){
            throw new StorageFileNotFoundException("Could not read file "+filename);
        }
    }
    @Override
    public Path load(String filename){
        return rootLocation.resolve(filename);
    }
    @Override
    public void delete(String storedFilename) throws IOException {
        Path destinationFile=rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();

        Files.delete(destinationFile);
    }
    @Override
    public void init(){
        try {
            Files.createDirectories(rootLocation);
            System.out.println(rootLocation.toString());
        }catch (Exception e){
            throw new StorageException("Could not initialize storage ",e);
        }
    }
}
