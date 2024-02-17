package com.projet.ecommerce.service;

import com.projet.ecommerce.entities.Commande;
import com.projet.ecommerce.repository.commRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplcommandeService implements IcommandeService {

    @Autowired
    private commRepository commandeRepository;

    @Override
    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> findAllCommandes() {
        return commandeRepository.findAll();
    }

}

