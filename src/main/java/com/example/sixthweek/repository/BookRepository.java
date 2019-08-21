package com.example.sixthweek.repository;

import com.example.sixthweek.web.dto.FoundBook;
import com.example.sixthweek.web.dto.SearchBook;

import java.util.List;

public interface BookRepository {

    List<FoundBook> getBooksByFilter(SearchBook searchBook);
    
    Integer getMaxYear();
    
    Integer getMinYear();
}
