package projectbookapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import projectbookapplication.repository.BookRepository;
import projectbookapplication.repository.entity.Book;
import projectbookapplication.service.BookService;
import projectbookapplication.service.model.FixBook;

import javax.websocket.server.PathParam;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping(value = "/showall",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> showAll(){
        List<Book> result = bookService.findAll();
        return result;
    }

    @GetMapping(value = "/findbytype",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> getByType(@RequestParam("type") String type){
        List<Book> result = bookService.findByType(type);
        return result;
    }

    @GetMapping(value = "/findbytitle",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> getByTitle(@RequestParam("title") String title){
        List<Book> result = bookService.findByTitle(title);
        return result;
    }

    @GetMapping(value = "/findbyauthor",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> getByAuthor(@RequestParam("author") String author){
        List<Book> result = bookService.findByAuthor(author);
        return result;
    }

    @GetMapping(value = "/sortbyprice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> sortByPrice(@RequestParam("author") String author){
        List<Book> result = bookService.showBookSortByPrice();
        return result;
    }

    @GetMapping(value = "/showlistfavorite", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> showListFavorite(){
        List<Book> result= bookService.showFavoriteBook();
        return result;
    }

    @PostMapping(value = "/insert",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody String insertBook(@RequestBody Book book){
        bookService.insertBook(book);
        return "Complete upload book";
    }

    @PutMapping(value ="/changeinfor/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Book changeInForBook(@PathVariable("id") Integer id,
                                                @RequestBody FixBook modelfix){
        return bookService.changeInforBook(id,modelfix);
    }

    @DeleteMapping(value ="/delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteBook(@PathVariable("id") Integer id){
        bookService.deleteBook(id);
        return "Deleted";
    }

}
