package br.com.testedit.teste_tecnico_dti.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @NotNull(message = "Number of pages is mandatory")
    @Min(value = 1, message = "Number of pages must be at least 1")
    private Integer pages;

    private LocalDate publicationDate;
    private String description;
}
