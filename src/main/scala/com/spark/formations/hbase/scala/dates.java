package com.spark.formations.hbase.scala;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class dates {

    public static void main(String[] args) {

        String inventoryDate = "20190201";
        String datelimit = "20190208";
        String pathData = "C:\\Users\\ddiop\\Documents\\data";

        while( !getDateplusOne( inventoryDate).equals(datelimit))
        {
            final String path = pathData.concat(File.separator).concat(inventoryDate);
            System.out.println(path);
            inventoryDate = getDateplusOne( inventoryDate);
           // System.out.println(inventoryDate);
        }

    }
    static String  getDateplusOne( String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate  = LocalDate.parse(date, formatter).plusDays(1);
        return formatter.format(localDate);



    }
}
