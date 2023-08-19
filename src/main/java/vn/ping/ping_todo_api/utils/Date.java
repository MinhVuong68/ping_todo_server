package vn.ping.ping_todo_api.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public static LocalDate formatFromDateStringtoLocalDate(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date,df);
    }
}
