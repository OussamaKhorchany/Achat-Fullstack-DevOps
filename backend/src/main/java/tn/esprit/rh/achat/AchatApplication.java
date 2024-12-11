package tn.esprit.rh.achat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories("tn.esprit.rh.achat.repositories")
@EntityScan(basePackages = "tn.esprit.rh.achat.entities") 
public class AchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(AchatApplication.class, args);
    }

}
