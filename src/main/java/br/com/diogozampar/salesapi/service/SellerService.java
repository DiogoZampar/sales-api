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
    
    public Optional<Seller> getSellerById(UUID id){
        return sellerRepository.findById(id);
    }

    public Seller createSeller(SellerDTO sellerDTO){
        return sellerRepository.save(new Seller(sellerDTO.name(), sellerDTO.email()));
    }

    public List<SellerRankingDTO> getSellerRankingListBetween(LocalDate starDate, LocalDate endDate){
        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerRankingDTO> sellerRankingDTOList = new ArrayList<>();

        for (Seller seller : sellerList) {
            Long totalSales = saleRepository.countBySellerIdAndSaleDateBetween(seller.getSellerId(), starDate, endDate);
            Double averageSales = totalSales.doubleValue()/(ChronoUnit.DAYS.between(starDate, endDate));
            sellerRankingDTOList.add(
                new SellerRankingDTO(seller.getSellerId(), totalSales, averageSales)
            );
        }

        sellerRankingDTOList.sort((o1, o2) -> o2.totalSales().compareTo(o1.totalSales()));
        return sellerRankingDTOList;
    }


}
