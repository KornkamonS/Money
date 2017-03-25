package tarikalovebird.money;

/**
 * Created by ASUS-GL552 on 23/03/2560.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
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

import outcome_db.Outcome_data;
import outcome_db.Outcome_metaData;

public class Add_Outcome extends AppCompatActivity{

    private ActionBar a ;
    private EditText ExpenseName;
    private EditText ExpensePrice;
    private Button OkBut;
    private Button CancelBut;
    private RadioGroup typegroup;
    private Spinner spin;
    private Button dateBut;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int CALENDAR_VIEW_ID = 1;
    private AlertDialog.Builder Error;
    private Get_datefromCalender aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__outcome);
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

        typegroup=(RadioGroup) findViewById(R.id.TypeExp);
        ExpenseName=(EditText) findViewById(R.id.expenseName);
        ExpensePrice=(EditText)findViewById(R.id.expensePrice);
        OkBut=(Button)findViewById(R.id.expenseOk);
        CancelBut=(Button)findViewById(R.id.expenseCancel);
        spin = ( Spinner ) this.findViewById ( R.id.expenseSpin );

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
                if(ExpensePrice.getText().toString().isEmpty()){

                    ExpensePrice.setError("Please input amount of money");
                    FLAG=false;
                }
                if(FLAG){

                    Outcome_data outcome_data = new Outcome_data(getApplicationContext());
                    Outcome_metaData outcome = new Outcome_metaData();
                    String spintext=spin.getSelectedItem().toString();

                    String Typetext="Other";
                    int typeid=typegroup.getCheckedRadioButtonId();
                    switch (typeid)
                    {
                        case R.id.gift2: Typetext="Gift"; break;
                        case R.id.oil: Typetext="Petrol"; break;
                        case R.id.home:  Typetext="Rents"; break;
                        case R.id.elect: Typetext="Bill"; break;
                        case R.id.shop:  Typetext="Shopping"; break;
                        case R.id.entertain: Typetext="Entertain"; break;
                        case R.id.pen:  Typetext="Stationery"; break;
                        case R.id.study: Typetext="Learning"; break;
                        case R.id.add2: Typetext="Other"; break;
                    }

                    if(spintext!="Day") {

                        if(!ExpenseName.getText().toString().isEmpty())
                            outcome.name = ExpenseName.getText().toString();
                        else outcome.name=Typetext;

                        outcome.amount = (Float.parseFloat(ExpensePrice.getText().toString()));
                        outcome.type = typeid;
                        outcome.period=spintext;
                        outcome_data.insert(outcome);
                        Toast.makeText(getApplicationContext(),"insert Expense",Toast.LENGTH_SHORT).show();
                    }

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
