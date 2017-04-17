package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Tips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void onClickSave1(View view){
        Button btn_next = (Button)findViewById(R.id.payfood);
        Intent intent = new Intent(Tips.this,Save_pay_food.class);
        startActivity(intent);
    }
    public void onClickSave2(View view){
        Button btn_next = (Button)findViewById(R.id.payoil);
        Intent intent = new Intent(Tips.this,Save_pay_petrol.class);
        startActivity(intent);
    }
    public void onClickSave3(View view){
        Button btn_next = (Button)findViewById(R.id.payetc);
        Intent intent = new Intent(Tips.this,Save_other_pay.class);
        startActivity(intent);
    }
}
