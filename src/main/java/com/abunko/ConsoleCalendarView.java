package com.abunko;

import sun.util.resources.LocaleData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

import static com.abunko.ConsoleColor.*;


/**
 * Created by Андрей on 03.07.2017.
 */
public class ConsoleCalendarView implements CalendarView {

    private final LocalDate nowDate;
    private final LocalDate date;
    private final Locale loc;

    ConsoleCalendarView(){
        nowDate = LocalDate.now();
        loc = Locale.getDefault();
        date = LocalDate.of(nowDate.getYear(), nowDate.getMonth().getValue(), 1);
    }

    ConsoleCalendarView(int month){
        nowDate = LocalDate.now();
        loc = Locale.getDefault();
        date = LocalDate.of(nowDate.getYear(), month, 1);
    }

    public void showCalendar(){
        printDayOfWeek();
        printDaysOfMonth();
        System.out.println();
    }

    private void printDayOfWeek() {

        System.out.println(" Текущая дата " + YElOW + nowDate.format(DateTimeFormatter.ofPattern("yyyy.LL.dd ")) + RESET);
        System.out.println(" " +date.getMonth().getDisplayName(TextStyle.FULL, Locale.UK));

        Arrays.stream(DayOfWeek.values()).map(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SUNDAY) ||
                dayOfWeek.equals(DayOfWeek.SATURDAY) ? " " + BLUE + dayOfWeek.getDisplayName(TextStyle.SHORT, loc) + RESET :
                " " + dayOfWeek.getDisplayName(TextStyle.SHORT, loc)).forEach(System.out::print);

        System.out.println();
    }

    private void printDaysOfMonth(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

        for ( int i = 1; i < DayOfWeek.from(date.getDayOfWeek()).getValue(); i++)
            System.out.print("   ");

        for (int j = 0; j < date.lengthOfMonth(); j++){
            LocalDate d = date.plusDays(j);

            if (d.equals(nowDate)) {
                if (d.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                    System.out.println(" " + YElOW + d.format(formatter) + RESET);
                else
                    System.out.print(" " + YElOW + d.format(formatter) + RESET);
            }
            else if(d.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                System.out.println(" " + BLUE + d.format(formatter) + RESET);
            }
            else if (d.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                System.out.print(" " + BLUE + d.format(formatter) + RESET);
            }
            else System.out.print(" " + d.format(formatter));
        }
    }
}