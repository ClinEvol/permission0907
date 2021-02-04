package com.ujiuye.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String timeStr) {
        //timeStr前台传过来的字符串 2020/09/09  2020-09-09  12：23：23
        String patten="yyyy/MM/dd";
        if(timeStr.contains("-")){
            patten="yyyy-MM-dd";
        }
        if(timeStr.contains(":")){
            patten+=" HH:mm:ss";
        }
        SimpleDateFormat format=new SimpleDateFormat(patten);
        try {
            Date parse = format.parse(timeStr);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
