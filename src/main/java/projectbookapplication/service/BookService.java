package projectbookapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectbookapplication.repository.BookRepository;
import projectbookapplication.repository.entity.Book;
import projectbookapplication.service.model.FixBook;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;


    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByType(String Types) {
        return bookRepository.findByType(Types);
    }

    public List<Book> findByAuthor(String Authors) {
        return bookRepository.findByAuthor(Authors);
    }

    public List<Book> findByYearRelease(String YearRelease) {
        return bookRepository.findByYearRelease(YearRelease);
    }

    public List<Book> showFavoriteBook() {
        return bookRepository.findFavoriteBooks();
    }

    public List<Book> showBookSortByPrice() {
        return bookRepository.sortByPrice();
    }

    // Post
    public Book insertBook(Book book) {
        Book newBook = new Book();
        newBook.setIdUser(book.getIdUser());
        newBook.setAuthor(book.getAuthor());
        newBook.setLink_img(book.getLink_img());
        newBook.setPrices(book.getPrices());
        newBook.setTitle(book.getTitle());
        newBook.setType(book.getType());
        newBook.setYearRelease(book.getYearRelease());
        newBook.setDescription(book.getDescription());

        return bookRepository.save(newBook);
    }

    // PUT
    public Book changeInforBook(Integer id, FixBook modelFix) {
        Book result = bookRepository.findById(id).orElse(null);
        if (result != null) {
            if (modelFix.getTitle() != null) {
                result.setTitle(modelFix.getTitle());
            }
            if (modelFix.getLink_img() != null) {
                result.setLink_img(modelFix.getLink_img());
            }
            if (modelFix.getType() != null) {
                result.setType(modelFix.getType());
            }
            if (modelFix.getAuthor() != null) {
                result.setAuthor(modelFix.getAuthor());
            }
            if (modelFix.getYearRelease() != null) {
                result.setYearRelease(modelFix.getYearRelease());
            }
            if (modelFix.getPrices() != null) {
                result.setPrices(modelFix.getPrices());
            }
            if (modelFix.getDescription() != null) {
                result.setDescription(modelFix.getDescription());
            }
            if (modelFix.isFavorite()) {
                result.setFavorite(modelFix.isFavorite());
            }
            return bookRepository.save(result);
        }
        return null;
    }
    public String deleteBook(Integer id){
        bookRepository.deleteById(id);
        return "DELETED";
    }

}
