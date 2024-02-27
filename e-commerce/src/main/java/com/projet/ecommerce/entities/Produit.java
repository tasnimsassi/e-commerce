package com.projet.ecommerce.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "produit")
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
    private Long id;

 
    private String nom;
    private String description;
    private double prix;
    private String imagePath;
    @JsonBackReference
    @ManyToMany(mappedBy = "produits")
    private List<Commande> commandes;
    // Constructors, getters, and setters

    public Produit() {
        // Default constructor
    }

    public Produit(String nom, String description, double prix) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    // Getters and setters

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
