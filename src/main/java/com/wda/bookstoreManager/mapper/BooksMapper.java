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

    public BooksEntity toResponseModel(BooksResponseDTO booksResponseDTO){
        return mapper.map(booksResponseDTO, BooksEntity.class);
    }

    public BooksResponseDTO toBookDTO(BooksEntity booksEntity){
        return mapper.map(booksEntity, BooksResponseDTO.class);
    }
}
