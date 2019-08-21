package com.example.sixthweek.web.validation;

import com.example.sixthweek.web.dto.SearchBook;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SearchBookValidator implements ConstraintValidator<ValidSearchBook, SearchBook> {

    private boolean isValidStringValue(String value) {
        return !(value == null
                || value.trim().isEmpty());
    }

    private boolean isValidInteger(Integer value) {
        return value != null;
    }

    @Override
    public boolean isValid(SearchBook searchBook, ConstraintValidatorContext constraintValidatorContext) {
        return isValidStringValue(searchBook.getAuthorName()) ||
                isValidStringValue(searchBook.getGenreName()) ||
                isValidInteger(searchBook.getProductionYearTo()) ||
                isValidInteger(searchBook.getProductionYearFrom());
    }
}
