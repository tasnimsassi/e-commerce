package com.projet.ecommerce.service;

import com.projet.ecommerce.entities.Commande;

import java.util.List;

public interface IcommandeService {
    Commande saveCommande(Commande commande);

    List<Commande> findAllCommandes();

    
}
