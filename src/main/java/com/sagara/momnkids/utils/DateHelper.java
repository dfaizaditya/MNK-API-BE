package com.sagara.momnkids.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateHelper {

    public static LocalDate StringToDate(String s){

        LocalDate result = null;
        try{
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            result  = LocalDate.parse(s, dateFormat);
        }
    
        catch(Exception e){
            e.printStackTrace();
    
        }
        return result ;
    }
    
}
