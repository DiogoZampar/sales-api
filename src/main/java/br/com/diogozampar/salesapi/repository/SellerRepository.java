package br.com.diogozampar.salesapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diogozampar.salesapi.model.Seller;


public interface SellerRepository extends JpaRepository<Seller, UUID> {
    
}
