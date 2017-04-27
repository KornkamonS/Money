package tarikalovebird.money;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import tarikalovebird.money.Picture.ImagePicker;
import tarikalovebird.money.Picture.Mypic;

import static tarikalovebird.money.Picture.Mypic.bitmap;

public class Add_Target extends AppCompatActivity {

    private EditText targetPrice;
    private EditText targetName;
    private EditText targetDay;
    private ImageView picselect;
    private Button okbut;
    private Button cancelbut;
    private RadioGroup typegroup;
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
        picselect=(ImageView) findViewById(R.id.imageView4);
        cancelbut=(Button)findViewById(R.id.CancelbuttonTarget);

        t=new Target(this);

        Error = new AlertDialog.Builder(this);
        Error.setTitle("Error! ");
        Error.setIcon(android.R.drawable.btn_dialog);
        Error.setPositiveButton("Close", null);

        typegroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (typegroup.getCheckedRadioButtonId()) {
                    case R.id.TypeLearning:
                        picselect.setImageResource(R.drawable.learning1);
                        break;
                    case R.id.TypeGift:
                        picselect.setImageResource(R.drawable.gift1);
                        break;
                    case R.id.TypeToy:
                        picselect.setImageResource(R.drawable.toy1);
                        break;
                    case R.id.TypeMusic:
                        picselect.setImageResource(R.drawable.music2);
                        break;
                    case R.id.TypeTechno:
                        picselect.setImageResource(R.drawable.techno1);
                        break;
                    case R.id.AddTypeTarget:
                        picselect.setImageBitmap(bitmap);
                        break;
                    default:
                        picselect.setImageResource(R.drawable.main);
                }
            }
        });

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

                    Notierror(t.setDate(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(Calendar.YEAR)));
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
    public void onPickImage(View view) {
        ImagePicker.pickImage(this, "Select your image:");
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Bitmap mybitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        if (mybitmap != null) {

            Mypic.bitmap = mybitmap;
            typegroup.check(R.id.AddTypeTarget);
        }
        InputStream is = ImagePicker.getInputStreamFromResult(this, requestCode, resultCode, data);
        if (is != null) {

            try {
                is.close();
            } catch (IOException ex) {
            }
        } else {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
