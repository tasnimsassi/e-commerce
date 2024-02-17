package com.projet.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.ecommerce.entities.Produit;
import com.projet.ecommerce.repository.produitRepository;

@Service
public class ImplproduitService implements IproduitService {

    @Autowired
    private produitRepository produitRepository;

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> findAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit findProduitById(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public Produit updateProduit(Long id, Produit updatedProduit) {
        Produit existingProduit = findProduitById(id);

        if (existingProduit != null) {
            // Mettez à jour les propriétés du produit existant avec les nouvelles valeurs
            existingProduit.setNom(updatedProduit.getNom());
            existingProduit.setDescription(updatedProduit.getDescription());
            existingProduit.setPrix(updatedProduit.getPrix());

            return produitRepository.save(existingProduit);
        }

        return null;
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
