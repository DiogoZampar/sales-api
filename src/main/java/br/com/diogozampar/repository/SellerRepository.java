package br.com.diogozampar.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diogozampar.model.Seller;


public interface SellerRepository extends JpaRepository<Seller, UUID> {
    
}
