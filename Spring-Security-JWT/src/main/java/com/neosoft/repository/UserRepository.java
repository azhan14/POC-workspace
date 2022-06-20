package com.neosoft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}
