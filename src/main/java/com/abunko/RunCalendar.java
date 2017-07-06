package com.abunko;

/**
 * Created by Андрей on 03.07.2017.
 */
public class RunCalendar {
    public static void main(String[] args) {
        CalendarView calendarViewDefault = new ConsoleCalendarView();
        calendarViewDefault.showCalendar();
        System.out.println();

        for (int i = 1; i <= 12; i++){
            new ConsoleCalendarView(i).showCalendar();
            System.out.println();
        }
    }
}
