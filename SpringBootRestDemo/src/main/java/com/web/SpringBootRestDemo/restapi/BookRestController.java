package com.web.SpringBootRestDemo.restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.SpringBootRestDemo.entity.Book;
import com.web.SpringBootRestDemo.service.BookService;
import com.web.SpringBootRestDemo.utility.AppConstants;
import com.web.SpringBootRestDemo.utility.Status;

@RestController
@RequestMapping("/books")
public class BookRestController {

    @Autowired
    private BookService bookService;
    
    public BookRestController() {
        System.out.println("Book Rest Controller default constructor");
    }
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String author){
        if(author==null)
            return bookService.getAllBooks();
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable  int id){
        Map<String, Object> map = new HashMap<>();
       // try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book",bookService.getBookById(id) );
            return ResponseEntity.ok(map);
//        }
//        catch (RuntimeException e){
//            map.put(AppConstants.STATUS, Status.FAILURE);
//            map.put("error",e.getMessage());
//            return ResponseEntity.badRequest().body(map);
//        }
    }

    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody  Book book){
        System.out.println("Book "+book);
        Map<String, Object> map = new HashMap<>();
        //try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book",bookService.addNewBook(book) );
            return ResponseEntity.ok(map);
  //      }
//        catch (RuntimeException e){
//            map.put(AppConstants.STATUS, Status.FAILURE);
//            map.put("error",e.getMessage());
//            return ResponseEntity.badRequest().body(map);
//        }
    }

    @PutMapping
    public ResponseEntity<Object> updateBook(@RequestBody  Book book){
        System.out.println("Book "+book);
        Map<String, Object> map = new HashMap<>();
       // try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book",bookService.updateBook(book) );
            return ResponseEntity.ok(map);
//        }
//        catch (RuntimeException e){
//            map.put(AppConstants.STATUS, Status.FAILURE);
//            map.put("error",e.getMessage());
//            return ResponseEntity.badRequest().body(map);
//        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable  int id){
        Map<String, Object> map = new HashMap<>();
       // try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            if(bookService.deleteBook(id)) {
                map.put("message", "Book deleted successfully");
                return ResponseEntity.ok(map);
            }
//        }
//        catch (RuntimeException e){
//            map.put(AppConstants.STATUS, Status.FAILURE);
//            map.put("error",e.getMessage());
//
//        }
        return ResponseEntity.badRequest().body(map);
    }
}
