package tarikalovebird.money;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tarikalovebird.money.Income.income_db.Income_data;
import tarikalovebird.money.Income.income_db.Income_metaData;
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_metaData;

public class Add_Income extends AppCompatActivity {


    private EditText IncomeName;
    private EditText IncomePrice;
    private Button OkBut;
    private Button CancelBut;
    private RadioGroup typegroup;
    private Spinner spin;
    private Button dateBut;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Switch SwitchIn;

    static final int CALENDAR_VIEW_ID = 1;
    private Get_datefromCalender aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__income);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            dateBut = (Button) findViewById(R.id.seldate);

            // add a click listener to the button
            dateBut.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), myCalendarView.class);
                    startActivityForResult(intent,CALENDAR_VIEW_ID);
                }
            });

            // get the current date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH)+1;
            mDay = c.get(Calendar.DAY_OF_MONTH);
            aa=new Get_datefromCalender(this);
            // display the current date
            updateCurrentDate();

            List< String > spinlist = new ArrayList< String >( );
            spinlist.add ( "Month" );
            spinlist.add ( "Year" );
            ArrayAdapter< String > dataAdapter = new ArrayAdapter < String > ( this, android.R.layout.simple_spinner_item, spinlist );

            typegroup=(RadioGroup) findViewById(R.id.select);
            IncomeName=(EditText) findViewById(R.id.incomeName);
            IncomePrice=(EditText)findViewById(R.id.incomePrice);
            OkBut=(Button)findViewById(R.id.incomeOk);
            CancelBut=(Button)findViewById(R.id.incomeCancel);
            spin = ( Spinner ) this.findViewById ( R.id.incomeSpin );
            spin.setAdapter ( dataAdapter );
            SwitchIn = (Switch) findViewById(R.id.switchIn);

            spin.setSelection(0);


            OkBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean FLAG=true;
                    if(IncomePrice.getText().toString().isEmpty()||Float.parseFloat(IncomePrice.getText().toString())==0){

                        IncomePrice.setError("Please input Price");
                        FLAG=false;
                    }
                    if(FLAG){
                        String Typetext="Other";
                        int typeid=typegroup.getCheckedRadioButtonId();
                        switch (typeid)
                        {
                            case R.id.gift: Typetext="Gift"; break;
                            case R.id.salary: Typetext="Salary"; break;
                            case R.id.sale:  Typetext="Bussiness"; break;
                            case R.id.add: Typetext="Other"; break;
                        }


                        Income_data income_data = new Income_data(getApplicationContext());
                        Income_metaData income = new Income_metaData();

                        String spintext=spin.getSelectedItem().toString();


                        if(spintext!="Day") {

                            if(!IncomeName.getText().toString().isEmpty()){
                                income.name = IncomeName.getText().toString();
                            }
                            else {income.name=Typetext;  }

                            income.amount = (Float.parseFloat(IncomePrice.getText().toString()));
                            income.type = typeid;
                            income.period=spintext;
                            income_data.insert(income);

                            Toast.makeText(getApplicationContext(),"insert income ",Toast.LENGTH_SHORT).show();
                        }


                        Report_data report_data=new Report_data(getApplicationContext());
                        Report_metaData report=new Report_metaData();

                        if(!IncomeName.getText().toString().isEmpty()){
                            report.name=IncomeName.getText().toString();
                        }
                        else { report.name=Typetext;}
                        report.amount = (Float.parseFloat(IncomePrice.getText().toString()));
                        report.type = typeid;
                        report.inorout=Report_metaData.IN;
                        report.day=mDay;
                        report.month=mMonth;
                        report.year=mYear;

                        report_data.insert(report);

                        Toast.makeText(getApplicationContext(),"insert report ",Toast.LENGTH_SHORT).show();

                        Intent returnIntent = new Intent();
                        setResult(RESULT_OK, returnIntent);
                        finish();

                    }
                }
            });
            CancelBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent returnIntent = new Intent();
                    setResult(RESULT_CANCELED, returnIntent);
                    finish();
                }
            });
        }
        // updates the date we display in the TextView
    private void updateCurrentDate() {
        dateBut.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append("-")
                        .append(mMonth).append("-")
                        .append(mYear).append(" "));
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
            case CALENDAR_VIEW_ID:
                if (resultCode == RESULT_OK) {

                    mDay=aa.getDay();
                    mMonth=aa.getMonth();
                    mYear=aa.getYear();

                    Bundle bundle = data.getExtras();
                    dateBut.setText(bundle.getString("dateSelected"));
                    break;
                }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
