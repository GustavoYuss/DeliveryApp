package fei.uv.mx.deliveryapp;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;

@SpringBootApplication
public class DeliveryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryAppApplication.class, args);
    }

}
