package com.projet.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projet.ecommerce.entities.Produit;
import com.projet.ecommerce.service.FileStorageService;
import com.projet.ecommerce.service.IproduitService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/api/produits")
public class produitController {

    @Autowired
    private IproduitService produitService;
    @Autowired
   
    private FileStorageService fileStorageService; 

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
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produit> addProduit(@Valid Produit produit, @RequestParam("file") MultipartFile file) {
    try {
        String fileName = fileStorageService.storeFile(file);
        produit.setImagePath(fileName);

        Produit savedProduit = produitService.saveProduit(produit);

        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

@GetMapping("/byName/{partialName}")
    public ResponseEntity<List<Produit>> getProduitsByPartialName(
            @PathVariable String partialName) {
        List<Produit> produits = produitService.findProduitsByPartialName(partialName);
        return ResponseEntity.ok(produits);
    }

@GetMapping("/byPrice/{prixMin}/{prixMax}")
public ResponseEntity<List<Produit>> getProduitsByPriceRange(
        @PathVariable double prixMin, @PathVariable double prixMax) {
    List<Produit> produits = produitService.findProduitsByPrixBetween(prixMin, prixMax);
    return ResponseEntity.ok(produits);
}
}
