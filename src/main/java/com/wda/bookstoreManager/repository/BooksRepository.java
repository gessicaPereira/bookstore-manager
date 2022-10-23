package com.wda.bookstoreManager.repository;

import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Long> {

    Optional<BooksRequestDTO> findByName(String name);

    Optional<BooksEntity> findById(Integer id);

    void deleteById(Integer bookId);



}
