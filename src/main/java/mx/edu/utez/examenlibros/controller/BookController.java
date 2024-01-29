package mx.edu.utez.examenlibros.controller;

import jakarta.validation.Valid;
import mx.edu.utez.examenlibros.config.ApiResponse;
import mx.edu.utez.examenlibros.controller.dto.book.BookDto;
import mx.edu.utez.examenlibros.model.book.Book;
import mx.edu.utez.examenlibros.service.BookService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody BookDto dto){
        return service.save(dto.toEntity());
    }
    @GetMapping("/{name}")
    public Optional<Book> getBook(@PathVariable String name) {
        return service.findByName(name);
    }
    @DeleteMapping("/{folio}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable String folio) {
        return service.deleteByFolio(folio);
    }
}
