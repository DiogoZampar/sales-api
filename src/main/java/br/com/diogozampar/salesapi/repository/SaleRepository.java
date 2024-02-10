package br.com.diogozampar.salesapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diogozampar.salesapi.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    
}