package com.neosoft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{

}
