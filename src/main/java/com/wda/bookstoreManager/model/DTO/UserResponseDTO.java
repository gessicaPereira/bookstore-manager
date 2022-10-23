package com.wda.bookstoreManager.model.DTO;


import com.sun.istack.NotNull;
import com.wda.bookstoreManager.model.RentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    public Integer id;

    @NotNull
    public String name;

    @NotNull
    public String city;

    @NotNull
    public String address;

    @NotNull
    public String email;

    public List<RentEntity> rentEntityList;

}
