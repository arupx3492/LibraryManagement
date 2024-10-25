package com.example.Library.management.service;

import com.example.Library.management.entity.Author;

import java.util.List;

public interface AuthorService {

    Author createAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(int authorId);

    List<Author> getAuthors();
    Author getAuthorById(int authorId);
}
