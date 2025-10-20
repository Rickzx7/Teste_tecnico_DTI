package br.com.testedit.teste_tecnico_dti.service;

import br.com.testedit.teste_tecnico_dti.repository.BookRepository;
import br.com.testedit.teste_tecnico_dti.dto.BookDto;
import br.com.testedit.teste_tecnico_dti.entities.Book;
import br.com.testedit.teste_tecnico_dti.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public Book create(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPages(dto.getPages());
        book.setPublicationDate(dto.getPublicationDate());
        book.setDescription(dto.getDescription());
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book update(Long id, BookDto dto) {
        return bookRepository.findById(id).map(existsBook -> {
            existsBook.setTitle(dto.getTitle());
            existsBook.setAuthor(dto.getAuthor());
            existsBook.setPages(dto.getPages());
            existsBook.setPublicationDate(dto.getPublicationDate());
            existsBook.setDescription(dto.getDescription());
            return bookRepository.save(existsBook);
        }).orElseThrow(() -> new RuntimeException("Book not found!" + id));
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found!" + id);
        }
        bookRepository.deleteById(id);
    }

    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getPublicationDate(),
                book.getDescription());
    }

    public String formatBookForDisplay(Book book) {
        String descriptionDisplay = (book.getDescription() != null && !book.getDescription().trim().isEmpty())
                ? " | Description: " + book.getDescription()
                : "";
        return String.format("ID: %d | Title: %s | Author: %s | Pages: %d | Publication Date: %s%s",
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                DateUtils.formatDate(book.getPublicationDate()),
                descriptionDisplay);
    }

}
