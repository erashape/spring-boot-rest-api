package com.healthapi.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
public class DateUtils {

    public static String getNow(String format) {
        format = "".equals(format) || isValid(format)  ? format : "yyyyMMdd";
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);
        return  LocalDateTime.now().format(timeFormatter);
    }

    public static boolean isValid(String format) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);

        try {
            LocalDateTime.now().format(timeFormatter);
        } catch (DateTimeException e) {
            log.info("날짜 포맷 파싱 에러, argument:[{}]", format);
            return false;
        }

        return true;
    }
}
