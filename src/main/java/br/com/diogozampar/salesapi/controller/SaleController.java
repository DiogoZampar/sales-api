package br.com.diogozampar.salesapi.controller;

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
import br.com.diogozampar.salesapi.service.SaleService;
import jakarta.validation.Valid;


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
        return ResponseEntity
            .ok()
            .body(saleService.getSaleById(id));
    }


    @PostMapping
    public ResponseEntity<Object> createSale(@RequestBody @Valid SaleDTO saleDTO){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saleService.createSale(saleDTO));
    }


}
