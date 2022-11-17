package com.wda.bookstoreManager.repository;

import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import com.wda.bookstoreManager.model.DTO.BooksResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Integer> {

    Optional<BooksEntity> findByName(String name);

   // BooksEntity findBookById(Integer id);

   // Optional<BooksEntity> findById(Integer bookId);

   // Optional <BooksResponseDTO> getId(Integer bookId);

   // void deleteById(Integer bookId);



}
