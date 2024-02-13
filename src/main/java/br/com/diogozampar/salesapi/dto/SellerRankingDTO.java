package br.com.diogozampar.salesapi.dto;


public record SellerRankingDTO(
    String sellerName,
    Long totalSales,
    Double averageDailySales
) {
    
}
