package com.silkroad.BitsBids.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silkroad.BitsBids.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
