package br.com.diogozampar.salesapi.dto;

import java.util.UUID;

public record SellerRankingDTO(
    UUID sellerID,
    Long totalSales,
    Double averageDailySales
) {
    
}
