package br.com.testedit.teste_tecnico_dti.CLI;

import br.com.testedit.teste_tecnico_dti.Entities.Book;
import br.com.testedit.teste_tecnico_dti.Service.BookService;
import br.com.testedit.teste_tecnico_dti.dto.BookDto;
import br.com.testedit.teste_tecnico_dti.util.DateUtils;
import br.com.testedit.teste_tecnico_dti.util.ValidationUtils;
import br.com.testedit.teste_tecnico_dti.util.BookValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@Component
public class CommandLineApp implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    private final Scanner scanner = new Scanner(System.in);

    public void run() throws Exception {
        run((String[]) null);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("=== COMMAND LINE APP INICIADA ===");
        log.info("Starting CommandLineApp");
        int choice = -1;
        System.out.println("Welcome to the System books");

        while (choice != 0) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List Books");
            System.out.println("2. Search Books for ID");
            System.out.println("3. Add Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listBooks();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 0:
                    System.out.println("System shutting down... byee");
                    break;
                default:
                    System.out.println("Invalid input. Please choose a valid option");
            }
        }
    }

    private void listBooks() {
        List<Book> books = bookService.findAll();
        if (books.isEmpty()) {
            System.out.println("There are no books in the system");
        } else {
            System.out.println("List of books in the system");
            books.forEach(book -> System.out.println(bookService.formatBookForDisplay(book)));
        }
    }

    private void updateBook() {
        System.out.println("=== Updating Book ===");
        System.out.println("Enter Book ID:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<Book> book = bookService.findById(id);
        if (book.isEmpty()) {
            System.out.println("Book not found!");
            return;
        }

        System.out.println("Current book: " + bookService.formatBookForDisplay(book.get()));
        System.out.println("\nEnter new information:");

        try {
            // 1ª Validação: BookValidator (validação manual + entrada de dados)
            String title = bookValidator.getValidatedTitle();
            String author = bookValidator.getValidatedAuthor();
            Integer pages = bookValidator.getValidatedPages();
            LocalDate publicationDate = bookValidator.getValidatedPublicationDate();
            String description = bookValidator.getValidatedDescription();

            // 2ª Validação: Bean Validation (ValidationUtils)
            BookDto dto = new BookDto(id, title, author, pages, publicationDate, description);
            ValidationUtils.validate(dto);

            // Se passou nas duas validações, atualiza o livro
            bookService.update(id, dto);
            System.out.println("Book updated successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private void deleteBook() {
        System.out.println("Enter Book ID");
        Long id = scanner.nextLong();
        try {
            bookService.delete(id);
            System.out.println("Book deleted successfully!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchBook() {
        System.out.println("Enter Book ID");
        Long id = scanner.nextLong();
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            System.out.println("Book found:");
            System.out.println(bookService.formatBookForDisplay(book.get()));
        } else {
            System.out.println("Book not found!");
        }
    }

    private void addBook() {
        System.out.println("=== Adding New Book ===");

        try {

            String title = bookValidator.getValidatedTitle();
            String author = bookValidator.getValidatedAuthor();
            Integer pages = bookValidator.getValidatedPages();
            LocalDate publicationDate = bookValidator.getValidatedPublicationDate();
            String description = bookValidator.getValidatedDescription();

            BookDto dto = new BookDto(null, title, author, pages, publicationDate, description);
            ValidationUtils.validate(dto);

            Book savedBook = bookService.create(dto);
            System.out.println("Book added successfully! ID: " + savedBook.getId());

        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
