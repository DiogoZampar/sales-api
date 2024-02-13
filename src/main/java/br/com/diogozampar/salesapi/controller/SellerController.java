package br.com.diogozampar.salesapi.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.diogozampar.salesapi.dto.SellerDTO;
import br.com.diogozampar.salesapi.model.Seller;
import br.com.diogozampar.salesapi.service.SellerService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/seller")
public class SellerController {
    
    
    private SellerService sellerService;
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }



    @GetMapping
    public ResponseEntity<Object> getAllSellers() {
        return ResponseEntity
            .ok()
            .body(sellerService.getAllSellers());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSellerById(@PathVariable("id") @Valid UUID id){
        Optional<Seller> opt = sellerService.getSellerById(id);
        if(opt.isPresent()){
            return ResponseEntity
                .ok()
                .body(opt.get());
        }else{
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createSeller(@RequestBody @Valid SellerDTO sellerDTO){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(sellerService.createSeller(sellerDTO));
    }




    @GetMapping("/ranking")
    public ResponseEntity<Object> getSellerRankingListBetween(@RequestParam("start") LocalDate starDate, @RequestParam("end") LocalDate endDate) {
        return ResponseEntity.ok(sellerService.getSellerRankingListBetween(starDate, endDate));
    }

}
