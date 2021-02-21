package projectbookapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import projectbookapplication.repository.BookRepository;
import projectbookapplication.repository.entity.Book;
import projectbookapplication.service.BookService;
import projectbookapplication.service.model.FixBook;
import projectbookapplication.service.model.SearchBook;


import java.awt.*;
import java.util.List;


@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookrepo;

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<Book> searchBook(@RequestBody SearchBook searchmodel,
                          @RequestParam(name = "page") Integer pageNum,
                          @RequestParam(name = "size") Integer pageSize,
                          @RequestParam(name = "sortBy") String sortBy,
                          @RequestParam(name = "orderBy") String orderBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        if (orderBy.equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);
        if (searchmodel.getTitle() != null) {
            List<Book> result = bookrepo.findByTitle(searchmodel.getTitle(), pageRequest);
            return result;
        }

        if (searchmodel.getType() != null) {
            List<Book> result = bookrepo.findByType(searchmodel.getType(), pageRequest);
            return result;
        }
        if (searchmodel.getAuthor() != null) {
            List<Book> result = bookrepo.findByAuthor(searchmodel.getAuthor(), pageRequest);
            return result;
        }
        if (searchmodel.getYearRelease() != null) {
            List<Book> result = bookrepo.findByYearRelease(searchmodel.getYearRelease(), pageRequest);
            return result;
        }
        if (searchmodel.getIduser() != null) {
            List<Book> result = bookrepo.findByIdUser(searchmodel.getIduser(), pageRequest);
            return result;
        }
        return null;
    }


    @GetMapping(value = "/showlistfavorite", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> showListFavorite() {
        List<Book> result = bookService.showFavoriteBook();
        return result;
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String insertBook(@RequestBody Book book) {
        bookService.insertBook(book);
        return "Complete upload book";
    }

    @PutMapping(value = "/changeinfor/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Book changeInForBook(@PathVariable("id") Integer id,
                         @RequestBody FixBook modelfix) {
        return bookService.changeInforBook(id, modelfix);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "Deleted";
    }

    @PutMapping(value = "/favorite/{id}")
    public Book favoriteBook(@PathVariable("id") Integer id,
                             @RequestParam("favorite") Boolean favorite) {
        return bookService.makeFavorite(id, favorite);
    }

}
