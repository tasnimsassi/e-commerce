package com.projet.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.ecommerce.entities.Produit;

@Repository
public interface produitRepository extends JpaRepository<Produit, Long> {
}
