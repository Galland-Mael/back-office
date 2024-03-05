package project.backoffice.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

}
