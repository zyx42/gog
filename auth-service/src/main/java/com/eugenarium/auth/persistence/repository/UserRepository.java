package com.eugenarium.auth.persistence.repository;

import com.eugenarium.auth.persistence.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
