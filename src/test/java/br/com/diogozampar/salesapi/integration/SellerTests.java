package br.com.diogozampar.salesapi.integration;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.diogozampar.salesapi.model.Seller;


@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SellerTests {
    
    @Autowired
    WebTestClient webTestClient;

    @Test
    @DirtiesContext
    public void testCreateSeller_Success(){
        String name = "adequate length name";
        String email = "email@email.com";

        webTestClient
        .post()
        .uri("/seller")
        .bodyValue(new Seller(name, email))
        .exchange()
        .expectStatus().isCreated()
        .expectBody()
        .jsonPath("name").isEqualTo(name)
        .jsonPath("email").isEqualTo(email)
        .jsonPath("sellerId").isNotEmpty();
        
    }


    @Test
    public void testCreateSeller_ShortName_Fail(){
        String name = "smol";
        String email = "email@email.com";

        webTestClient
        .post()
        .uri("/seller")
        .bodyValue(new Seller(name, email))
        .exchange()
        .expectStatus().isBadRequest();
    }

    @Test
    public void testCreateSeller_InvalidEmail_Fail(){
        String name = "adequate length name";
        String email = "emailemail.com";

        webTestClient
        .post()
        .uri("/seller")
        .bodyValue(new Seller(name, email))
        .exchange()
        .expectStatus().isBadRequest();
    }

    @Test
    @DirtiesContext
    public void testCreateAndGetSameSeller_Success(){
        String name = "adequate length name";
        String email = "email@email.com";

        UUID uuid = 
        webTestClient
        .post()
        .uri("/seller")
        .bodyValue(new Seller(name, email))
        .exchange()
        .expectBody(Seller.class)
        .returnResult()
        .getResponseBody()
        .getSellerId();


        webTestClient
        .get()
        .uri("/seller/" + uuid)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Seller.class)
        .returnResult()
        .getResponseBody()
        .getSellerId()
        .equals(uuid);

    }



}
