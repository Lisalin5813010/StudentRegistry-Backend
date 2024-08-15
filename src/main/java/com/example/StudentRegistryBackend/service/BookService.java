// Contains business logic
package com.example.StudentRegistryBackend.service;
import cn.hutool.core.collection.CollectionUtil;
import com.example.StudentRegistryBackend.exception.CustomException;
import com.example.StudentRegistryBackend.model.Book;
import com.example.StudentRegistryBackend.model.Type;
import com.example.StudentRegistryBackend.repository.BookRepository;
import com.example.StudentRegistryBackend.repository.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {

  private static final Logger logger = LoggerFactory.getLogger(BookService.class);

  @Autowired
  private BookRepository bookRepository;
  @Autowired
  TypeRepository typeRepository;

  public List<Book> getAllBooks() {
    List<Book> list = bookRepository.findAll();
    if(CollectionUtil.isEmpty(list)){
      return new ArrayList<>();
    }else{
      for (Book book: list)
      {
        Type type = typeRepository.selectByPrimaryKey(book.getTypeId());

        book.setTypeName(type.getName());
      }
        return list;
    }



  }
  public Book findByName(String name){
    return bookRepository.findByName(name);
  }




  public void add(Book book) {

    // 1. 用户名一定要有，否则不让新增（后面需要用户名登录）
    if (book.getName() == null || "".equals(book.getName())) {

      throw new CustomException("用户名不能为空");
    }
    // 2. 进行重复性判断，同一名字的书不允许重复新增：只要根据书名去数据库查询一下就可以了
    Book foundbook = bookRepository.findByName(book.getName());
    if (foundbook != null) {
      // 说明已经有了，这里我们就要提示前台不允许新增了
      throw new CustomException("该书已存在，请更换用户名");
    }
    //
    bookRepository.save(book);

  }

  public void update(Book book) {
    bookRepository.save(book);
  }

  public Book findByById(Long id) {
   return bookRepository.findById(id).orElse(null);
  }
  public void deleteBookById(Long id) {

    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      bookRepository.deleteById(id);
    } else {
      throw new CustomException("Book not found");
    }


  }



}