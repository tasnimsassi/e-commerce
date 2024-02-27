
package com.projet.ecommerce.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.ecommerce.entities.Commande;
import com.projet.ecommerce.entities.User;
import com.projet.ecommerce.repository.userRepository;
import com.projet.ecommerce.service.IcommandeService;
@RestController
@RequestMapping("/api/commandes")
public class commandeController {

    @Autowired
    private IcommandeService commandeService;

    @Autowired
    private userRepository userRepository;  


    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.findAllCommandes();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Commande> addCommande(@Valid @RequestBody Commande commande) {
        User user = userRepository.findById(commande.getUser().getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + commande.getUser().getUserId()));
    
        commande.setUser(user);
        Commande savedCommande = commandeService.saveCommande(commande);
    
        return new ResponseEntity<>(savedCommande, HttpStatus.CREATED);
    }
    
    @PostMapping("/{commandeId}/produits/{produitId}")
    public ResponseEntity<Void> addProduitToCommande(@PathVariable Long commandeId, @PathVariable Long produitId) {
        commandeService.addProduitToCommande(commandeId, produitId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{commandeId}/produits/{produitId}")
    public ResponseEntity<Void> removeProduitFromCommande(@PathVariable Long commandeId, @PathVariable Long produitId) {
        commandeService.removeProduitFromCommande(commandeId, produitId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // Ajoutez d'autres méthodes au besoin

}
