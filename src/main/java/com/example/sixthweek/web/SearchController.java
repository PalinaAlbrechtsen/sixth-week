package com.example.sixthweek.web;

import com.example.sixthweek.service.BookService;
import com.example.sixthweek.web.dto.SearchBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SearchController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/searchBooks")
    public String searchBooks(@Valid @RequestBody SearchBook searchBook) {
        return bookService.findBooksByFilter(searchBook);
    }
}
