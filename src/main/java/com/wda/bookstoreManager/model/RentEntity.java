package com.wda.bookstoreManager.model;

import com.wda.bookstoreManager.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JoinColumn(name = "booksEntity")
    private BooksEntity booksEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usersEntity_id_fk")
    private UsersEntity usersEntity;

    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rental_date;

    @Column(name = "forecast_return", nullable = false)
    private LocalDateTime forecast_return;

    @Column(name = "return_date", nullable = false)
    private LocalDateTime return_date;

    @Enumerated(EnumType.STRING)
    private Status status;

}
