package br.com.diogozampar.salesapi.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record SaleDTO(
    @NotNull(message = "Date cannot be null") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format should be: YYYY-MM-DD") String saleDate, 
    @NotNull(message = "Cost cannot be null") @PositiveOrZero(message = "Cost cannot be negative") Double totalCost, 
    @NotNull(message = "Seller ID cannot be null") @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "Seller ID cannot be blank or null") String sellerId    
) {}