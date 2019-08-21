package com.example.sixthweek.web.dto;

import com.example.sixthweek.web.validation.ValidSearchBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidSearchBook
public class SearchBook {

    private String authorName;
    private String genreName;
    private Integer productionYearFrom;
    private Integer productionYearTo;
}
