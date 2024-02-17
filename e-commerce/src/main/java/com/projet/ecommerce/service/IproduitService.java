package com.projet.ecommerce.service;

import java.util.List;

import com.projet.ecommerce.entities.Produit;

public interface IproduitService {

    Produit saveProduit(Produit produit);

    List<Produit> findAllProduits();

    Produit findProduitById(Long id);

    Produit updateProduit(Long id, Produit updatedProduit);

    void deleteProduit(Long id);
}
