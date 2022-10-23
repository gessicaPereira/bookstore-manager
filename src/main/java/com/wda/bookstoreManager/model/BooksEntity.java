package com.wda.bookstoreManager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BooksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "name")
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "launch", nullable = false)
    private LocalDate launch;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "publishing")
    private PublishingEntity publishing;

    @OneToMany(mappedBy = "booksEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentEntity> rentEntities;

}
