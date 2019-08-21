package com.example.sixthweek.repository;

import com.example.sixthweek.web.dto.FoundBook;
import com.example.sixthweek.web.dto.SearchBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcBookRepository implements BookRepository {

    private static final String GET_BOOKS_BY_FILTER = "SELECT b.name AS book_name," +
            "b.creation_year AS year_create, g.name AS g_name, a.name AS a_name " +
            "FROM library.book_storage.book AS b " +
            "INNER JOIN library.book_storage.genre AS g " +
            "ON b.genre_id = g.id " +
            "INNER JOIN library.book_storage.author AS a " +
            "ON b.author_id = a.id " +
            "WHERE a.name ILIKE :aname " +
            "AND g.name ILIKE :gname " +
            "AND creation_year >= :from_year " +
            "AND creation_year <= :to_year";

    private static final String GET_MAX_YEAR = "SELECT MAX(creation_year) FROM library.book_storage.book";

    private static final String GET_MIN_YEAR = "SELECT MIN(creation_year) FROM library.book_storage.book";

    @Autowired
    private NamedParameterJdbcTemplate parameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<FoundBook> getBooksByFilter(SearchBook searchBook) {
        MapSqlParameterSource mSPS = new MapSqlParameterSource();
        mSPS.addValue("aname", "%" + searchBook.getAuthorName() + "%");
        mSPS.addValue("gname", "%" + searchBook.getGenreName() + "%");
        mSPS.addValue("from_year", searchBook.getProductionYearFrom());
        mSPS.addValue("to_year", searchBook.getProductionYearTo());

        return parameterJdbcTemplate.query(GET_BOOKS_BY_FILTER, mSPS,
                new FoundBookRowMapper());
    }

    @Override
    public Integer getMaxYear() {
        return jdbcTemplate.queryForObject(GET_MAX_YEAR, Integer.class);
    }

    @Override
    public Integer getMinYear() {
        return jdbcTemplate.queryForObject(GET_MIN_YEAR, Integer.class);
    }
}
