package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
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

import outcome_db.Outcome_data;
import outcome_db.Outcome_metaData;

public class Outcome_edit_item extends AppCompatActivity {

    private int _Outcome_id=0;
    private EditText OutcomeName;
    private EditText OutcomePrice;
    private Button OkBut;
    private Button DeleteBut;
    private Button CancelBut;
    private RadioGroup typegroup;
    private Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List< String > spinlist = new ArrayList< String >( );
        spinlist.add ( "Month" );
        spinlist.add ( "Year" );
        ArrayAdapter< String > dataAdapter = new ArrayAdapter < String > ( this, android.R.layout.simple_spinner_item, spinlist );

        DeleteBut=(Button) findViewById(R.id.deleteoutcome);
        typegroup=(RadioGroup) findViewById(R.id.editTypeExp);
        OutcomeName=(EditText) findViewById(R.id.editexpenseName);
        OutcomePrice=(EditText)findViewById(R.id.editexpensePrice);
        OkBut=(Button)findViewById(R.id.editoutcomeOk);
        CancelBut=(Button)findViewById(R.id.editoutcomeCancel);
        spin = ( Spinner ) this.findViewById ( R.id.editexpenseSpin );
        spin.setAdapter ( dataAdapter );

        _Outcome_id=1;

        Intent intent = getIntent();
        _Outcome_id = intent.getIntExtra("outcome_Id",0);

        Outcome_data outcome = new Outcome_data(this);
        Outcome_metaData outcomeMetaData= new Outcome_metaData();
        outcomeMetaData=outcome.getOutcomeById(_Outcome_id);

        OutcomeName.setText(outcomeMetaData.name);
        OutcomePrice.setText(String.valueOf(outcomeMetaData.amount));

        String myString = outcomeMetaData.period;
        ArrayAdapter myAdap = (ArrayAdapter) spin.getAdapter();
        int spinnerPosition = myAdap.getPosition(myString);
        spin.setSelection(spinnerPosition);

        typegroup.check(outcomeMetaData.type);


        OkBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean FLAG=true;
                if(OutcomePrice.getText().toString().isEmpty()){

                    OutcomePrice.setError("Please input Price");
                    FLAG=false;
                }
                if(FLAG){
                    Outcome_data outcome_data = new Outcome_data(getApplicationContext());
                    Outcome_metaData outcome = new Outcome_metaData();

                    outcome.outcome_ID=_Outcome_id;
                    outcome.name = OutcomeName.getText().toString();
                    outcome.amount = (Float.parseFloat(OutcomePrice.getText().toString()));
                    outcome.type = typegroup.getCheckedRadioButtonId();
                    outcome.period=spin.getSelectedItem().toString();
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
                Outcome_data repo = new Outcome_data(getApplicationContext());
                repo.delete(_Outcome_id);
                Toast.makeText(getApplicationContext(), "Outcome Record Deleted", Toast.LENGTH_SHORT);

                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}
