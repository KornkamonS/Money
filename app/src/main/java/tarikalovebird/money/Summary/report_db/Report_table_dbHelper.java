package tarikalovebird.money.Summary.report_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tarikalovebird.money.Income.income_db.Income_metaData;

public class Report_table_dbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "Report.db";

    public Report_table_dbHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_REPORT = "CREATE TABLE " + Report_metaData.TABLE  + "("
                + Report_metaData.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Report_metaData.KEY_Name + " TEXT, "
                + Report_metaData.KEY_Amount + " REAL, "
                + Report_metaData.KEY_Day + " INTEGER, "
                + Report_metaData.KEY_Month+" INTEGER , "
                + Report_metaData.KEY_Year + " INTEGER, "
                + Report_metaData.KEY_InorOut + " INTEGER, "
                + Report_metaData.KEY_Type+" INTEGER ) ";

        db.execSQL(CREATE_TABLE_REPORT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Report_metaData.TABLE);
        onCreate(db);

    }
}
