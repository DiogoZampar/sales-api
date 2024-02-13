package br.com.diogozampar.salesapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.diogozampar.salesapi.dto.SaleDTO;
import br.com.diogozampar.salesapi.exception.DateInputException;
import br.com.diogozampar.salesapi.exception.MissingEntityException;
import br.com.diogozampar.salesapi.model.Sale;
import br.com.diogozampar.salesapi.model.Seller;
import br.com.diogozampar.salesapi.repository.SaleRepository;
import br.com.diogozampar.salesapi.repository.SellerRepository;



@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SellerRepository sellerRepository;
    public SaleService(SaleRepository saleRepository, SellerRepository sellerRepository){
        this.saleRepository = saleRepository;
        this.sellerRepository = sellerRepository;
    }


    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    
    public Sale getSaleById(UUID id){
        Optional<Sale> optSale = saleRepository.findById(id);
        if(optSale.isPresent()){
            return optSale.get();
        }else{
            throw new MissingEntityException("Sale with ID: " + id + "could not be found.");
        }
    }
    

    public Sale createSale(SaleDTO saleDTO){
        if(LocalDate.parse(saleDTO.saleDate()).isAfter(LocalDate.now())) throw new DateInputException("Sale date cannot be in the future.");
        Optional<Seller> optSeller = sellerRepository.findById(UUID.fromString(saleDTO.sellerId()));
        if(optSeller.isPresent()){
            return saleRepository.save(new Sale(LocalDate.parse(saleDTO.saleDate()), saleDTO.totalCost(), optSeller.get()));
        }else{
            throw new MissingEntityException("Seller with ID:" + saleDTO.sellerId() + " could not be found. Sale could not be created.");
        }
    }
}
