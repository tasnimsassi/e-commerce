package com.projet.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.projet.ecommerce.entities.Produit;

public interface IproduitService {

    Produit saveProduit(Produit produit);

    List<Produit> findAllProduits();

    Produit findProduitById(Long id);

    Produit updateProduit(Long id, Produit updatedProduit);

    void deleteProduit(Long id);
    
    Produit saveProduit(Produit produit, MultipartFile file);

    List<Produit> findProduitsByPartialName(String partialName);

    List<Produit> findProduitsByPrixBetween(double prixMin, double prixMax);
}
