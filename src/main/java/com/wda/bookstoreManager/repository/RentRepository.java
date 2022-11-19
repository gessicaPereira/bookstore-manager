package com.wda.bookstoreManager.repository;

import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import com.wda.bookstoreManager.model.UsersEntity;
import com.wda.bookstoreManager.model.enums.Status;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RentRepository extends JpaRepository<RentEntity, Integer> {

    //Optional <RentResponseDTO> rentById (Integer id);

    //RentEntity findId(Integer rentId);

    RentEntity findRentById(Integer id);

    List<RentEntity> findByBooks(BooksEntity books);

    Optional<RentEntity> findByBooksAndUsersAndStatus(BooksEntity books, UsersEntity users, Status status);
}
