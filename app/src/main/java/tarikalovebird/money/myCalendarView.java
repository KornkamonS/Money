package tarikalovebird.money;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.Calendar;

/**
 * Created by TunasanG on 25/3/2560.
 */

public class myCalendarView extends Activity {
    private int mYear;
    private int mMonth;
    private int mDay;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalendarView calendar = new CalendarView(this);
        mMonth= Calendar.MONTH+1;
        mYear=Calendar.YEAR;
        mDay=Calendar.DAY_OF_MONTH;
        calendar.setOnDateChangeListener(mDateSetListener);
        calendar.setMaxDate(System.currentTimeMillis());
        setContentView(calendar);

    }

    // the callback received when the user "sets" the date in the dialog
    private CalendarView.OnDateChangeListener mDateSetListener =
            new CalendarView.OnDateChangeListener() {

                public void onSelectedDayChange(CalendarView view, int year,
                                                int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    Get_datefromCalender a=new Get_datefromCalender(getApplicationContext());

                    a.setDay(mDay);
                    a.setMonth(mMonth+1);
                    a.setYear(mYear);
                    helpcode aa=new helpcode();
                    String selectedDate = new StringBuilder().append(mDay).append(" ").append(aa.getMonthtext(mMonth+1)).append(" ")
                            .append(mYear).append(" ").toString();

                    Bundle b = new Bundle();
                    b.putString("dateSelected", selectedDate);

                    //Add the set of extended data to the intent and start it
                    Intent intent = new Intent();
                    intent.putExtras(b);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            };
}
