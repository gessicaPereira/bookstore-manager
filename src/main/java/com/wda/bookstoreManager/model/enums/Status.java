package com.wda.bookstoreManager.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Status {

    PROGRESSO("Progress"),
    DEVOLVIDO("Deadline"),
    ATRASADO("Late");

    private String status;

}
