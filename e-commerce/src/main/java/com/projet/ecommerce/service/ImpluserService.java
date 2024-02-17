package com.projet.ecommerce.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.projet.ecommerce.entities.User;
import com.projet.ecommerce.repository.userRepository;

@Service
public class ImpluserService implements IuserService {
	
	@Autowired
	userRepository urepos;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return urepos.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return urepos.findAll();
	}

	@Override
    public Optional<User> findUserById(Long id) {
        return urepos.findById(id);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOptional = urepos.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPwd(updatedUser.getPwd());
            existingUser.setFname(updatedUser.getFname());
            existingUser.setLname(updatedUser.getLname());
            // Vous pouvez ajouter d'autres propriétés à mettre à jour
            return urepos.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        urepos.deleteById(id);
    }

	

}
