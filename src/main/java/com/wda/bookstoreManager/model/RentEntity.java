package com.wda.bookstoreManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wda.bookstoreManager.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rent")
public class RentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    //@JsonIgnoreProperties("rents")
    //@JsonIgnore
    @JoinColumn
    private BooksEntity books;

    @ManyToOne(cascade = CascadeType.MERGE)
    //@JsonIgnoreProperties("users")
   // @JsonIgnore
    @JoinColumn
    private UsersEntity users;

    @Column(name = "rental_date")
    private LocalDate rental_date;

    @Column(name = "forecast_return", nullable = false)
    private LocalDate forecast_return;

    @Column(name = "return_date", nullable = false)
    private LocalDate return_date;

    @Enumerated(EnumType.STRING)
    private Status status;

}
