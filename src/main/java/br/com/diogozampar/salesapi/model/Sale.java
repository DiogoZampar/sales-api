package br.com.diogozampar.salesapi.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID saleID;

    private LocalDate saleDate;

    private double totalCost;

    private UUID sellerId;

    private String sellerName;
    

    public Sale(LocalDate saleDate, double totalCost, UUID sellerId, String sellerName){
        this.saleDate = saleDate;
        this.totalCost = totalCost;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
    }


}
