package br.com.diogozampar.salesapi.unit.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import br.com.diogozampar.salesapi.dto.SellerDTO;
import br.com.diogozampar.salesapi.exception.DateInputException;
import br.com.diogozampar.salesapi.exception.MissingEntityException;
import br.com.diogozampar.salesapi.exception.UniqueEntityFieldViolationException;
import br.com.diogozampar.salesapi.repository.SaleRepository;
import br.com.diogozampar.salesapi.repository.SellerRepository;
import br.com.diogozampar.salesapi.service.SellerService;



@ActiveProfiles("Test")
public class SellerServiceTest {
    
    @Mock
    private SellerRepository sellerRepository;
    @Mock
    private SaleRepository saleRepository;
    
    @Autowired
    @InjectMocks
    private SellerService sellerService;
    
    @BeforeEach
    void testSetup(){
        MockitoAnnotations.openMocks(this);
    }
    

    

    @Test
    void testCreateSeller_EmailAlreadyUsed_ThrowsUniqueEntityFieldViolationException() {
        when(sellerRepository.existsSellerByEmail(anyString())).thenReturn(true);
        assertThrows(UniqueEntityFieldViolationException.class,
        () -> sellerService.createSeller(new SellerDTO("seller name", "anyemail@email.com")));
    }


    @Test
    void testGetSellerById_SellerNotFound_ThrowsMissingEntityException() {
        when(sellerRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(MissingEntityException.class,
        () -> sellerService.getSellerById(UUID.randomUUID()) );
    }


    @Test
    void testGetSellerRankingListBetween_StartAndEndDatesInverted_ThrowsDateInputException() {
        assertThrows(DateInputException.class, 
        () -> sellerService.getSellerRankingListBetween(LocalDate.now(), LocalDate.now().minusDays(5)));
    }


    
    @Test
    void testGetSellerRankingListBetween_StartDateInTheFuture_ThrowsDateInputException() {
        assertThrows(DateInputException.class, 
        () -> sellerService.getSellerRankingListBetween(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10)));
    }
}
