package tarikalovebird.money.Summary.report_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Report_data {
    private Report_table_dbHelper dbHelper;

    private String selectall="SELECT  " +
            Report_metaData.KEY_ID + "," +
            Report_metaData.KEY_Name + "," +
            Report_metaData.KEY_Amount + "," +
            Report_metaData.KEY_Day + "," +
            Report_metaData.KEY_Month + "," +
            Report_metaData.KEY_Year + "," +
            Report_metaData.KEY_InorOut + "," +
            Report_metaData.KEY_Type +
            " FROM " + Report_metaData.TABLE;

    public Report_data(Context context){dbHelper = new Report_table_dbHelper(context);}

    public int insert(Report_metaData report){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Report_metaData.KEY_Name,report.name);
        values.put(Report_metaData.KEY_Amount, report.amount);
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
    public void update(Report_metaData report){

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
        db.update(Report_metaData.TABLE, values, Report_metaData.KEY_ID + "= ?", new String[] { String.valueOf(report.report_id) });
        db.close(); // Closing database connection
    }

    public Report_metaData getReportById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  selectall + " WHERE " +
                Report_metaData.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Report_metaData report = new Report_metaData();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                report.report_id =cursor.getInt(cursor.getColumnIndex(Report_metaData.KEY_ID));
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

    public List<String> getList(Cursor cursor){
        List<String> report = new ArrayList<String>();
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

            report.add(cursor.getLong(0) + "/" +
                    cursor.getString(1) + "/" +
                    cursor.getString(2) + "/" +
                    cursor.getString(3) + "/" +
                    cursor.getString(4) + "/" +
                    cursor.getString(5) + "/" +
                    cursor.getString(6) + "/" +
                    cursor.getString(7));

            cursor.moveToNext();
        }
        return report;

    }
    public List<String> getList_month(Cursor cursor){
        List<String> report = new ArrayList<String>();
        if (cursor != null) {
            cursor.moveToFirst();
        }
        /*  * 0:id
            * 1:day
            * 1:month
            * 2:year
            * 3:amount */

        while(!cursor.isAfterLast()) {

            report.add(cursor.getLong(0) + "/" +
                    cursor.getString(1) + "/" +
                    cursor.getString(2) + "/" +
                    cursor.getString(3) + "/" +
                    cursor.getString(4));
            cursor.moveToNext();
        }
        return report;

    }
    public List<String> getList_year(Cursor cursor){
        List<String> report = new ArrayList<String>();
        if (cursor != null) {
            cursor.moveToFirst();
        }
        /*  * 0:id
            * 1:month
            * 2:year
            * 3:amount */

        while(!cursor.isAfterLast()) {

            report.add(cursor.getLong(0) + "/" +
                    cursor.getString(1) + "/" +
                    cursor.getString(2) + "/" +
                    cursor.getString(3) );
            cursor.moveToNext();
        }
        return report;

    }

    public List<String> getAllIncomeList(){
        String selectQuery =  selectall+ " WHERE " +
                Report_metaData.KEY_InorOut + "=?";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(1)});
        List<String> report=getList(cursor);
        db.close();
        return report;
    }
    public List<String> getAllOutcomeList(){

        String selectQuery =  selectall + " WHERE " +
                Report_metaData.KEY_InorOut + "=?";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Report_metaData.OUT)});
        List<String> report=getList(cursor);
        db.close();
        return report;
    }
    public List<String> getReportList() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query
                (Report_metaData.TABLE, null, null, null, null, null, null);
        List<String> report=getList(cursor);
        db.close();
        return report;
    }


    public List<String> getReportListbyDate(int day,int month,int year){
        String selectQuery =  selectall + " WHERE " +
                Report_metaData.KEY_Day + "=?"+" AND "+
                Report_metaData.KEY_Month + "=?"+" AND "+
                Report_metaData.KEY_Year + "=?";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String a[]=new String[]{ String.valueOf(day),String.valueOf(month),String.valueOf(year)};
        Cursor cursor = db.rawQuery(selectQuery, a);
        List<String> report=getList(cursor);
        db.close();
        return report;
    }
    public List<String> getIncomeListbyDate(int day,int month,int year){
        String selectQuery =  selectall + " WHERE " +
                Report_metaData.KEY_Day + "=?"+" AND "+
                Report_metaData.KEY_Month + "=?"+" AND "+
                Report_metaData.KEY_Year + "=?"+" AND "+
                Report_metaData.KEY_InorOut+"=?";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String a[]=new String[]{ String.valueOf(day),String.valueOf(month),String.valueOf(year),String.valueOf(Report_metaData.IN)};
        Cursor cursor = db.rawQuery(selectQuery, a);
        List<String> report=getList(cursor);
        db.close();
        return report;
    }
    public List<String> getOutcomeListbyDate(int day,int month,int year){
        String selectQuery =  selectall + " WHERE " +
                Report_metaData.KEY_Day + "=?"+" AND "+
                Report_metaData.KEY_Month + "=?"+" AND "+
                Report_metaData.KEY_Year + "=?"+" AND "+
                Report_metaData.KEY_InorOut+"=?";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String a[]=new String[]{ String.valueOf(day),String.valueOf(month),String.valueOf(year),String.valueOf(Report_metaData.OUT)};
        Cursor cursor = db.rawQuery(selectQuery, a);
        List<String> report=getList(cursor);
        db.close();
        return report;
    }


    public List<String> getTotalListbyMonth(int month,int year){

        String selectQuery = "SELECT  " +
                Report_metaData.KEY_ID + "," +
                Report_metaData.KEY_Day + "," +
                Report_metaData.KEY_Month + "," +
                Report_metaData.KEY_Year + "," +
                "SUM( "+ Report_metaData.KEY_Amount + "*" + Report_metaData.KEY_InorOut +")" +
                " FROM " + Report_metaData.TABLE+
                " WHERE " + Report_metaData.KEY_Month + "=?"+ " AND "+
                Report_metaData.KEY_Year + "=?"+
                "GROUP BY "+Report_metaData.KEY_Day;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(month),String.valueOf(year)});
        List<String> report= getList_month(cursor);
        db.close();
        return report;
    }
    public List<String> getIncomeListbyMonth(int month,int year){

        String selectQuery = "SELECT  " +
                Report_metaData.KEY_ID + "," +
                Report_metaData.KEY_Day + "," +
                Report_metaData.KEY_Month + "," +
                Report_metaData.KEY_Year + "," +
                "SUM( "+ Report_metaData.KEY_Amount +")" +
                " FROM " + Report_metaData.TABLE+
                " WHERE " + Report_metaData.KEY_Month + "=?"+ " AND "+
                Report_metaData.KEY_Year + "=?"+ " AND "+
                Report_metaData.KEY_InorOut+"=?"+
                " GROUP BY "+Report_metaData.KEY_Day;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(month),String.valueOf(year),String.valueOf(Report_metaData.IN)});
        List<String> report= getList_month(cursor);
        db.close();
        return report;
    }
    public List<String> getOutcomeListbyMonth(int month,int year){

        String selectQuery = "SELECT  " +
                Report_metaData.KEY_ID + "," +
                Report_metaData.KEY_Day + "," +
                Report_metaData.KEY_Month + "," +
                Report_metaData.KEY_Year + "," +
                "SUM( "+ Report_metaData.KEY_Amount +")" +
                " FROM " + Report_metaData.TABLE+
                " WHERE " + Report_metaData.KEY_Month + "=?"+ " AND "+
                Report_metaData.KEY_Year + "=?"+ " AND "+
                Report_metaData.KEY_InorOut+"=?"+
                " GROUP BY "+Report_metaData.KEY_Day;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,
                new String[]{String.valueOf(month),String.valueOf(year),String.valueOf(Report_metaData.OUT)});
        List<String> report= getList_month(cursor);
        db.close();
        return report;
    }


    public List<String> getTotalListbyYear(int year){

        String selectQuery = "SELECT  " +
                Report_metaData.KEY_ID + "," +
                Report_metaData.KEY_Month + "," +
                Report_metaData.KEY_Year + "," +
                " SUM( "+ Report_metaData.KEY_Amount + "*" + Report_metaData.KEY_InorOut +")" +
                " FROM " + Report_metaData.TABLE+
                " WHERE " + Report_metaData.KEY_Year + "=?"+
                " GROUP BY "+Report_metaData.KEY_Month;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(year)});
        List<String> report= getList_year(cursor);
        db.close();
        return report;
    }
    public List<String> getIncomeListbyYear(int year){

        String selectQuery = "SELECT  " +
                Report_metaData.KEY_ID + "," +
                Report_metaData.KEY_Month + "," +
                Report_metaData.KEY_Year + "," +
                " SUM( "+ Report_metaData.KEY_Amount +")" +
                " FROM " + Report_metaData.TABLE+
                " WHERE " + Report_metaData.KEY_Year + "=?"+ " AND "+
                Report_metaData.KEY_InorOut+"=?"+
                " GROUP BY "+Report_metaData.KEY_Month;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(year),String.valueOf(Report_metaData.IN)});
        List<String> report= getList_year(cursor);
        db.close();
        return report;
    }
    public List<String> getOutcomeListbyYear(int year){

        String selectQuery = "SELECT  " +
                Report_metaData.KEY_ID + "," +
                Report_metaData.KEY_Month + "," +
                Report_metaData.KEY_Year + "," +
                " SUM( "+ Report_metaData.KEY_Amount +")" +
                " FROM " + Report_metaData.TABLE+
                " WHERE " + Report_metaData.KEY_Year + "=?"+ " AND "+
                Report_metaData.KEY_InorOut+"=?"+
                " GROUP BY "+Report_metaData.KEY_Month;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(year),String.valueOf(Report_metaData.OUT)});
        List<String> report= getList_year(cursor);
        db.close();
        return report;
    }

    public float getTotalmoney()
    {
        String selectQuery = "SELECT  " +
                " SUM( "+ Report_metaData.KEY_Amount + " * " + Report_metaData.KEY_InorOut +" )" +
                " FROM " + Report_metaData.TABLE;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor == null) {
            return  0;
        }
        else {cursor.moveToFirst();
            String Shave=cursor.getString(0) ;
            if(Shave!=null)     return Float.parseFloat(Shave);
            else return 0;
        }
    }
}
