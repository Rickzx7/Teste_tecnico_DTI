package br.com.testedit.teste_tecnico_dti.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String title;
   @Column
   private String author;
   @Column
   private Integer pages;
   @Column(name = "publication_date", columnDefinition = "DATE")
   private LocalDate publicationDate;
   @Column(nullable = true)
   private String description;

}
