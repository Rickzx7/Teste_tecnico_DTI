package br.com.testedit.teste_tecnico_dti.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.testedit.teste_tecnico_dti.dto.BookDto;
import br.com.testedit.teste_tecnico_dti.entities.Book;
import br.com.testedit.teste_tecnico_dti.repository.BookRepository;
import br.com.testedit.teste_tecnico_dti.service.BookService;
import br.com.testedit.teste_tecnico_dti.util.DateUtils;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Should create a new book successfully")
    void testCreate() {

        BookDto dto = new BookDto();
        dto.setTitle("Clean Code");
        dto.setAuthor("Robert Martin");
        dto.setPages(464);
        dto.setPublicationDate(LocalDate.of(2008, 8, 1));
        dto.setDescription("A Handbook of Agile Software Craftsmanship");

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setTitle("Clean Code");
        expectedBook.setAuthor("Robert Martin");
        expectedBook.setPages(464);
        expectedBook.setPublicationDate(LocalDate.of(2008, 8, 1));
        expectedBook.setDescription("A Handbook of Agile Software Craftsmanship");

        when(bookRepository.save(any(Book.class))).thenReturn(expectedBook);

        Book result = bookService.create(dto);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Clean Code");
        assertThat(result.getAuthor()).isEqualTo("Robert Martin");
        assertThat(result.getPages()).isEqualTo(464);
        assertThat(result.getPublicationDate()).isEqualTo(LocalDate.of(2008, 8, 1));
        assertThat(result.getDescription()).isEqualTo("A Handbook of Agile Software Craftsmanship");

    }

    @Test
    @DisplayName("Should find book by ID successfully")
    void testFindById() {
        Long bookId = 1L;
        Book expectedBook = new Book();
        expectedBook.setId(bookId);
        expectedBook.setTitle("Clean Code");
        expectedBook.setAuthor("Robert Martin");
        expectedBook.setPages(464);
        expectedBook.setPublicationDate(LocalDate.of(2008, 8, 1));
        expectedBook.setDescription("A Handbook of Agile Software Craftsmanship");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(expectedBook));

        Optional<Book> result = bookService.findById(bookId);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(bookId);
        assertThat(result.get().getTitle()).isEqualTo("Clean Code");

        verify(bookRepository).findById(bookId);
    }

    @Test
    @DisplayName("Should return empty Optional when book does not exist")
    void testFindByIdNotFound() {

        Long bookId = 999L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Optional<Book> result = bookService.findById(bookId);

        assertThat(result).isEmpty();
        verify(bookRepository).findById(bookId);
    }

    @Test
    @DisplayName("Should list all books")
    void testFindAll() {

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert Martin");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Effective Java");
        book2.setAuthor("Joshua Bloch");

        List<Book> expectedBooks = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> result = bookService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Clean Code");
        assertThat(result.get(1).getTitle()).isEqualTo("Effective Java");

        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no books exist")
    void testFindAllEmpty() {

        when(bookRepository.findAll()).thenReturn(Arrays.asList());

        List<Book> result = bookService.findAll();

        assertThat(result).isEmpty();
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("Should update existing book successfully")
    void testUpdate() {
        // Arrange
        Long bookId = 1L;
        BookDto dto = new BookDto();
        dto.setTitle("Clean Code Updated");
        dto.setAuthor("Robert Martin");
        dto.setPages(500);
        dto.setPublicationDate(LocalDate.of(2008, 8, 1));
        dto.setDescription("Updated description");

        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle("Clean Code");
        existingBook.setAuthor("Robert Martin");
        existingBook.setPages(464);

        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setTitle("Clean Code Updated");
        updatedBook.setAuthor("Robert Martin");
        updatedBook.setPages(500);
        updatedBook.setPublicationDate(LocalDate.of(2008, 8, 1));
        updatedBook.setDescription("Updated description");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        Book result = bookService.update(bookId, dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(bookId);
        assertThat(result.getTitle()).isEqualTo("Clean Code Updated");
        assertThat(result.getPages()).isEqualTo(500);
        assertThat(result.getDescription()).isEqualTo("Updated description");

        verify(bookRepository).findById(bookId);
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    @DisplayName("Should throw exception when trying to update non-existent book")
    void testUpdateNotFound() {

        Long bookId = 999L;
        BookDto dto = new BookDto();
        dto.setTitle("Clean Code Updated");

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.update(bookId, dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Book not found!");

        verify(bookRepository).findById(bookId);
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    @DisplayName("Should delete existing book successfully")
    void testDelete() {

        Long bookId = 1L;
        when(bookRepository.existsById(bookId)).thenReturn(true);

        bookService.delete(bookId);

        verify(bookRepository).existsById(bookId);
        verify(bookRepository).deleteById(bookId);
    }

    @Test
    @DisplayName("Should throw exception when trying to delete non-existent book")
    void testDeleteNotFound() {

        Long bookId = 999L;
        when(bookRepository.existsById(bookId)).thenReturn(false);

        assertThatThrownBy(() -> bookService.delete(bookId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Book not found!");

        verify(bookRepository).existsById(bookId);
        verify(bookRepository, never()).deleteById(bookId);
    }

    @Test
    @DisplayName("Should convert Book to BookDto")
    void testToDto() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert Martin");
        book.setPages(464);
        book.setPublicationDate(LocalDate.of(2008, 8, 1));
        book.setDescription("A Handbook of Agile Software Craftsmanship");

        BookDto result = bookService.toDto(book);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Clean Code");
        assertThat(result.getAuthor()).isEqualTo("Robert Martin");
        assertThat(result.getPages()).isEqualTo(464);
        assertThat(result.getPublicationDate()).isEqualTo(LocalDate.of(2008, 8, 1));
        assertThat(result.getDescription()).isEqualTo("A Handbook of Agile Software Craftsmanship");
    }

    @Test
    @DisplayName("Should format book for display with description")
    void testFormatBookForDisplay() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert Martin");
        book.setPages(464);
        book.setPublicationDate(LocalDate.of(2008, 8, 1));
        book.setDescription("A Handbook of Agile Software Craftsmanship");

        String result = bookService.formatBookForDisplay(book);

        assertThat(result).contains("ID: 1");
        assertThat(result).contains("Title: Clean Code");
        assertThat(result).contains("Author: Robert Martin");
        assertThat(result).contains("Pages: 464");
        assertThat(result).contains("Publication Date: 01/08/2008");
        assertThat(result).contains("Description: A Handbook of Agile Software Craftsmanship");
    }

    @Test
    @DisplayName("Should format book for display without description")
    void testFormatBookForDisplayWithoutDescription() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert Martin");
        book.setPages(464);
        book.setPublicationDate(LocalDate.of(2008, 8, 1));
        book.setDescription(null);

        String result = bookService.formatBookForDisplay(book);

        assertThat(result).contains("ID: 1");
        assertThat(result).contains("Title: Clean Code");
        assertThat(result).contains("Author: Robert Martin");
        assertThat(result).contains("Pages: 464");
        assertThat(result).contains("Publication Date: 01/08/2008");
        assertThat(result).doesNotContain("Description:");
    }
}
