package edu.poly.shirtpolofinal;

import edu.poly.shirtpolofinal.config.StorageProperties;
import edu.poly.shirtpolofinal.service.Impl.MailSenderServiceImpl;
import edu.poly.shirtpolofinal.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties(StorageProperties.class)
public class ShirtPoloFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirtPoloFinalApplication.class, args);
    }
    @Bean
    CommandLineRunner init(StorageService storageService){
        return (args -> {
            storageService.init();
        });
    }

}
