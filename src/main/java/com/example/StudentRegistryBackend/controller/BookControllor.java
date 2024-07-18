package com.example.StudentRegistryBackend.controller;

import com.example.StudentRegistryBackend.common.Result;
import com.example.StudentRegistryBackend.model.Book;
import com.example.StudentRegistryBackend.service.BookService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/book")
public class BookControllor {


  private static final Logger logger = LoggerFactory.getLogger(BookControllor.class);

  @Autowired
  private BookService bookService;
  @GetMapping("")
  public Result findAll() {
    List<Book> books = bookService.getAllBooks();
    return Result.success(books);
  }
  @GetMapping("/search")
  public Result findBySearch(@RequestParam String name) {
    Book book = bookService.findByName(name);
    return book != null ? Result.success(book) : Result.error("Book not founddddd");
  }
  @PostMapping
  public Result save(@RequestBody Book book) {
    if (book.getId() == null) {
      logger.info("Book updated successfully");
      bookService.add(book);
    } else {
      logger.info("Hi, the new info is here" + book.getAuthor()) ;
      bookService.update(book);
    }
    return Result.success();
  }
  @DeleteMapping("/{id}")
  public Result deleteBookById(@PathVariable Long id) {
    bookService.deleteBookById(id);
    return Result.success("Book deleted successfully");
  }

}
