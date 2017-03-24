package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Add_Target extends AppCompatActivity {

    private EditText targetPrice;
    private EditText targetName;
    private EditText targetDay;
    private Button okbut;
    private Button cancelbut;
    private RadioGroup typegroup;
    private RadioButton typeselect;

     public Target t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        typegroup=(RadioGroup) findViewById(R.id.TypeTargetRaioGroup);
        targetName=(EditText) findViewById(R.id.ADDTargetName);
        targetPrice=(EditText)findViewById(R.id.ADDTargetPrice);
        targetDay=(EditText)findViewById(R.id.ADDDayTargey) ;
        okbut=(Button)findViewById(R.id.OkbuttonTarget);
        cancelbut=(Button)findViewById(R.id.CancelbuttonTarget);

        t=new Target(this);

        final AlertDialog.Builder Error = new AlertDialog.Builder(this);
        Error.setTitle("Error! ");
        Error.setIcon(android.R.drawable.btn_dialog);
        Error.setPositiveButton("Close", null);

        okbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean FLAG=true;
                if(targetDay.getText().toString().isEmpty()){

                    targetDay.setError("Please input Day");
                    FLAG=false;
                }
                if(targetPrice.getText().toString().isEmpty()){

                    targetPrice.setError("Please input Price");
                    FLAG=false;
                }
                if(targetDay.getText().toString().contains(".")){
                    targetDay.setError("Day must be an Integer");
                    FLAG=false;
                }
                if(FLAG){
                    FLAG=t.setNameTarget(targetName.getText().toString());
                    FLAG=t.setTargetDay(Integer.parseInt(targetDay.getText().toString()));
                    FLAG=t.setTargetPrice(Float.parseFloat(targetDay.getText().toString()));
                    FLAG=t.setTargetType(typegroup.getCheckedRadioButtonId());
                    if(!FLAG){
                        Error.setMessage("Save Target Error");
                        Error.show();
                    }
                    else{
                        Intent returnIntent = new Intent();
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
                }
            }
        });
        cancelbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });

    }

}
