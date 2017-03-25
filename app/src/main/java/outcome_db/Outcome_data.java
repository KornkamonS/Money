package outcome_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Preaw-PC on 25/3/2560.
 */

public class Outcome_data {
    private Outcome_table_dbHelper dbHelper;

    public Outcome_data(Context context){dbHelper = new Outcome_table_dbHelper(context);}

    public int insert(Outcome_metaData outcome)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Outcome_metaData.KEY_Amount, outcome.amount);
        values.put(Outcome_metaData.KEY_Name,outcome.name);
        values.put(Outcome_metaData.KEY_Period, outcome.period);
        values.put(Outcome_metaData.KEY_Type, outcome.type);
        values.put(Outcome_metaData.KEY_Period, outcome.period);
        // Inserting Row
        long outcome_id = db.insert(Outcome_metaData.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) outcome_id;
    }

    public void delete(int outcome_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Outcome_metaData.TABLE, Outcome_metaData.KEY_ID + "= ?", new String[] { String.valueOf(outcome_id) });
        db.close(); // Closing database connection
    }

    public void update(Outcome_metaData outcome) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Outcome_metaData.KEY_Name, outcome.name);
        values.put(Outcome_metaData.KEY_Amount,outcome.amount);
        values.put(Outcome_metaData.KEY_Period, outcome.period);
        values.put(Outcome_metaData.KEY_Type, outcome.type);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Outcome_metaData.TABLE, values, Outcome_metaData.KEY_ID + "= ?", new String[] { String.valueOf(outcome.outcome_ID) });
        db.close(); // Closing database connection
    }

    public List<String> getOutcomeList() {

        List<String> income = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query
                (Outcome_metaData.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
            /*
            * 0:id
            * 1:name
            * 2:amount
            * 3:period
            * 4:type*/
        while(!cursor.isAfterLast()) {

            income.add(cursor.getLong(0) + "/" +
                    cursor.getString(1) + "/" +
                    cursor.getString(2) + "/" +
                    cursor.getString(3) + "/" +
                    cursor.getString(4));

            cursor.moveToNext();
        }

        db.close();
        return income;
    }

    public Outcome_metaData getOutcomeById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Outcome_metaData.KEY_ID + "," +
                Outcome_metaData.KEY_Name + "," +
                Outcome_metaData.KEY_Amount + "," +
                Outcome_metaData.KEY_Period + "," +
                Outcome_metaData.KEY_Type +
                " FROM " + Outcome_metaData.TABLE
                + " WHERE " +
                Outcome_metaData.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Outcome_metaData outcome = new Outcome_metaData();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                outcome.outcome_ID =cursor.getInt(cursor.getColumnIndex(Outcome_metaData.KEY_ID));
                outcome.name =cursor.getString(cursor.getColumnIndex(Outcome_metaData.KEY_Name));
                outcome.amount  =cursor.getFloat(cursor.getColumnIndex(Outcome_metaData.KEY_Amount));
                outcome.period =cursor.getString(cursor.getColumnIndex(Outcome_metaData.KEY_Period));
                outcome.type =cursor.getInt(cursor.getColumnIndex(Outcome_metaData.KEY_Type));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return outcome;
    }
}
