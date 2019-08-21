package com.example.sixthweek.service;

import com.example.sixthweek.repository.JdbcBookRepository;
import com.example.sixthweek.web.dto.FoundBook;
import com.example.sixthweek.web.dto.SearchBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BookService implements BService {

    @Autowired
    private JdbcBookRepository jdbcBookRepository;

    @Override
    public String findBooksByFilter(SearchBook searchBook) {
        if (searchBook.getAuthorName() == null) {
            searchBook.setAuthorName("");
        }
        if (searchBook.getGenreName() == null) {
            searchBook.setGenreName("");
        }
        if (searchBook.getProductionYearFrom() == null) {
            searchBook.setProductionYearFrom(jdbcBookRepository.getMinYear());
        }
        if (searchBook.getProductionYearTo() == null) {
            searchBook.setProductionYearTo(jdbcBookRepository.getMaxYear());
        }

        String foundBooks = jdbcBookRepository.getBooksByFilter(searchBook).stream()
                .map(FoundBook::toCsv)
                .collect(Collectors.joining(System.lineSeparator()));
        if (foundBooks.isEmpty()) {
            return "No books with this parameters";
        } else {
            return foundBooks;
        }
    }
}
