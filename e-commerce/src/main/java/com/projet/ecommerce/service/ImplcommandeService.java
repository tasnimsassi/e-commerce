package com.projet.ecommerce.service;

import com.projet.ecommerce.entities.Commande;
import com.projet.ecommerce.entities.Produit;
import com.projet.ecommerce.repository.commRepository;
import com.projet.ecommerce.repository.produitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class ImplcommandeService implements IcommandeService {

    @Autowired
    private commRepository commandeRepository;

    @Autowired
    private produitRepository produitRepository;

    @Override
    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> findAllCommandes() {
        return commandeRepository.findAll(); 
    }

    @Override
    public void addProduitToCommande(Long commandeId, Long produitId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new EntityNotFoundException("Commande not found with id: " + commandeId));
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new EntityNotFoundException("Produit not found with id: " + produitId));
    
      
        commande.addProduit(produit);
    
        // Mettez à jour la commande
        commandeRepository.save(commande);
    }

@Override
public void removeProduitFromCommande(Long commandeId, Long produitId) {
    Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new EntityNotFoundException("Commande not found with id: " + commandeId));
    Produit produit = produitRepository.findById(produitId)
            .orElseThrow(() -> new EntityNotFoundException("Produit not found with id: " + produitId));

    commande.removeProduit(produit);
    commandeRepository.save(commande);
}



}
