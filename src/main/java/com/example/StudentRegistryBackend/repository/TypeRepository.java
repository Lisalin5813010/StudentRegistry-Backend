package com.example.StudentRegistryBackend.repository;

import com.example.StudentRegistryBackend.model.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
  Type findByName(String name);

  @Query("SELECT t FROM Type t WHERE t.id = :id")
  Type selectByPrimaryKey(@Param("id") Integer id);
}