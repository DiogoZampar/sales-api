package br.com.diogozampar.salesapi.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.diogozampar.salesapi.dto.SaleDTO;
import br.com.diogozampar.salesapi.model.Sale;
import br.com.diogozampar.salesapi.model.Seller;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SaleTests {
    

    @Autowired
	WebTestClient webTestClient;

    protected String name = "adequate length name";
    protected String email = "email@email.com";
    protected Seller seller = new Seller(name, email);
    


	@Test
    @DirtiesContext
	public void testCreateSale_Success() {
        UUID uuid = webTestClient.post().uri("/seller").bodyValue(seller).exchange().expectBody(Seller.class).returnResult().getResponseBody()
            .getSellerId();

        LocalDate saleDate = LocalDate.now();

        Sale createdsale = webTestClient
        .post().uri("/sale")
        .bodyValue(new SaleDTO(saleDate.toString(), 10.0, uuid.toString()))
        .exchange()
        .expectStatus().isCreated()
        .expectBody(Sale.class)
        .returnResult()
        .getResponseBody();

        assertNotNull(createdsale);
        assertNotNull(createdsale.getSaleID());
        assertEquals(saleDate, createdsale.getSaleDate());
        assertEquals(10, createdsale.getTotalCost());

	}

    @Test
    @DirtiesContext
    public void testCreateSale_FutureDate_Fail(){
        UUID uuid = webTestClient.post().uri("/seller").bodyValue(seller).exchange().expectBody(Seller.class).returnResult().getResponseBody()
            .getSellerId();
        LocalDate futureSaleDate = LocalDate.now().plusDays(3);

        webTestClient
        .post().uri("/sale")
        .bodyValue(new SaleDTO(futureSaleDate.toString(), 10.0, uuid.toString()))
        .exchange()
        .expectStatus().isBadRequest();
    }


    @Test
    @DirtiesContext
    public void testeCreateSale_InexistentSellerId_Fail(){
        UUID uuid = UUID.randomUUID();

        LocalDate futureSaleDate = LocalDate.now().plusDays(3);

        webTestClient
        .post().uri("/sale")
        .bodyValue(new SaleDTO(futureSaleDate.toString(), 10.0, uuid.toString()))
        .exchange()
        .expectStatus().isBadRequest();
    }
}
