package com.sagara.momnkids.utils;

import java.time.LocalDate;
import java.time.Month;

public final class PregnancyCalc {
    
    public static final int DAYS_7 = 7;
    public static final int MONTH_3 = 3;
    public static final int MONTH_9 = 9;
    public static final int YEAR_1 = 1;

    public static LocalDate pregdue(LocalDate date) {
        System.out.println(date.getDayOfMonth());
        if (date.getDayOfMonth() < 25) {
            if (date.getMonth() == Month.JANUARY
                    || date.getMonth() == Month.FEBRUARY
                    || date.getMonth() == Month.MARCH) {
                return case1(date);
            } else {
                return case2(date);
            }
        }else{
            return case2(date);
        }

    }

    public static LocalDate case1(LocalDate date) {
        LocalDate dueDate = date;
        dueDate = dueDate.plusDays(DAYS_7);
        dueDate = dueDate.plusMonths(MONTH_9);
        return dueDate;
    }

    public static LocalDate case2(LocalDate date) {
        LocalDate dueDate = date;
        dueDate = dueDate.plusDays(DAYS_7);
        dueDate = dueDate.minusMonths(MONTH_3);
        dueDate = dueDate.plusYears(YEAR_1);
        return dueDate;
    }
}
