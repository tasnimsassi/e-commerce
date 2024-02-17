package com.projet.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projet.ecommerce.entities.Produit;
import com.projet.ecommerce.service.IproduitService;

@RestController
@RequestMapping("/api/produits")
public class produitController {

    @Autowired
    private IproduitService produitService;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.findAllProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Produit produit = produitService.findProduitById(id);
        return ResponseEntity.ok(produit);
    }

    @PostMapping
    public Produit createProduit(@Valid @RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @Valid @RequestBody Produit updatedProduit) {
        Produit produit = produitService.findProduitById(id);

        if (produit == null) {
            return ResponseEntity.notFound().build();
        }

        produit.setNom(updatedProduit.getNom());
        produit.setDescription(updatedProduit.getDescription());
        produit.setPrix(updatedProduit.getPrix());
        // Ajoutez d'autres propriétés à mettre à jour

        Produit savedProduit = produitService.saveProduit(produit);
        return ResponseEntity.ok(savedProduit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        Produit produit = produitService.findProduitById(id);

        if (produit == null) {
            return ResponseEntity.notFound().build();
        }

        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}
