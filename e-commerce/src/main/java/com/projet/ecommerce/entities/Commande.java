package com.projet.ecommerce.entities;

import javax.persistence.*;


@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    //private Long userId;

    // Constructors, getters, setters...

    public Commande() {
        // Default constructor
    }

    public Commande(String reference, double totalAmount, User user) {
        this.reference = reference;
        this.totalAmount = totalAmount;
        this.user = user;
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

    public double getTotalAmount() {
        return totalAmount;
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
}
