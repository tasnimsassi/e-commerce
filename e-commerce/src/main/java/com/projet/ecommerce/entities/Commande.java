package com.projet.ecommerce.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

   
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "commande_produit",
        joinColumns = @JoinColumn(name = "commande_id"),
        inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Produit> produits;

    private String reference;
    private double totalAmount;
    @Transient
    private List<String> productNames;
    public Commande() {
        // Default constructor
    }

    public Commande(String reference, double totalAmount, User user, List<Produit> produits) {
        this.reference = reference;
        this.totalAmount = totalAmount;
        this.user = user;
        this.produits = produits;
    }

    // Getters and Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

   // public double getTotalAmount() {
    //    return totalAmount;
    //}

    public double getTotalAmount() {
     if (produits != null && !produits.isEmpty()) {
            return produits.stream()
                   .mapToDouble(Produit::getPrix)
                   .sum();
        }
       return 0.0;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public void addProduit(Produit produit) {
        produits.add(produit);
        produit.getCommandes().add(this);
    }
    public void removeProduit(Produit produit) {
        produits.remove(produit);
        produit.getCommandes().remove(this);
    }


}
