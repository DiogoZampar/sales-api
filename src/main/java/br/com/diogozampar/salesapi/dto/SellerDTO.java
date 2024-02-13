package br.com.diogozampar.salesapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SellerDTO(
    
@NotNull(message = "Name cannot be null") @Size(min = 5, max = 100, message = "Name must have 5 to 100 characters") String name, 
@NotBlank(message = "Email cannot be blank or null") @Email(message = "Not a valid email") String email
    
) {
    
}
