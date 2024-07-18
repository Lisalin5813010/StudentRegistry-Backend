// Represents the data model.
package com.example.StudentRegistryBackend.model;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "author")
  private String author;
  @Column(name = "price")
  private String price;
  @Column(name = "press")
  private String press;
  @Column(name = "img")
  private String img;
  @Transient
  private String token;

  public Book() {}

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getPress() {
    return press;
  }
  public void setPress(String press){
    this.press = press;
  }
  public String getImg() {
    return img;
  }
  public void setImg( String img){
    this.img = img;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

