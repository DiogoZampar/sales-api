package br.com.diogozampar.salesapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.diogozampar.salesapi.dto.SellerDTO;
import br.com.diogozampar.salesapi.model.Seller;
import br.com.diogozampar.salesapi.repository.SellerRepository;

@Service
public class SellerService {
    
    private final SellerRepository sellerRepository;
    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
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


}
