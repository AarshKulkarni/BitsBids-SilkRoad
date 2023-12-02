package com.silkroad.BitsBids.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silkroad.BitsBids.models.User;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
}
