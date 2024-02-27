package com.projet.ecommerce.service;





import java.util.List;
import java.util.Optional;

import com.projet.ecommerce.entities.User;

public interface IuserService {

	User saveUser(User user);

	List<User> findAllUsers();
    
    Optional<User> findUserById(Long id);

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
    List<User> getUsersWithCommands();
    
    User getUserWithCommandsAndProducts(Long userId);
}
