package controllers;

import exceptions.BookIdMismatchException;
import exceptions.BookNotFoundException;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import repo.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController{

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List<Book> findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id) {
        return "home";
        //bookRepository.findById(id);
                //.orElseThrow(BookNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id);
                //.orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        /*if (book.getId() != id) {
            throw new BookIdMismatchException("book.toString() does not exist", BookNotFoundException::new);
        }*/
        bookRepository.findById(id);
                //.orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
}