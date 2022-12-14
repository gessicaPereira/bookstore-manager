package com.wda.bookstoreManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "publishing")
public class PublishingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "publishing", fetch = FetchType.LAZY)
    private List<BooksEntity> books;

}
