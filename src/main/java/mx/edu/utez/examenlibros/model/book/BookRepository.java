package mx.edu.utez.examenlibros.model.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Spliterator;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    Optional<Book> findByFolio(String folio);


}
