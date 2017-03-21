package tarikalovebird.money;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import org.w3c.dom.Text;

/**
 * Created by TunasanG on 20/3/2560.
 */

public class Target
{
      private final String KEY_Name = "TARGET";
      private final String KEY_Price = "PRICE";
      private final String KEY_Day ="DAY";
      private final String KEY_Type="TYPE";



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

            dbEditTarget.putInt(KEY_Day, d);
            return dbEditTarget.commit();
            }
        }
        public String getCountDown()
        {
            return "CountDown: "+getTargetDay()+" Days";
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
        public int getTargetType(){
            return dbTarget.getInt(KEY_Type,0);}

}

