package tarikalovebird.money.Outcome.outcome_db;

/**
 * Created by Preaw-PC on 25/3/2560.
 */

public class Outcome_metaData {
    public static final String TABLE = "Outcome_db";

    public static final String KEY_ID = "id";
    public static final String KEY_Name = "name";
    public static final String KEY_Amount = "amount";
    public static final String KEY_Period = "Period";
    public static final String KEY_Type="Type";

    public int outcome_ID;
    public String name;
    public float amount;
    public String period;
    public int type;

    public void setValue(int id,String n,float a,String p,int t)
    {
        if(id!=-1) outcome_ID=id;
        name=n;
        amount=a;
        period=p;
        type=t;
    }
}
