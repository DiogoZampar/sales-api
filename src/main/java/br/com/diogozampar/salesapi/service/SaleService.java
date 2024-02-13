package br.com.diogozampar.salesapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.diogozampar.salesapi.dto.SaleDTO;
import br.com.diogozampar.salesapi.model.Sale;
import br.com.diogozampar.salesapi.repository.SaleRepository;



@Service
public class SaleService {

    private final SaleRepository saleRepository;
    public SaleService(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
    }


    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(UUID id){
        return saleRepository.findById(id);
    }
    
    public Sale createSale(SaleDTO saleDTO){
        return saleRepository.save(new Sale(LocalDate.parse(saleDTO.saleDate()), saleDTO.totalCost(), UUID.fromString(saleDTO.sellerId()), saleDTO.sellerName()));
    }

}
