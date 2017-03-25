package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import income_db.Income_data;
import income_db.Income_detial;
import income_db.Income_metaData;

public class Income_edit_item extends AppCompatActivity {

    private int _Income_id=0;
    private EditText IncomeName;
    private EditText IncomePrice;
    private Button OkBut;
    private Button DeleteBut;
    private Button CancelBut;
    private RadioGroup typegroup;
    private Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List< String > spinlist = new ArrayList< String >( );
        spinlist.add ( "Month" );
        spinlist.add ( "Year" );
        ArrayAdapter< String > dataAdapter = new ArrayAdapter < String > ( this, android.R.layout.simple_spinner_item, spinlist );

        DeleteBut=(Button) findViewById(R.id.deleteincome);
        typegroup=(RadioGroup) findViewById(R.id.editselect);
        IncomeName=(EditText) findViewById(R.id.editincomeName);
        IncomePrice=(EditText)findViewById(R.id.editincomePrice);
        OkBut=(Button)findViewById(R.id.editincomeOk);
        CancelBut=(Button)findViewById(R.id.editincomeCancel);
        spin = ( Spinner ) this.findViewById ( R.id.editincomeSpin );
        spin.setAdapter ( dataAdapter );

        _Income_id=1;

        Intent intent = getIntent();
        _Income_id = intent.getIntExtra("income_Id",0);

        Income_data income = new Income_data(this);
        Income_metaData incomeMetaData=new Income_metaData();
        incomeMetaData=income.getIncomeById(_Income_id);

        IncomeName.setText(incomeMetaData.name);
        IncomePrice.setText(String.valueOf(incomeMetaData.amount));

        typegroup.check(incomeMetaData.type);
        if(incomeMetaData.period=="Month")
        {
            spin.setSelection(1);
        }else spin.setSelection(0);

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

                        income.income_ID=_Income_id;
                        income.name = IncomeName.getText().toString();
                        income.amount = (Float.parseFloat(IncomePrice.getText().toString()));
                        income.type = typegroup.getCheckedRadioButtonId();
                        income.period=spin.getSelectedItem().toString();
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
                Income_data repo = new Income_data(getApplicationContext());
                repo.delete(_Income_id);
                Toast.makeText(getApplicationContext(), "Income Record Deleted", Toast.LENGTH_SHORT);
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}
