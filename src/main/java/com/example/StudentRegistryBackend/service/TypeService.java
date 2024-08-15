package com.example.StudentRegistryBackend.service;

import com.example.StudentRegistryBackend.exception.CustomException;
import com.example.StudentRegistryBackend.model.Book;
import com.example.StudentRegistryBackend.model.Type;
import com.example.StudentRegistryBackend.repository.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
  @Autowired
  private TypeRepository typeRepository;
  private static final Logger logger = LoggerFactory.getLogger(TypeService.class);
  public void add(Type type) {
    //logger.info("I am here");

    // 1. 用户名一定要有，否则不让新增（后面需要用户名登录）
    if (type.getName() == null || "".equals(type.getName())) {

      throw new CustomException("用户名不能为空");
    }
    // 2. 进行重复性判断，同一名字的书不允许重复新增：只要根据书名去数据库查询一下就可以了
    Type foundtype = typeRepository.findByName(type.getName());
    if (foundtype != null) {
      // 说明已经有了，这里我们就要提示前台不允许新增了
      throw new CustomException("该书已存在，请更换用户名");
    }
    //
    typeRepository.save(type);

  }
  public void update(Type type){
    typeRepository.save(type);
  }
  public Type findBySearch(String name){
    return typeRepository.findByName(name);
  }
  public List<Type> getAllTypes(){
    return typeRepository.findAll();
  }
  public void deleteTypeById(Long id) {
    Optional<Type> type = typeRepository.findById(id);
    if (type.isPresent()) {
      typeRepository.deleteById(id);
    } else {
      throw new CustomException("Type not found");
    }
  }
}
