package br.com.diogozampar.salesapi.unit.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import br.com.diogozampar.salesapi.exception.MissingEntityException;
import br.com.diogozampar.salesapi.repository.SaleRepository;
import br.com.diogozampar.salesapi.repository.SellerRepository;
import br.com.diogozampar.salesapi.service.SaleService;


@ActiveProfiles("test")
public class SaleServiceTest {
    
    @Mock
    private SaleRepository saleRepository;

    @Mock
    private SellerRepository sellerRepository;

    @Autowired
    @InjectMocks
    private SaleService saleService;

    @BeforeEach
    void testSetup(){
        MockitoAnnotations.openMocks(this);
    }
    




    @Test
    void testCreateSale_SellerNotFound_ThrowsMissingEntityException() {
        when(sellerRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(MissingEntityException.class, 
            () -> saleService.getSaleById(UUID.randomUUID()));
    }


    @Test
    void testGetSaleById_SaleNotFound_ThrowsMissingEntityException() {
        when(saleRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(MissingEntityException.class, 
            () -> saleService.getSaleById(UUID.randomUUID()));
    }
}
