package projectbookapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projectbookapplication.repository.entity.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findById(Integer id);

    List<Book> findByTitle(String title);

    List<Book> findByIdUser(String idUser);

    List<Book> findByType(String type);

    List<Book> findByAuthor(String author);

    List<Book> findByYearRelease(String yearRelease);

    @Query(nativeQuery = true,value = "SELECT * FROM books WHERE favorite = 1")
    List<Book> findFavoriteBooks ();

    @Query(nativeQuery = true,value = "SELECT * FROM books ORDER BY `prices` ASC")
    List<Book> sortByPrice();
}
