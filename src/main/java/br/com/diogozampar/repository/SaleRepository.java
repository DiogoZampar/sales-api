package br.com.diogozampar.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diogozampar.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    
}
