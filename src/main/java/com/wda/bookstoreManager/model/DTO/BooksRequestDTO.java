package com.wda.bookstoreManager.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksRequestDTO {

    @NotNull
    public String name;

    @NotNull
    public String author;

    @NotNull
    public Integer publishingId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate launch;

    @NotNull
    public Integer quantity;
}
