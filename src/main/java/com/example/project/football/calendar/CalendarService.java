package com.example.project.football.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public Calendar getCalendar(Long calendarId) {
        return calendarRepository.findById(calendarId).orElseThrow();
    }

    public List<Calendar> getCalendarList() {
        return calendarRepository.findAll();
    }

    public Calendar save(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    public void delete(Long id) {
        calendarRepository.deleteById(id);
    }

    public Calendar updateName(Long id, String name) {
        Calendar calendar = getCalendar(id);
        calendar.setName(name);
        return calendarRepository.save(calendar);
    }

        public void move(Long id, Long destinationId) {
        Calendar target = getCalendar(id);
        Calendar destination = getCalendar(destinationId);

        target.setParent(destination);
        calendarRepository.save(target);
    }

    public List<Calendar> getSearchedCalendarList(String keyword) {
        return calendarRepository.findByNameContaining(keyword);
    }
    public  Calendar getCalendarById(Long id){
        return calendarRepository.getCalendarById(id);
    }
}

