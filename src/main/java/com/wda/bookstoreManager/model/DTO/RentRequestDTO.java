package com.wda.bookstoreManager.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import com.wda.bookstoreManager.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentRequestDTO {

    @NotNull
    public Integer bookId;

    @NotNull
    public Integer user;

    //public Integer quantity;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate rental_date;

   /* @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate return_date;*/

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate forecast_return;


}
