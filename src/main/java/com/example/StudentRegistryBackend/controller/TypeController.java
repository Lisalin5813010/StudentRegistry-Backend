package com.example.StudentRegistryBackend.controller;

import com.example.StudentRegistryBackend.common.Result;
import com.example.StudentRegistryBackend.model.Type;
import com.example.StudentRegistryBackend.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/type")
public class TypeController {
  @Autowired
  private TypeService typeService;
  private static final Logger logger = LoggerFactory.getLogger(TypeController.class);


  @GetMapping
  public Result findAll() {
    List<Type> types = typeService.getAllTypes();
    return Result.success(types);

  }
  @PostMapping
  public Result save(@RequestBody Type type) {
    //logger.info("Hi, the new info is here" + type.getName()) ;
    if (type.getId() == null) {

      typeService.add(type);
      //
    } else {
      typeService.update(type);
      //
    }
    return Result.success();
  }
  @GetMapping("/search")
    public Result findBySearch(@RequestParam String name) {
        Type type = typeService.findBySearch(name);
        return type != null ? Result.success(type) : Result.error("Type not founddddd");
    }


  @DeleteMapping("/{id}")
  public Result deleteTypeById(@PathVariable Long id) {
    typeService.deleteTypeById(id);
    return Result.success("Type deleted successfully");
  }
  @PutMapping("/delBatch")
  public Result delBatch(@RequestParam List<Type> list) {
    logger.info("I am hereeeeeeee");
    for (Type type : list){
      typeService.deleteTypeById(type.getId());
      logger.info(type.getName());
    }
    return Result.success("Types deleted successfully");
  }
  //@GetMapping("/export")
  /*public Result export() {
    //要一行一行组装数据，装进list里面
    //每一行数据，其实对应数据库表中的一个对象，也就是对应java实体类Type
    //我们怎么知道某一列对应哪个表头呢

  }*/

}
