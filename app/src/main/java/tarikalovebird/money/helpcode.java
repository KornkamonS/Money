package tarikalovebird.money;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by tunas on 22-Apr-17.
 */

public class helpcode {

    public static int getIdpicType(int typeid)
    {
        switch (typeid)
        {
            case R.id.gift2: return  R.drawable.food1;
            case R.id.oil: return R.drawable.oil1;
            case R.id.home:  return R.drawable.home1;
            case R.id.elect: return R.drawable.elec1;
            case R.id.shop:  return R.drawable.shop1;
            case R.id.entertain: return R.drawable.entertain1;
            case R.id.pen:  return R.drawable.pen1;
            case R.id.study: return R.drawable.study1;
            case R.id.add2: return R.drawable.addd1;

            case R.id.gift: return R.drawable.gifts1;
            case R.id.salary: return R.drawable.salary1;
            case R.id.sale:  return R.drawable.sale1;
            case R.id.add: return R.drawable.add1;
            default: return R.drawable.add1;
        }
    }

    private static final String DAY_MONTH_YEAR_PATT="dd MMMM yyyy";
    public  static String formatDayMonthYear(int day , int monthOfYear, int year) {
        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);
        calendar.clear();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        SimpleDateFormat format = new SimpleDateFormat(
                DAY_MONTH_YEAR_PATT, Locale.getDefault());
        return format.format(calendar.getTime());
    }

}


