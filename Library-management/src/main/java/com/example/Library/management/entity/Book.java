package com.example.Library.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private String isbn;

    private final Boolean availabilityStatus=true;

}
