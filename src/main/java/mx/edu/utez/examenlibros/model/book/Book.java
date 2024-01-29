package mx.edu.utez.examenlibros.model.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 13, nullable = false)
    private Long isbn;
    @Column(length = 50, nullable = false)
    private String author_name;
    @Column(length = 50, nullable = false)
    private String author_surname;
    @Column(length = 5, nullable = false)
    private int num_pages;
    @Column(length = 50, nullable = false)
    private String category;
    @Column( nullable = false)
    private LocalDate published_date;
    @Column(unique = true)
    //Generado automatico
    private String folio;

    public Book(String name, Long isbn, String authorName, String authorSurname, int numPages, String category, LocalDate published_date) {
        this.name = name;
        this.isbn = isbn;
        this.author_name = authorName;
        this.author_surname = authorSurname;
        this.num_pages = numPages;
        this.category = category;
        this.published_date = published_date;
    }
}
