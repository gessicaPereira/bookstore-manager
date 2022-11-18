package com.wda.bookstoreManager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wda.bookstoreManager.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rent")
public class RentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private BooksEntity books;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private UsersEntity users;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rental_date;

    @Column(name = "forecast_return", nullable = false)
    private LocalDate forecast_return;

    @Column(name = "return_date")
    private LocalDate return_date;

    @Enumerated(EnumType.STRING)
    private Status status;

}
