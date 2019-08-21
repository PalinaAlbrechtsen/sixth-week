package com.example.sixthweek.service;

import com.example.sixthweek.web.dto.SearchBook;

public interface BService {

    String findBooksByFilter(SearchBook searchBook);
}
