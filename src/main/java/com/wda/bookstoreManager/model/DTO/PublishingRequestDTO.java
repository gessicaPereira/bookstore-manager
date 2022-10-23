package com.wda.bookstoreManager.model.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishingRequestDTO {

    @NotNull
    public String name;
    @NotNull
    public String city;

}
