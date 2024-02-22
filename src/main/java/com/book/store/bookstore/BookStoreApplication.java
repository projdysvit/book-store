package com.book.store.bookstore;

import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
    private BookService bookService;

    public BookStoreApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("book");
            book.setDescripton("very cool book");
            book.setIsbn("idk");
            book.setAuthor("Jhon Idk");
            book.setPrice(BigDecimal.valueOf(199));
            book.setCoverImage("none");

            bookService.save(book);

            System.out.println(bookService.findAll());
        };
    }
}
