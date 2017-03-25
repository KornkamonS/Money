package tarikalovebird.money;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import income_db.Income_data;
import income_db.Income_metaData;

public class Add_Income extends AppCompatActivity {

    private ActionBar a ;
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
    private AlertDialog.Builder Error;
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
            Error = new AlertDialog.Builder(this);
            // get the current date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH)+1;
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // display the current date
            updateCurrentDate();

            List< String > spinlist = new ArrayList< String >( );
            spinlist.add ( "Day" );
            spinlist.add ( "Month" );
            spinlist.add ( "Year" );
            ArrayAdapter< String > dataAdapter = new ArrayAdapter < String > ( this, android.R.layout.simple_spinner_item, spinlist );

            typegroup=(RadioGroup) findViewById(R.id.select);
            IncomeName=(EditText) findViewById(R.id.incomeName);
            IncomePrice=(EditText)findViewById(R.id.incomePrice);
            OkBut=(Button)findViewById(R.id.incomeOk);
            CancelBut=(Button)findViewById(R.id.incomeCancel);
            spin = ( Spinner ) this.findViewById ( R.id.incomeSpin );

            aa=new Get_datefromCalender(this);

            spin.setAdapter ( dataAdapter );

            spin.setSelection(0);
            Error.setTitle("Error! ");
            Error.setIcon(android.R.drawable.btn_dialog);
            Error.setPositiveButton("Close", null);

            OkBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean FLAG=true;
                    if(IncomePrice.getText().toString().isEmpty()){

                        IncomePrice.setError("Please input Price");
                        FLAG=false;
                    }
                    if(FLAG){
                        Income_data income_data = new Income_data(getApplicationContext());
                        Income_metaData income = new Income_metaData();

                        income.name=IncomeName.getText().toString();
                        income.amount=(Float.parseFloat(IncomePrice.getText().toString()));
                        income.type=typegroup.getCheckedRadioButtonId();
                        income.period=spin.getSelectedItem().toString();
                        income_data.insert(income);

                        Toast.makeText(getApplicationContext(),"insert income",Toast.LENGTH_SHORT).show();
                        /*String income_name = IncomeName.getText().toString();
                        float income_price = (Float.parseFloat(IncomePrice.getText().toString()));
                        int income_type = typegroup.getCheckedRadioButtonId();
                        String income_period = spin.getSelectedItem().toString();

                        Toast.makeText(getApplicationContext(), String.valueOf(mDay),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), String.valueOf(mMonth),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),  String.valueOf(mYear),Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(),  String.valueOf(income_price), Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(),  income_name, Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),  "Type"+String.valueOf(income_type), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Period"+ income_period, Toast.LENGTH_LONG).show();*/
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
