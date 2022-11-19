package com.wda.bookstoreManager.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Status {

    PROGRESS("Progress"),
    DEADLINE("Deadline"),
    LATE("Late");

    private String status;

}
