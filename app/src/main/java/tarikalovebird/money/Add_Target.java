package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Add_Target extends AppCompatActivity {

    private EditText targetPrice;
    private EditText targetName;
    private EditText targetDay;
    private Button okbut;
    private Button cancelbut;
    private RadioGroup typegroup;
    private RadioButton typeselect;
    private AlertDialog.Builder Error ;

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

        Error = new AlertDialog.Builder(this);
        Error.setTitle("Error! ");
        Error.setIcon(android.R.drawable.btn_dialog);
        Error.setPositiveButton("Close", null);

        okbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean FLAG=true;
                if(targetDay.getText().toString().isEmpty()||Integer.parseInt(targetDay.getText().toString())<=0){

                    targetDay.setError("Please input Day");
                    FLAG=false;
                }
                if(targetPrice.getText().toString().isEmpty()||Integer.parseInt(targetPrice.getText().toString())<=0){

                    targetPrice.setError("Please input Price");
                    FLAG=false;
                }
                if(targetDay.getText().toString().contains(".")){
                    targetDay.setError("Day must be an Integer");
                    FLAG=false;
                }
                if(FLAG){
                    Notierror(t.setNameTarget(targetName.getText().toString()));
                    Notierror(t.setTargetCountDownDay(Integer.parseInt(targetDay.getText().toString())));
                    Notierror(t.setTargetPrice(Float.parseFloat(targetPrice.getText().toString())));
                    Notierror(t.setTargetType(typegroup.getCheckedRadioButtonId()));

                    final Calendar c = Calendar.getInstance();

                    Notierror(t.setTargetYear(c.get(Calendar.YEAR)));
                    Notierror(t.setTargetMonth(c.get(Calendar.MONTH)+1));
                    Notierror(t.setTargetDay(c.get(Calendar.DAY_OF_MONTH)));

                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
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
    private void Notierror(boolean a)
    {
        if(!a)
        {
            Toast.makeText(getApplicationContext(),"Save Target Error",Toast.LENGTH_SHORT).show();
            onRestart();
        }
    }
}
