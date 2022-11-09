package com.wda.bookstoreManager.repository;

import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

   // UsersEntity findById(Integer id);

    UsersEntity findUserById(Integer id);

    void deleteById(Integer id);

}
