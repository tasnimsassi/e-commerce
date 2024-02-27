package com.projet.ecommerce.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.projet.ecommerce.entities.User;
@Repository
public interface userRepository extends JpaRepository<User,Long> {

    List<User> findByCommandesIsNotEmpty();
	

}
