package com.silkroad.BitsBids.repositories;

import com.silkroad.BitsBids.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
