package tarikalovebird.money.Summary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import tarikalovebird.money.Get_datefromCalender;
import tarikalovebird.money.R;
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_metaData;
import tarikalovebird.money.helpcode;
import tarikalovebird.money.myCalendarView;

public class edit_in_type extends AppCompatActivity {
    private int _Income_id=0;
    private EditText IncomeName;
    private EditText IncomePrice;
    private Button OkBut;
    private Button DeleteBut;
    private Button CancelBut;
    private RadioGroup typegroup;
    private AlertDialog.Builder builder;
    private TextView dateBut;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int CALENDAR_VIEW_ID = 1;
    private Get_datefromCalender aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_edit);

        builder=  new AlertDialog.Builder(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DeleteBut=(Button) findViewById(R.id.deleteincome);
        typegroup=(RadioGroup) findViewById(R.id.editselect);
        IncomeName=(EditText) findViewById(R.id.editincomeName);
        IncomePrice=(EditText)findViewById(R.id.editincomePrice);
        OkBut=(Button)findViewById(R.id.editincomeOk);
        CancelBut=(Button)findViewById(R.id.editincomeCancel);

        dateBut = (TextView) findViewById(R.id.seldate);

        dateBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myCalendarView.class);
                startActivityForResult(intent,CALENDAR_VIEW_ID);
            }
        });


        _Income_id=-1;

        Intent intent = getIntent();
        _Income_id = intent.getIntExtra("income_Id",-1);

        Report_data income = new Report_data(this);
        Report_metaData incomeMetaData=income.getReportById(_Income_id);

        IncomeName.setText(incomeMetaData.name);
        IncomePrice.setText(String.valueOf(incomeMetaData.amount));

        mYear =incomeMetaData.year;
        mMonth =incomeMetaData.month ;
        mDay = incomeMetaData.day;
        aa=new Get_datefromCalender(this);
        updateCurrentDate();

        typegroup.check(incomeMetaData.type);

        OkBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean FLAG=true;
                if(IncomePrice.getText().toString().isEmpty()){
                    IncomePrice.setError("Please input Price");
                    FLAG=false;
                }
                if(FLAG){
                    Report_data income_data = new Report_data(getApplicationContext());
                    Report_metaData income = new Report_metaData();

                    income.setValue(_Income_id,IncomeName.getText().toString(),
                            Float.parseFloat(IncomePrice.getText().toString())
                            ,mDay,mMonth,mYear,Report_metaData.IN,typegroup.getCheckedRadioButtonId());

                    income_data.update(income);
                    Toast.makeText(getApplicationContext(),"update income",Toast.LENGTH_SHORT).show();

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
                                Report_data repo = new  Report_data(getApplicationContext());
                                repo.delete(_Income_id);
                                Toast.makeText(getApplicationContext(), " Record Deleted", Toast.LENGTH_SHORT);
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
    private void updateCurrentDate() {
        dateBut.setText(helpcode.formatDayMonthYear(mDay,mMonth-1,mYear));
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
