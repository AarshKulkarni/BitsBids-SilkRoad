package com.silkroad.BitsBids.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silkroad.BitsBids.models.Chat;

@Repository
public interface ChatRepository extends CrudRepository<Chat,Long> {
}
