package br.com.diogozampar.salesapi.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.diogozampar.salesapi.dto.SellerDTO;
import br.com.diogozampar.salesapi.dto.SellerRankingDTO;
import br.com.diogozampar.salesapi.exception.DateInputException;
import br.com.diogozampar.salesapi.exception.MissingEntityException;
import br.com.diogozampar.salesapi.exception.UniqueEntityFieldViolationException;
import br.com.diogozampar.salesapi.model.Seller;
import br.com.diogozampar.salesapi.repository.SaleRepository;
import br.com.diogozampar.salesapi.repository.SellerRepository;


@Service
public class SellerService {
    
    private final SellerRepository sellerRepository;
    private final SaleRepository saleRepository;
    public SellerService(SellerRepository sellerRepository, SaleRepository saleRepository){
        this.sellerRepository = sellerRepository;
        this.saleRepository = saleRepository;
    }


    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }
    
    public Seller getSellerById(UUID id){
        Optional<Seller> optSeller = sellerRepository.findById(id);
        if(optSeller.isPresent()){
            return optSeller.get();
        }else{
            throw new MissingEntityException("Seller with ID:" + id + " could not be found.");
        }
    }

    public Seller createSeller(SellerDTO sellerDTO){
        if(sellerRepository.existsSellerByEmail(sellerDTO.email())) throw new UniqueEntityFieldViolationException("Email: " + sellerDTO.email() + " is already registered."); 
        return sellerRepository.save(new Seller(sellerDTO.name(), sellerDTO.email()));
    }

    public List<SellerRankingDTO> getSellerRankingListBetween(LocalDate starDate, LocalDate endDate){
        if(starDate.isAfter(endDate)) throw new DateInputException("startDate must come before endDate.");
        if(starDate.isAfter(LocalDate.now())) throw new DateInputException("startDate must be in the past.");
        
        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerRankingDTO> sellerRankingDTOList = new ArrayList<>();


        for (Seller seller : sellerList) {
            Long totalSales = saleRepository.countBySellerSellerIdAndSaleDateBetween(seller.getSellerId(), starDate, endDate);
            Double averageSales = totalSales.doubleValue()/(ChronoUnit.DAYS.between(starDate, endDate));
            sellerRankingDTOList.add(
                new SellerRankingDTO(seller.getName(), totalSales, averageSales)
            );
        }

        sellerRankingDTOList.sort((o1, o2) -> o2.totalSales().compareTo(o1.totalSales()));
        return sellerRankingDTOList;
    }


}
