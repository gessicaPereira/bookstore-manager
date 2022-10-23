package com.wda.bookstoreManager.repository;

import com.wda.bookstoreManager.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    UsersEntity findById(Integer id);

    void deleteById(Integer id);

}
