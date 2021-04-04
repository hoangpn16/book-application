package projectbookapplication.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projectbookapplication.repository.entity.BookEntity;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `title`LIKE %?1%")
    List<BookEntity> findByTitle(String title, Pageable pageable);

    List<BookEntity> findByIdUser(Integer idUser);

    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `type`LIKE %?1%")
    List<BookEntity> findByType(String type, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `author`LIKE %?1%")
    List<BookEntity> findByAuthor(String author, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `year_realease`LIKE %?1%")
    List<BookEntity> findByYearRelease(String yearRelease, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM `book-application`.`books` WHERE `id_user`=?1")
    List<BookEntity> findByIdUser(Integer idUser, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM books WHERE favorite = 1")
    List<BookEntity> findFavoriteBooks();

    @Query(nativeQuery = true, value = "SELECT * FROM books ORDER BY `prices` ASC")
    List<BookEntity> sortByPrice();


}
