package com.example.sixthweek.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoundBook {

    private String name;
    private Integer creationYear;
    private String genreName;
    private String authorName;

    public String toCsv() {
        return Stream.of(name, authorName, genreName, String.valueOf(creationYear))
                .collect(Collectors.joining(","));
    }
}
