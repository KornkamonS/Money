package tarikalovebird.money.Summary;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tarikalovebird.money.Get_datefromCalender;
import tarikalovebird.money.Outcome.outcome_db.Outcome_data;
import tarikalovebird.money.Outcome.outcome_db.Outcome_metaData;
import tarikalovebird.money.R;
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_metaData;
import tarikalovebird.money.helpcode;
import tarikalovebird.money.myCalendarView;

public class edit_out_type  extends AppCompatActivity{

    private ActionBar a ;
    private EditText ExpenseName;
    private EditText ExpensePrice;
    private TextView dateBut;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int CALENDAR_VIEW_ID = 1;
    private Get_datefromCalender aa;
    private int _Outcome_id=0;
    private EditText OutcomeName;
    private EditText OutcomePrice;
    private Button OkBut;
    private Button DeleteBut;
    private Button CancelBut;
    private RadioGroup typegroup;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.out_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        builder=  new AlertDialog.Builder(this);
        DeleteBut=(Button) findViewById(R.id.deleteoutcome);
        typegroup=(RadioGroup) findViewById(R.id.editTypeExp);
        OutcomeName=(EditText) findViewById(R.id.editexpenseName);
        OutcomePrice=(EditText)findViewById(R.id.editexpensePrice);
        OkBut=(Button)findViewById(R.id.editoutcomeOk);
        CancelBut=(Button)findViewById(R.id.editoutcomeCancel);

        dateBut = (TextView) findViewById(R.id.seldate);
        dateBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myCalendarView.class);
                startActivityForResult(intent,CALENDAR_VIEW_ID);
            }
        });



        _Outcome_id=-1;
        Intent intent = getIntent();
        _Outcome_id = intent.getIntExtra("income_Id",-1);

        Report_data outcome = new Report_data(this);
        Report_metaData outcomeMetaData=outcome.getReportById(_Outcome_id);

        OutcomeName.setText(outcomeMetaData.name);
        OutcomePrice.setText(String.valueOf(outcomeMetaData.amount));
        typegroup.check(outcomeMetaData.type);

        mYear = outcomeMetaData.year;
        mMonth = outcomeMetaData.month;
        mDay = outcomeMetaData.day;
        updateCurrentDate();
        aa=new Get_datefromCalender(this);
        OkBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean FLAG=true;
                if(OutcomePrice.getText().toString().isEmpty()){

                    OutcomePrice.setError("Please input Price");
                    FLAG=false;
                }
                if(FLAG){
                    Report_data outcome_data = new Report_data(getApplicationContext());
                    Report_metaData outcome = new Report_metaData();
                    outcome.setValue(_Outcome_id,OutcomeName.getText().toString(),
                            Float.parseFloat(OutcomePrice.getText().toString())
                            ,mDay,mMonth,mYear,Report_metaData.OUT,typegroup.getCheckedRadioButtonId());
                    outcome_data.update(outcome);
                    Toast.makeText(getApplicationContext(),"update outcome",Toast.LENGTH_SHORT).show();

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

        DeleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("Are you sure want to DELETE")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Report_data repo = new Report_data(getApplicationContext());
                                repo.delete(_Outcome_id);
                                Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_SHORT).show();

                                Intent returnIntent = new Intent();
                                setResult(RESULT_OK, returnIntent);
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
    // updates the date we display in the TextView
    private void updateCurrentDate() {
        helpcode a=new helpcode();
        dateBut.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append(" ")
                        .append(a.getMonthtext(mMonth)).append(" ")
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
