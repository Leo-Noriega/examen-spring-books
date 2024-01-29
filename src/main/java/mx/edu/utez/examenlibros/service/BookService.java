package mx.edu.utez.examenlibros.service;

import mx.edu.utez.examenlibros.config.ApiResponse;
import mx.edu.utez.examenlibros.model.book.Book;
import mx.edu.utez.examenlibros.model.book.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity<ApiResponse> save(Book book) {
         /*
         El folio del libro se debe componer de la primera letra del título, la primera letra
         del nombre del autor, las primeras 2 letras del apellido del autor, la fecha de
         publicación (yyyy-MM-dd), las primeras 4 letras del ISBN y 2 dígitos random
         (letras y números).
          */
        Random random = new Random();
        StringBuilder folio = new StringBuilder();
        folio.append(book.getName().charAt(0));
        folio.append(book.getAuthor_name().charAt(0));
        //2 letras apellido
        folio.append(book.getAuthor_surname().split(" ")[0].charAt(0));
        folio.append(book.getAuthor_surname().toUpperCase().split(" ")[0].charAt(1));
        // jalar la fecha de publicacio
        folio.append(book.getPublished_date().toString().split("-")[0]);
        folio.append(book.getPublished_date().toString().split("-")[1]);
        folio.append(book.getPublished_date().toString().split("-")[2]);
        //4 numeros ISBn
        folio.append(book.getIsbn().toString().split(" ")[0].charAt(0));
        folio.append(book.getIsbn().toString().split(" ")[0].charAt(1));
        folio.append(book.getIsbn().toString().split(" ")[0].charAt(2));
        folio.append(book.getIsbn().toString().split(" ")[0].charAt(3));
        folio.append(random.nextInt(0, 9));
        folio.append(random.nextInt(0, 9));
        Optional<Book> foundBook = repository.findByName(book.getName());
        if (foundBook.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "BookAlreadyExist"), HttpStatus.BAD_REQUEST);
        }
        if (book.getFolio() != null)
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "BookAlreadyExist"), HttpStatus.BAD_REQUEST);
        book.setFolio(folio.toString());
        repository.saveAndFlush(book);
        return new ResponseEntity<>(new ApiResponse(book, HttpStatus.OK), HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public Optional<Book> findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    public ResponseEntity<ApiResponse> deleteByFolio(String folio) {
        Optional<Book> foundBook = repository.findByFolio(folio);
        if (foundBook.isPresent()) {
            repository.delete(foundBook.get());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "BookDeleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "BookNotFound"), HttpStatus.BAD_REQUEST);
    }
}
