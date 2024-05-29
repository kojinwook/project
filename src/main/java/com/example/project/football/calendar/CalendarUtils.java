package com.example.project.football.calendar;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CalendarUtils {
    public static List<String> getDaysInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        List<String> days = new ArrayList<>();
        for (int day = 1; day <= daysInMonth; day++) {
            days.add(String.valueOf(day));
        }
        return days;
    }
}
