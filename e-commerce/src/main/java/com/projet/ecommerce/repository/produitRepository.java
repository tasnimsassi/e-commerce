package com.projet.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.ecommerce.entities.Produit;

@Repository
public interface produitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByNomContaining(String partialName);
    List<Produit> findByPrixBetween(double prixMin, double prixMax);
}
