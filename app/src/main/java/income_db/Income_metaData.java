package income_db;

/**
 * Created by TunasanG on 25/3/2560.
 */


public class Income_metaData {
    public static final String TABLE = "Income_db";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_Name = "name";
    public static final String KEY_Amount = "amount";
    public static final String KEY_Period = "Period";
    public static final String KEY_Type="Type";

    // property help us to keep data
    public int income_ID;
    public String name;
    public float amount;
    public String period;
    public int type;
}
