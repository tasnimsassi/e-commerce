
package com.projet.ecommerce.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.ecommerce.entities.Commande;
import com.projet.ecommerce.entities.User;
import com.projet.ecommerce.repository.userRepository;
import com.projet.ecommerce.service.IcommandeService;

@RestController
@RequestMapping("/api")
public class commandeController {

    @Autowired
    private IcommandeService commandeService;
    @Autowired
    private userRepository userRepository;  
    @GetMapping("/commandes")
    public List<Commande> getAllCommandes() {
        List<Commande> commandes = commandeService.findAllCommandes();
        return commandes;
    }

    @PostMapping("/addcommande")

    public ResponseEntity<Commande> addCommande(@Valid @RequestBody Commande commande) {
        // Check if the User with the specified userId exists
        if (userRepository.findById(commande.getUserId()).isPresent()) {
            // Save the Commande
            Commande savedCommande = commandeService.saveCommande(commande);
    
            // Return the saved Commande in the response
            return new ResponseEntity<>(savedCommande, HttpStatus.CREATED);
        } else {
            // If the User does not exist, throw an EntityNotFoundException
            throw new EntityNotFoundException("User not found with id: " + commande.getUserId());
        }
    }
    
    
    
    

}
