package tarikalovebird.money.Summary.report_db;

public class Report_metaData {
    public static final String TABLE = "Report_db";

    // Labels Table Columns names
    public static final String KEY_ID = "Id";
    public static final String KEY_Name = "Name";
    public static final String KEY_Amount = "Amount";
    public static final String KEY_Day = "Day";
    public static final String KEY_Month="Month";
    public static final String KEY_Year = "Year";
    public static final String KEY_InorOut = "InorOut";
    public static final String KEY_Type="Type";

    // property help us to keep data
    public int report_id;
    public String name;
    public float amount;
    public int day;
    public int month;
    public int year;
    public int inorout;
    public int type;
}
