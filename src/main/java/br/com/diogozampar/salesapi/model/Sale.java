package br.com.diogozampar.salesapi.model;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PositiveOrZero;
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

    @PositiveOrZero
    private double totalCost;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_sellerId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("email")
    private Seller seller;

    public Sale(LocalDate saleDate, double totalCost, Seller seller){
        this.saleDate = saleDate;
        this.totalCost = totalCost;
        this.seller = seller;
    }


}
