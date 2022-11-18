package com.wda.bookstoreManager.mapper;

import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import com.wda.bookstoreManager.model.DTO.BooksResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BooksMapper {

    private final ModelMapper mapper;

    public BooksEntity toBookModel(BooksRequestDTO booksRequestDTO){
        return mapper.map(booksRequestDTO, BooksEntity.class);
    }

   /* public BooksRequestDTO toDTO(BooksEntity books){
        return mapper.map(books, BooksRequestDTO.class);
    }*/

    public BooksResponseDTO toBookDTO(BooksEntity booksEntity){
        return mapper.map(booksEntity, BooksResponseDTO.class);
    }
}
