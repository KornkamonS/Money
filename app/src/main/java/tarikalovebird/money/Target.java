package tarikalovebird.money;


import android.content.Context;
import android.content.SharedPreferences;
import android.provider.CalendarContract;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import tarikalovebird.money.Summary.report_db.Report_data;

/**
 * Created by TunasanG on 20/3/2560.
 */

public class Target
{
    private final String KEY_Name = "TARGET";
    private final String KEY_Price = "PRICE";
    private final String KEY_Day ="DAY";
    private final String KEY_Type="TYPE";
    private final String KEY_STARTDay = "STARTday";
    private final String KEY_STARTMonth = "STARTMonth";
    private final String KEY_STARTYear = "STARTYear";
    private final String KEY_FinishTargetDay = "Fday";
    private final String KEY_FinishTargetMonth = "FMonth";
    private final String KEY_FinishTargetYear = "FYear";
    private final String KEY_HAVE="HAVE";


    public static String KEY_MISSIONFAIL="The mission failed !!";
      private SharedPreferences dbTarget;
      private SharedPreferences.Editor dbEditTarget;

        public  Target(Context context) {
            dbTarget = context.getSharedPreferences(KEY_Name, Context.MODE_PRIVATE);
            dbEditTarget = dbTarget.edit();
        }
        public boolean setNameTarget(String name){
            if(!name.isEmpty())
                dbEditTarget.putString(KEY_Name,name);
            else dbEditTarget.putString(KEY_Name,"Target");
            return dbEditTarget.commit();
        }
        public boolean setTargetType(int type){
                dbEditTarget.putInt(KEY_Type,type);
            return dbEditTarget.commit();
        }
        public boolean setTargetPrice(float p) {

            if(p==0)
                return false;
            else {
                dbEditTarget.putFloat(KEY_Price, p);
                return dbEditTarget.commit();
            }
        }
        public boolean setTargetDay(int d) {
        if(d==0)
            return false;
        else {
            dbEditTarget.putInt(KEY_STARTDay, d);
            return dbEditTarget.commit();
            }
        }
        public boolean setTargetCountDownDay(int d)        {
            if (d == 0)
                return false;
            else {dbEditTarget.putInt(KEY_Day, d);
                return dbEditTarget.commit();
            }
        }
        public boolean setTargetMonth(int d) {
        if (d == 0)
            return false;
        else {dbEditTarget.putInt(KEY_STARTMonth, d);
            return dbEditTarget.commit();        }
        }
        public boolean setTargetYear(int d) {
        if (d == 0)
            return false;
        else {dbEditTarget.putInt(KEY_STARTYear, d);
            return dbEditTarget.commit();}
        }
        public boolean setHAVE(float have){
            dbEditTarget.putFloat(KEY_HAVE,have);
            return dbEditTarget.commit();
        }

        public boolean setDate(int day,int month,int year) {
            boolean flag=true;
            flag=setTargetDay(day);
            if(flag==false)
                return flag;
            flag=setTargetMonth(month);
            if(flag==false)
                return flag;
            flag=setTargetYear(year);
            if(flag==false)
                return flag;

            Calendar c = Calendar.getInstance();
            c.set(getSTARTyear(), getSTARTmonth(), getSTARTday(), 0, 0);
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + getTargetDay());

            flag=setFindate(c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            if(flag==false)
                return flag;
            return flag;

        }
        public boolean setFindate(int year,int month,int day){
            dbEditTarget.putInt(KEY_FinishTargetYear,year );
            dbEditTarget.putInt(KEY_FinishTargetMonth, month );
            dbEditTarget.putInt(KEY_FinishTargetDay, day);
            return dbEditTarget.commit();
        }
        public String getCountDown() {
            long d=getDiffDay();
            if(d<0)
                return KEY_MISSIONFAIL;
            return "CountDown: "+String.valueOf(d)+" Days";
        }
        public long getDelay(){
            long d=getDiffDay();
            if(d<0)
                return d*-1;
            else return 0;
        }
        public long getDiffDay(){
            if(getTargetDay()==0) return 0;
            else{
                Calendar c = Calendar.getInstance();
                c.set(getFyear(), getFmonth(), getFday(), 0, 0);
                long msDiff =  c.getTimeInMillis()-Calendar.getInstance().getTimeInMillis();
                long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
                return daysDiff;
            }
        }
        public float CanuseToday(){
            Calendar now = Calendar.getInstance();
            float cost_in_month=0;
            int diff_month=getFmonth()-now.get(Calendar.MONTH);
            int n_day=0;
            if(diff_month!=0)
            {
                int day_in_month=now.getActualMaximum(Calendar.DAY_OF_MONTH);
                n_day=day_in_month-now.get(Calendar.DAY_OF_MONTH);
                cost_in_month=getTargetPrice()/diff_month;
                if(n_day==0) return getHAVE()-cost_in_month;
                else return (getHAVE()-cost_in_month)/n_day;
            }
            else
            {
                if(getDiffDay()!=0) {
                    cost_in_month =(getHAVE()-getTargetPrice()) / getDiffDay();
                    return cost_in_month;
                }
                else {
                    cost_in_month = getHAVE()-getTargetPrice();
                    return cost_in_month;
                }
            }

        }
        public String getTargetName()
        {
            return dbTarget.getString(KEY_Name,"Target");
        }
        public int getTargetDay()
        {
            return dbTarget.getInt(KEY_Day,0);
        }
        public float getTargetPrice()
        {
            return dbTarget.getFloat(KEY_Price,0);
        }
        public int getTargetType(){return dbTarget.getInt(KEY_Type,0);}
        public String getSTARTdate() {

            return helpcode.formatDayMonthYear(getSTARTday(),getSTARTmonth(),getSTARTyear());
    }
        public String getFdate() {

        return helpcode.formatDayMonthYear(getFday(),getFmonth(),getFyear());
        }
        public int getSTARTyear() {
        return dbTarget.getInt(KEY_STARTYear, 0);
    }
        public int getSTARTmonth() {
        return dbTarget.getInt(KEY_STARTMonth, 0);
    }
        public int getSTARTday() {
        return dbTarget.getInt(KEY_STARTDay, 0);
    }

        public int getFyear() {
        return dbTarget.getInt(KEY_FinishTargetYear, 0);
    }
        public int getFmonth() {return dbTarget.getInt(KEY_FinishTargetMonth, 0);}
        public int getFday() {
        return dbTarget.getInt(KEY_FinishTargetDay, 0);
    }
        public float getRest(){
            return  getTargetPrice() - getHAVE();
        }
        public float getHAVE(){ return   dbTarget.getFloat(KEY_HAVE,0); }


}

