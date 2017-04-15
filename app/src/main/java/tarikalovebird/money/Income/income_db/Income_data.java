package tarikalovebird.money.Income.income_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TunasanG on 25/3/2560.
 */

public class Income_data {

    private Income_table_dbHelper dbHelper;

    public Income_data(Context context){dbHelper = new Income_table_dbHelper(context);}

    public int insert(Income_metaData income)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Income_metaData.KEY_Amount, income.amount);
        values.put(Income_metaData.KEY_Name,income.name);
        values.put(Income_metaData.KEY_Period, income.period);
        values.put(Income_metaData.KEY_Type, income.type);
        values.put(Income_metaData.KEY_Period, income.period);
        // Inserting Row
        long income_id = db.insert(Income_metaData.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) income_id;
    }

    public void delete(int income_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Income_metaData.TABLE, Income_metaData.KEY_ID + "= ?", new String[] { String.valueOf(income_id) });
        db.close(); // Closing database connection
    }

    public void update(Income_metaData income) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Income_metaData.KEY_Name, income.name);
        values.put(Income_metaData.KEY_Amount,income.amount);
        values.put(Income_metaData.KEY_Period, income.period);
        values.put(Income_metaData.KEY_Type, income.type);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Income_metaData.TABLE, values, Income_metaData.KEY_ID + "= ?", new String[] { String.valueOf(income.income_ID) });
        db.close(); // Closing database connection
    }

    public List<String> getIncomeList() {

        List<String> income = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query
                (Income_metaData.TABLE, null, null, null, null, null, null);

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

    public Income_metaData getIncomeById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Income_metaData.KEY_ID + "," +
                Income_metaData.KEY_Name + "," +
                Income_metaData.KEY_Amount + "," +
                Income_metaData.KEY_Period + "," +
                Income_metaData.KEY_Type +
                " FROM " + Income_metaData.TABLE
                + " WHERE " +
                Income_metaData.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Income_metaData income = new Income_metaData();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                income.income_ID =cursor.getInt(cursor.getColumnIndex(Income_metaData.KEY_ID));
                income.name =cursor.getString(cursor.getColumnIndex(Income_metaData.KEY_Name));
                income.amount  =cursor.getFloat(cursor.getColumnIndex(Income_metaData.KEY_Amount));
                income.period =cursor.getString(cursor.getColumnIndex(Income_metaData.KEY_Period));
                income.type =cursor.getInt(cursor.getColumnIndex(Income_metaData.KEY_Type));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return income;
    }

}