package br.com.testedit.teste_tecnico_dti.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class BookDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Should create valid BookDto with all fields")
    void shouldCreateValidBookDtoWithAllFields() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(464);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));
        bookDto.setDescription("A Handbook of Agile Software Craftsmanship");

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should create valid BookDto without description")
    void shouldCreateValidBookDtoWithoutDescription() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(464);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));
        bookDto.setDescription(null);

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should fail validation when title is null")
    void shouldFailValidationWhenTitleIsNull() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(null);
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(464);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Title is mandatory");
    }

    @Test
    @DisplayName("Should fail validation when title is blank")
    void shouldFailValidationWhenTitleIsBlank() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(464);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Title is mandatory");
    }

    @Test
    @DisplayName("Should fail validation when title is only whitespace")
    void shouldFailValidationWhenTitleIsOnlyWhitespace() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("   ");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(464);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Title is mandatory");
    }

    @Test
    @DisplayName("Should fail validation when author is null")
    void shouldFailValidationWhenAuthorIsNull() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor(null);
        bookDto.setPages(464);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Author is mandatory");
    }

    @Test
    @DisplayName("Should fail validation when author is blank")
    void shouldFailValidationWhenAuthorIsBlank() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor("");
        bookDto.setPages(464);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Author is mandatory");
    }

    @Test
    @DisplayName("Should fail validation when pages is null")
    void shouldFailValidationWhenPagesIsNull() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(null);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Number of pages is mandatory");
    }

    @Test
    @DisplayName("Should fail validation when pages is zero")
    void shouldFailValidationWhenPagesIsZero() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(0);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Number of pages must be at least 1");
    }

    @Test
    @DisplayName("Should fail validation when pages is negative")
    void shouldFailValidationWhenPagesIsNegative() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(-5);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Number of pages must be at least 1");
    }

    @Test
    @DisplayName("Should pass validation when pages is exactly 1")
    void shouldPassValidationWhenPagesIsExactlyOne() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(1);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should handle multiple validation errors")
    void shouldHandleMultipleValidationErrors() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("");
        bookDto.setAuthor("");
        bookDto.setPages(-1);

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).hasSize(3);

        Set<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(messages).contains(
                "Title is mandatory",
                "Author is mandatory",
                "Number of pages must be at least 1");
    }

    @Test
    @DisplayName("Should allow empty description")
    void shouldAllowEmptyDescription() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Clean Code");
        bookDto.setAuthor("Robert Martin");
        bookDto.setPages(464);
        bookDto.setPublicationDate(LocalDate.of(2008, 8, 1));
        bookDto.setDescription("");

        Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should use all args constructor correctly")
    void shouldUseAllArgsConstructorCorrectly() {
        BookDto bookDto = new BookDto(
                1L,
                "Clean Code",
                "Robert Martin",
                464,
                LocalDate.of(2008, 8, 1),
                "A Handbook of Agile Software Craftsmanship");

        assertThat(bookDto.getId()).isEqualTo(1L);
        assertThat(bookDto.getTitle()).isEqualTo("Clean Code");
        assertThat(bookDto.getAuthor()).isEqualTo("Robert Martin");
        assertThat(bookDto.getPages()).isEqualTo(464);
        assertThat(bookDto.getPublicationDate()).isEqualTo(LocalDate.of(2008, 8, 1));
        assertThat(bookDto.getDescription()).isEqualTo("A Handbook of Agile Software Craftsmanship");
    }

    @Test
    @DisplayName("Should use no args constructor correctly")
    void shouldUseNoArgsConstructorCorrectly() {
        BookDto bookDto = new BookDto();

        assertThat(bookDto.getId()).isNull();
        assertThat(bookDto.getTitle()).isNull();
        assertThat(bookDto.getAuthor()).isNull();
        assertThat(bookDto.getPages()).isNull();
        assertThat(bookDto.getPublicationDate()).isNull();
        assertThat(bookDto.getDescription()).isNull();
    }
}
