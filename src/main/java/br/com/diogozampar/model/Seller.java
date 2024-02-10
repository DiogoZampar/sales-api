package br.com.diogozampar.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Seller {
    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sellerId;

    private String name;

    private String email;


    public Seller(String name, String email){
        this.name = name;
        this.email = email;
    }

}
