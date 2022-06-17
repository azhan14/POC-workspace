package com.neosoft.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.user.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

}
