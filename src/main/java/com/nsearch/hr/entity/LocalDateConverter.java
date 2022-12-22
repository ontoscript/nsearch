package com.nsearch.hr.entity;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends AbstractBeanField {
    private static final String FORMATTER_DATE_PATTERN_1 = "dd-MMM-yy";
    private static final String REGEX_DATE_PATTERN_1 = "^\\d\\d-\\w\\w\\w-\\d\\d$";
    private static final String FORMATTER_DATE_PATTERN_2 = "yyyy-MM-dd";
    private static final String REGEX_DATE_PATTERN_2 = "^\\d\\d\\d\\d-\\d\\d-\\d\\d$";

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        DateTimeFormatter formatter = null;
        if(s.matches(REGEX_DATE_PATTERN_1)){
            formatter = DateTimeFormatter.ofPattern(FORMATTER_DATE_PATTERN_1);
        }else if(s.matches(REGEX_DATE_PATTERN_2)){
            formatter = DateTimeFormatter.ofPattern(FORMATTER_DATE_PATTERN_2);
        }
        if(formatter != null) {
            return LocalDate.parse(s, formatter);
        } else{
            return null;
        }
    }
}
