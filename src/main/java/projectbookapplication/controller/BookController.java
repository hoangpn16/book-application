package projectbookapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import projectbookapplication.repository.BookRepository;
import projectbookapplication.repository.entity.BookEntity;
import projectbookapplication.service.BookService;
import projectbookapplication.service.SecurityService;
import projectbookapplication.service.model.FixBook;
import projectbookapplication.service.model.SearchBook;


import java.util.List;


@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookrepo;
    @Autowired
    SecurityService securityService;

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<BookEntity> searchBook(@RequestBody SearchBook searchmodel,
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
            List<BookEntity> result = bookrepo.findByTitle(searchmodel.getTitle(), pageRequest);
            return result;
        }

        if (searchmodel.getType() != null) {
            List<BookEntity> result = bookrepo.findByType(searchmodel.getType(), pageRequest);
            return result;
        }
        if (searchmodel.getAuthor() != null) {
            List<BookEntity> result = bookrepo.findByAuthor(searchmodel.getAuthor(), pageRequest);
            return result;
        }
        if (searchmodel.getYearRelease() != null) {
            List<BookEntity> result = bookrepo.findByYearRelease(searchmodel.getYearRelease(), pageRequest);
            return result;
        }
        if (searchmodel.getIduser() != null) {
            List<BookEntity> result = bookrepo.findByIdUser(searchmodel.getIduser(), pageRequest);
            return result;
        }
        return null;
    }


    @GetMapping(value = "/showlistfavorite", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<BookEntity> showListFavorite(@RequestHeader(name = "token") String token) {
        securityService.validRequest(token,"book/showlistfavorite","GET");
        List<BookEntity> result = bookService.showFavoriteBook();
        return result;
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String insertBook(@RequestHeader(name = "token") String token,@RequestBody BookEntity book) {
        securityService.validRequest(token,"/book/insert","POST");
        bookService.insertBook(book);
        return "Complete upload book";
    }

    @PutMapping(value = "/changeinfor/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    BookEntity changeInForBook(@RequestHeader(name = "token") String token,@PathVariable("id") Integer id,
                               @RequestBody FixBook modelfix) {
        securityService.validRequest(token,"/book/changeinfor/","PUT");
        return bookService.changeInforBook(id, modelfix);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteBook(@PathVariable("id") Integer id,@RequestHeader(name = "token") String token) {
        securityService.validRequest(token,"book/delete/","DELETE")  ;
        bookService.deleteBook(id);
        return "Deleted";
    }

    @PutMapping(value = "/favorite/{id}")
    public BookEntity favoriteBook(@RequestHeader(name = "token") String token,@PathVariable("id") Integer id,
                                   @RequestParam("favorite") Boolean favorite) {
        securityService.validRequest(token,"/book/favorite/","PUT");
        return bookService.makeFavorite(id, favorite);
    }

}
