package br.com.testedit.teste_tecnico_dti.util;

import br.com.testedit.teste_tecnico_dti.dto.BookDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
@Slf4j
public class BookValidator {

    Scanner scanner = new Scanner(System.in);

    public String getValidatedTitle() {
        log.info("Starting get validated title process");
        String title;
        do {
            System.out.print("Enter Book Title: ");
            title = scanner.nextLine();
            log.debug("User entered title: '{}'", title);
            BookDto tempDto = new BookDto();
            tempDto.setTitle(title);
            ValidationResult result = ValidationUtils.validateField(tempDto, "title");
            if (!result.isValid()) {
                log.warn("Title validation failed: {}", result.getErrorMessage());
                System.out.println(result.getErrorMessage());
            } else {
                log.info("Title validation passed: '{}'", title);
            }
        } while (title == null || title.trim().isEmpty());
        log.info("getValidatedTitle process completed successfully");
        return title.trim();
    }

    public String getValidatedAuthor() {
        log.info("Starting get validated author process");
        String author;
        do {
            System.out.print("Enter Author: ");
            author = scanner.nextLine();
            log.debug("User entered author: '{}'", author);
            BookDto tempDto = new BookDto();
            tempDto.setAuthor(author);
            ValidationResult result = ValidationUtils.validateField(tempDto, "author");
            if (!result.isValid()) {
                System.out.println(result.getErrorMessage());
            }
        } while (author == null || author.trim().isEmpty());
        return author.trim();
    }

    public Integer getValidatedPages() {
        log.info("Starting get validated pages process");
        Integer pages;
        do {
            System.out.print("Enter Number of Pages: ");
            try {
                String input = scanner.nextLine();
                log.debug("User entered pages: '{}'", input);
                pages = Integer.parseInt(input);
                BookDto tempDto = new BookDto();
                tempDto.setPages(pages);
                ValidationResult result = ValidationUtils.validateField(tempDto, "pages");
                if (!result.isValid()) {
                    log.warn("Pages validation failed: {}", result.getErrorMessage());
                    System.out.println(result.getErrorMessage());
                    pages = null;
                } else {
                    log.info("Pages validation passed: '{}'", pages);
                }
            } catch (NumberFormatException e) {
                log.error("Invalid number format: {}", e.getMessage());
                System.out.println("Please enter a valid number.");
                pages = null;
            }
        } while (pages == null);
        return pages;
    }

    public LocalDate getValidatedPublicationDate() {
        log.info("Starting get validated publication date process");
        LocalDate publicationDate = null;
        boolean validDate = false;
        while (!validDate) {

            System.out.print("Enter Publication Date (dd/MM/yyyy): ");
            String dateString = scanner.nextLine();
            try {
                publicationDate = DateUtils.parseDate(dateString);
                validDate = true;
                log.info("Publication date validation passed: '{}'", publicationDate);
            } catch (IllegalArgumentException e) {
                log.error("Invalid date format: {}", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        }
        return publicationDate;
    }

    public String getValidatedDescription() {
        log.info("Starting get validated description process");
        System.out.print("Enter Description (optional - press Enter to skip): ");
        String description = scanner.nextLine();
        if (description.trim().isEmpty()) {
            description = null;
        } else {
            log.info("Description validation passed: '{}'", description);
        }
        log.info("getValidatedDescription process completed successfully");
        return description;
    }

    public Long getValidatedId() {
        log.info("Starting get validated id process");
        Long id;
        do {
            try {
                id = scanner.nextLong();
                scanner.nextLine();
                log.info("Id validation passed: '{}'", id);
                return id;
            } catch (Exception e) {
                log.error("Invalid id format: {}", e.getMessage());
                System.out.println("Please enter a valid ID number.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                id = null;
            }
        } while (id == null);
        return id;
    }

}
