package com.wda.bookstoreManager.model.DTO;

import com.wda.bookstoreManager.model.RentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksResponseDTO {

    public Integer id;
    public String name;
    public String author;
    public Integer quantity;
    public PublishingRequestDTO publishing;
    public LocalDate launch;
    public Integer quantityRented;
    public List<RentEntity> rentEntityList;
}
