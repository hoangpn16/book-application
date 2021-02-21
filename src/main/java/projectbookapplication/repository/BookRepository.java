package projectbookapplication.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projectbookapplication.repository.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `title`LIKE %?1%")
    List<Book> findByTitle(String title,Pageable pageable);

    List<Book> findByIdUser(Integer idUser);

    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `type`LIKE %?1%")
    List<Book> findByType(String type, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `author`LIKE %?1%")
    List<Book> findByAuthor(String author,Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `year_realease`LIKE %?1%")
    List<Book> findByYearRelease(String yearRelease,Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `id_user`=?1")
    List<Book> findByIdUser(Integer idUser,Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM books WHERE favorite = 1")
    List<Book> findFavoriteBooks();

    @Query(nativeQuery = true, value = "SELECT * FROM books ORDER BY `prices` ASC")
    List<Book> sortByPrice();
}
