package com.neosoft.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
	List<User> findByName(String name);
	List<User> findBySurname(String surname);
	List<User> findByPincode(String pincode);
	
	@Query("SELECT u FROM User u WHERE u.deleted = 0")
	List<User> getAllUsers();
	
	@Query("SELECT u FROM User u WHERE u.deleted = 1")
	List<User> getAllSoftDeletedUsers();
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.deleted = 1 WHERE u.id = ?1")
	void softDelete(Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.locked = 1 WHERE u.id = ?1")
	void lockUser(Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.locked = 0 WHERE u.id = ?1")
	void unlockUser(Long id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM User u WHERE u.id = ?1")
	void deleteUserById(Long id);
}
