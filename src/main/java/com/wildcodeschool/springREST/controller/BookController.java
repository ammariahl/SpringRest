package com.wildcodeschool.springREST.controller;

import com.wildcodeschool.springREST.entity.Book;
import com.wildcodeschool.springREST.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book findBooksById(@PathVariable int id){
        return bookRepository.findById((long) id).get();
    }

    @PostMapping("/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTitle = body.get("title");
        String searchDescription = body.get("description ");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTitle, searchDescription);
    }

    @PostMapping("")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        Book bookToUpdate = bookRepository.findById((long) id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById((long) id);
        return true;
    }
}
