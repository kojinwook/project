package com.example.project.note.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(value = "year", required = false) Integer year,
                              @RequestParam(value = "month", required = false) Integer month,
                              Model model) {
        LocalDate currentDate = LocalDate.now();
        if (year == null) year = currentDate.getYear();
        if (month == null) month = currentDate.getMonthValue();

        List<String> days = CalendarUtils.getDaysInMonth(year, month);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("days", days);

        return "Calendar";
    }
}
