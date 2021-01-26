package projectbookapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectbookapplication.repository.BookRepository;
import projectbookapplication.repository.entity.Book;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public List<Book> findByType(String Types){
        return bookRepository.findByType(Types);
    }

    public List<Book> findByAuthor(String Authors){
        return bookRepository.findByAuthor(Authors);
    }

    public List<Book> findByYearRelease(String YearRelease){
        return bookRepository.findByYearRelease(YearRelease);
    }

    public List<Book> showFavoriteBook(){
        return bookRepository.findFavoriteBooks();
    }

    public List<Book> showBookSortByPrice(){
        return bookRepository.sortByPrice();
    }

    // Post
    public Book insertBook(Book book){
        Book newBook = new Book();
        newBook.setIdUser(book.getIdUser());
        newBook.setAuthor(book.getAuthor());
        newBook.setLink_img(book.getLink_img());
        newBook.setPrices(book.setPrices());
        newBook.setTitle(book.getTitle());
        newBook.setType(book.getType());
        newBook.setYearRealease(book.getYearRealease());
        newBook.setDescription(book.getDescription());

        return bookRepository.save(newBook);
    }



}
