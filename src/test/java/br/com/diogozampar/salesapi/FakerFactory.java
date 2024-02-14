package br.com.diogozampar.salesapi;

import com.github.javafaker.Faker;

import br.com.diogozampar.salesapi.dto.SellerDTO;

public class FakerFactory{
    private static Faker faker = new Faker();

    public static SellerDTO sellerDTO(){
        return new SellerDTO(faker.name().fullName(), faker.internet().emailAddress());
    }

    
}
