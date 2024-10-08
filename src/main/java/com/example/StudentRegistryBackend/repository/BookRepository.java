package com.example.StudentRegistryBackend.repository;


import com.example.StudentRegistryBackend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


  Book findByName(String name);


}
