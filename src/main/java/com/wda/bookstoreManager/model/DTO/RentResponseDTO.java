package com.wda.bookstoreManager.model.DTO;

import com.wda.bookstoreManager.model.UsersEntity;
import com.wda.bookstoreManager.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentResponseDTO {

    public Integer id;

    public UserResponseDTO users;

    public BooksResponseDTO booksDTO;

    public Status status;

    public LocalDate rental_date;

    public LocalDate forecast_return;

    public LocalDate return_date;

}
