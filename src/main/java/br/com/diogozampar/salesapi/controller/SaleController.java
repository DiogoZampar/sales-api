package br.com.diogozampar.salesapi.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogozampar.salesapi.dto.SaleDTO;
import br.com.diogozampar.salesapi.model.Sale;
import br.com.diogozampar.salesapi.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {


    private SaleService saleService;
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllSales(){
        return ResponseEntity
            .ok()
            .body(saleService.getAllSales());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getSaleById(@PathVariable(name =  "id") UUID id){
        Optional<Sale> opt = saleService.getSaleById(id);
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
    public ResponseEntity<Object> createSale(@RequestBody SaleDTO saleDTO){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saleService.createSale(saleDTO));
    }
}
