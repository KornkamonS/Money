package tarikalovebird.money;


import android.content.Context;
import android.content.SharedPreferences;
import android.provider.CalendarContract;
import android.support.annotation.DrawableRes;

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
    private final String KEY_Keep="KEEP";
    private final String KEY_STARTDay = "STARTday";
    private final String KEY_STARTMonth = "STARTMonth";
    private final String KEY_STARTYear = "STARTYear";
    private final String KEY_FinishTargetDay = "Fday";
    private final String KEY_FinishTargetMonth = "FMonth";
    private final String KEY_FinishTargetYear = "FYear";
    private final String KEY_HAVE="HAVE";
    private final String KEY_Bool="BOOL";

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
        public boolean setKeep()
        {
            if(getTargetDay()==0)  dbEditTarget.putFloat(KEY_Keep,getTargetPrice());
            else dbEditTarget.putFloat(KEY_Keep,getTargetPrice()/getTargetDay());
            return dbEditTarget.commit();
        }
        public boolean setBool(boolean b)
        {
            dbEditTarget.putBoolean(KEY_Bool,b);
            return dbEditTarget.commit();
        }
        public boolean getBool()
        {
            return dbTarget.getBoolean(KEY_Bool,false);

        }
        public float getKeep()
        {
            return dbTarget.getFloat(KEY_Keep,0);
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
           // float cost_in_month=0;
            int n_day=0;

            int day_in_month=now.getActualMaximum(Calendar.DAY_OF_MONTH);
            n_day=day_in_month-now.get(Calendar.DAY_OF_MONTH);
            if(n_day!=0)
            {
                return (getHAVE()-getTotalkeep())/n_day;
            }
            else return (getHAVE()-getTotalkeep());
        }
        public float getTotalkeep()
        {
            long passDay = getTargetDay()-getDiffDay();
            if(passDay==0)return 0;
            else return  getKeep()*passDay;
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
            return  getTargetPrice() - getTotalkeep();
        }
        public float getHAVE(){ return   dbTarget.getFloat(KEY_HAVE,0); }

        public void resetTarget()
        {
            setBool(false);
            setTargetDay(0);
            setTargetType(0);
            setTargetPrice(0);
            setDate(0,0,0);
            setFindate(0,0,0);
            setNameTarget("Target");
        }
}

