package tarikalovebird.money;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by TunasanG on 23/3/2560.
 */

public class DDATE {
    public String printDate(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = sdf.format(new Date(year, month, day));
        return formatedDate;
    }

    public Date getDate(int year, int month, int day) {
        Date date;
        return date = new GregorianCalendar(year, month, day).getTime();
    }
}
