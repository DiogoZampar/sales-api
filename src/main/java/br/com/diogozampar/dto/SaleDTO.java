package br.com.diogozampar.dto;

import java.time.LocalDate;
import java.util.UUID;

public record SaleDTO(LocalDate saleDate, double totalCost, UUID sellerId, String sellerName) {
    
}
