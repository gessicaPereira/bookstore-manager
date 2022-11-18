package com.wda.bookstoreManager.model.DTO;


import com.sun.istack.NotNull;
import com.wda.bookstoreManager.model.RentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    public Integer id;

    public String name;

    public String city;

    public String address;

    public String email;

    public List<RentEntity> rentEntityList;

}
