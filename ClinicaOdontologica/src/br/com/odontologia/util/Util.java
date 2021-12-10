package br.com.odontologia.util;

import java.util.Date;
import java.sql.Timestamp;

public class Util {

    public static Timestamp dateToTimeStamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
