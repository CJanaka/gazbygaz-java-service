package com.gazbygaz.common;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Util {

    private Util(){}
    public static String formatDate(Date date){

        if (date == null){
            log.warn("date empty");
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }
}
