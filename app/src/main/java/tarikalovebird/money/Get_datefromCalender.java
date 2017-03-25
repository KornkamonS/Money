package tarikalovebird.money;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

/**
 * Created by TunasanG on 25/3/2560.
 */

public class Get_datefromCalender {

    private final String KEY_DATE="DATE";
    private final String KEY_DAY = "DAY";
    private final String KEY_MONTH ="MONTH";
    private final String KEY_YEAR="YEAR";

    private SharedPreferences date;
    private SharedPreferences.Editor dateEdit;

    public  Get_datefromCalender(Context context)
    {
        date = context.getSharedPreferences(KEY_DATE, Context.MODE_PRIVATE);
        dateEdit = date.edit();
    }

    public boolean setDay(int day){
        if(day==0)
            return false;
        else dateEdit.putInt(KEY_DAY,day);
        return dateEdit.commit();
    }
    public boolean setMonth(int month){
        if(month==0)
            return false;
        else dateEdit.putInt(KEY_MONTH,month);
        return dateEdit.commit();
    }
    public boolean setYear(int year){
        if(year==0)
            return false;
        else dateEdit.putInt(KEY_YEAR,year);
        return dateEdit.commit();
    }
    public int getDay() {
        return date.getInt(KEY_DAY,Calendar.DAY_OF_MONTH);
    }
    public int getMonth() {
        return date.getInt(KEY_MONTH,Calendar.MONTH);
    }
    public int getYear() {
        return date.getInt(KEY_YEAR,Calendar.YEAR);
    }

}