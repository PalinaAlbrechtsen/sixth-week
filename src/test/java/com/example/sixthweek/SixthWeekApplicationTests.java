package com.example.sixthweek;

import com.example.sixthweek.repository.JdbcBookRepository;
import com.example.sixthweek.service.BService;
import com.example.sixthweek.web.dto.FoundBook;
import com.example.sixthweek.web.dto.SearchBook;
import com.example.sixthweek.web.validation.SearchBookValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SixthWeekApplicationTests {

    @MockBean
    private SearchBookValidator searchBookValidator;
    @MockBean
    private JdbcBookRepository jdbcBookRepository;
    @Autowired
    private BService bService;

    @Test
    public void checkBookServiceSearchingBooks() {
        SearchBook searchBook = SearchBook.builder()
                .authorName("Philip")
                .productionYearTo(5000)
                .productionYearFrom(1000)
                .build();
        FoundBook first = FoundBook.builder()
                .authorName("Philip Shultz")
                .creationYear(2000)
                .genreName("Horror")
                .name("Don't read this book")
                .build();
        FoundBook second = FoundBook.builder()
                .name("Oh My Mock!")
                .genreName("Fun")
                .authorName("Philip Pene")
                .creationYear(1562)
                .build();
        ArrayList<FoundBook> books = new ArrayList<>();
        books.add(first);
        books.add(second);
        when(jdbcBookRepository.getBooksByFilter(searchBook)).thenReturn(books);

        String scvFile = "Don't read this book,Philip Shultz,Horror,2000\r\nOh My Mock!,Philip Pene,Fun,1562";
        String booksByFilter = bService.findBooksByFilter(searchBook);
        assertThat(booksByFilter).isEqualTo(scvFile);
    }

    @Test
    public void checkCustomValidator() {
        when(searchBookValidator.isValid(new SearchBook(), eq(any(ConstraintValidatorContext.class)))).thenReturn(false);
        assertThat(searchBookValidator.isValid(new SearchBook(), eq(any(ConstraintValidatorContext.class)))).isFalse();
    }
}
