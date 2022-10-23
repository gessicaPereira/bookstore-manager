package com.wda.bookstoreManager.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Status {

    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable");

    private String status;

}
