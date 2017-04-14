package report_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Preaw-PC on 25/3/2560.
 */

public class Report_data {
    private Report_table_dbHelper dbHelper;

    public Report_data(Context context){dbHelper = new Report_table_dbHelper(context);}

    public int insert(Report_metaData report)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Report_metaData.KEY_Amount, report.amount);
        values.put(Report_metaData.KEY_Name,report.name);
        values.put(Report_metaData.KEY_Day, report.day);
        values.put(Report_metaData.KEY_Month, report.month);
        values.put(Report_metaData.KEY_Year, report.year);
        values.put(Report_metaData.KEY_InorOut, report.inorout);
        values.put(Report_metaData.KEY_Type, report.type);

        // Inserting Row
        long report_id = db.insert(Report_metaData.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) report_id;
    }

    public void delete(int report_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Report_metaData.TABLE, Report_metaData.KEY_ID + "= ?", new String[] { String.valueOf(report_id) });
        db.close(); // Closing database connection
    }

    public void update(Report_metaData report) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Report_metaData.KEY_Name, report.name);
        values.put(Report_metaData.KEY_Amount,report.amount);
        values.put(Report_metaData.KEY_Day, report.day);
        values.put(Report_metaData.KEY_Month, report.month);
        values.put(Report_metaData.KEY_Year, report.year);
        values.put(Report_metaData.KEY_InorOut,report.inorout);
        values.put(Report_metaData.KEY_Type, report.type);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Report_metaData.TABLE, values, Report_metaData.KEY_ID + "= ?", new String[] { String.valueOf(report.outcome_ID) });
        db.close(); // Closing database connection
    }

    public List<String> getOutcomeList() {

        List<String> income = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query
                (Report_metaData.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
            /*
            * 0:id
            * 1:name
            * 2:amount
            * 3:day
            * 4:month
            * 5:year
            * 6:inorout
            * 7:type*/
        while(!cursor.isAfterLast()) {

            income.add(cursor.getLong(0) + "/" +
                    cursor.getString(1) + "/" +
                    cursor.getString(2) + "/" +
                    cursor.getString(3) + "/" +
                    cursor.getString(4) + "/" +
                    cursor.getString(5) + "/" +
                    cursor.getString(6) + "/" +
                    cursor.getString(7));

            cursor.moveToNext();
        }

        db.close();
        return income;
    }

    public Report_metaData getOutcomeById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Report_metaData.KEY_ID + "," +
                Report_metaData.KEY_Name + "," +
                Report_metaData.KEY_Amount + "," +
                Report_metaData.KEY_Day + "," +
                Report_metaData.KEY_Month + "," +
                Report_metaData.KEY_Year + "," +
                Report_metaData.KEY_InorOut + "," +
                Report_metaData.KEY_Type +
                " FROM " + Report_metaData.TABLE
                + " WHERE " +
                Report_metaData.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Report_metaData report = new Report_metaData();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                report.outcome_ID =cursor.getInt(cursor.getColumnIndex(Report_metaData.KEY_ID));
                report.name =cursor.getString(cursor.getColumnIndex(Report_metaData.KEY_Name));
                report.amount  =cursor.getFloat(cursor.getColumnIndex(Report_metaData.KEY_Amount));
                report.day =cursor.getInt(cursor.getColumnIndex(Report_metaData.KEY_Day));
                report.month =cursor.getInt(cursor.getColumnIndex(Report_metaData.KEY_Month));
                report.year  =cursor.getInt(cursor.getColumnIndex(Report_metaData.KEY_Year));
                report.inorout =cursor.getInt(cursor.getColumnIndex(Report_metaData.KEY_InorOut));
                report.type =cursor.getInt(cursor.getColumnIndex(Report_metaData.KEY_Type));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return report;
    }
}
