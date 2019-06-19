package common.helpers;

//   convert from a java util date, to a java sql date; best if it's a method for reuse.

public class DateHelper {

    public  static java.sql.Date utilDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date sqlDateToUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }

}


