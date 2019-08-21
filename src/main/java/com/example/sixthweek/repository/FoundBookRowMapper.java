package com.example.sixthweek.repository;

import com.example.sixthweek.web.dto.FoundBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoundBookRowMapper implements RowMapper<FoundBook> {

    @Override
    public FoundBook mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoundBook.builder()
                .authorName(rs.getString("a_name"))
                .genreName(rs.getString("g_name"))
                .creationYear(rs.getInt("year_create"))
                .name(rs.getString("book_name"))
                .build();
    }
}
