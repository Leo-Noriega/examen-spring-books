package mx.edu.utez.examenlibros.controller.dto.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.examenlibros.model.book.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class BookDto {
    private Long id;
    @NotEmpty
    @Size(min = 3, max = 50, message = "NameSizeError")
    private String name;
    @NotNull
    private Long isbn;;
    @NotEmpty
    @Size(min = 3, max = 50, message = "NameSizeError")
    private String author_name;
    @NotEmpty
    @Size(min = 3, max = 50, message = "SurnameSizeError")
    private String author_surname;
    @NotNull
    private int num_pages;
    @NotEmpty
    @Size(min = 5, max = 50, message = "CategorySizeError")
    private String category;
    private LocalDate published_date;
    public Book toEntity() {
        return new Book(name, isbn, author_name, author_surname, num_pages, category, published_date);
    }
}
