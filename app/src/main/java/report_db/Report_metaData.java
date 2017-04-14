package report_db;

/**
 * Created by Preaw-PC on 25/3/2560.
 */

public class Report_metaData {
    public static final String TABLE = "Outcome_db";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_Name = "name";
    public static final String KEY_Amount = "amount";
    public static final String KEY_Day = "Day";
    public static final String KEY_Month="Month";
    public static final String KEY_Year = "Year";
    public static final String KEY_InorOut = "InorOut";
    public static final String KEY_Type="Type";

    // property help us to keep data
    public int outcome_ID;
    public String name;
    public float amount;
    public int day;
    public int month;
    public int year;
    public int inorout;
    public int type;
}
