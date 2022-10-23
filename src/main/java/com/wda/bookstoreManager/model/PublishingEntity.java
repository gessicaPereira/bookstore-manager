package com.wda.bookstoreManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publishing")
public class PublishingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "publishing", fetch = FetchType.LAZY)
    private List<BooksEntity> booksEntities;

}
