package fr.gopartner.auth.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
public class DateUtils {

    private DateUtils(){}

    public static LocalDateTime convertLocalDateToLocalDateTime(LocalDate localDate){
        if(localDate == null){
            log.warn("cannot convert a null date");
            return null ;
        }
        return localDate.atStartOfDay();
    }

    public static LocalDate convertLocalDateTimeToLocalDate(LocalDateTime localDateTime){
        if(localDateTime == null){
            log.warn("cannot convert a null date");
            return null ;
        }
        return localDateTime.toLocalDate();
    }
}
