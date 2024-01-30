package edu.poly.shirtpolofinal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Configuration
@ConfigurationProperties("storage")
@Data
public class StorageProperties {
    private String location;
}
