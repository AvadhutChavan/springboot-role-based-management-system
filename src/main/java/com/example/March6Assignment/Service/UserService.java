package com.example.March6Assignment.Service;
import com.example.March6Assignment.Repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.March6Assignment.Pojo.User;






@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public void saveUser(User user) {
		repo.save(user);
	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public void deleteUser(int id) {
		repo.deleteById(id);
	}

	public User getUserById(int id) {
		return repo.findById(id).orElse(null);
	}

	public Optional<User> findByUsername(String username) {
		return Optional.ofNullable(repo.findByUsername(username));
	}

	public List<User> searchByCity(String city) {
		return repo.findAll();
	}

	public List<User> searchUser(String city, String blood, String gender) {

//    city = (city == null || city.isBlank()) ? null : city;
//    blood = (blood == null || blood.isBlank()) ? null : blood;
//    gender = (gender == null || gender.isBlank()) ? null : gender;

		return repo.searchUser(city, blood, gender);
	}

}