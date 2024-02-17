package com.projet.ecommerce.repository;

import com.projet.ecommerce.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface commRepository extends JpaRepository<Commande, Long> {
    
}
