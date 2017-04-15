package tarikalovebird.money.Summary.report_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tarikalovebird.money.Income.income_db.Income_metaData;

/**
 * Created by Preaw-PC on 25/3/2560.
 */
public class Report_table_dbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "report.db";

    public Report_table_dbHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Report_metaData.TABLE  + "("
                + Income_metaData.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Income_metaData.KEY_Name + " TEXT, "
                + Income_metaData.KEY_Amount + " REAL, "
                + Income_metaData.KEY_Period + " TEXT, "
                + Income_metaData.KEY_Type+" INTEGER ) "
                + Income_metaData.KEY_Amount + " REAL, "
                + Income_metaData.KEY_Period + " TEXT, "
                + Income_metaData.KEY_Type+" INTEGER ) ";

        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Income_metaData.TABLE);

        // Create tables again
        onCreate(db);

    }
}
