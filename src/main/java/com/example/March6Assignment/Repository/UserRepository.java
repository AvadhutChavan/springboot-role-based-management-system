package com.example.March6Assignment.Repository;



import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.March6Assignment.Pojo.User;


public interface UserRepository extends JpaRepository<User,Integer>{

User findByUsername(String username);

List<User> findByCity(String city);

@Query("SELECT u FROM userTB u WHERE "
	     + "(:city IS NULL OR LOWER(u.city) = LOWER(:city)) AND "
	     + "(:blood IS NULL OR LOWER(u.bloodgroup) = LOWER(:blood)) AND "
	     + "(:gender IS NULL OR LOWER(u.gender) = LOWER(:gender))")
	List<User> searchUser(String city, String blood, String gender);

}
