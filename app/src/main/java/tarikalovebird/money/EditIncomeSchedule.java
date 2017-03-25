package tarikalovebird.money;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import income_db.Income_adepter;
import income_db.Income_data;
import income_db.Income_detial;

public class EditIncomeSchedule extends Activity {

    TextView income_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_income_schedule);

        Income_data repo = new Income_data(this);

        List<String> incomeList =  repo.getIncomeList();
        //String a=studentList.get(1).get("id");
        //Toast.makeText(this,a,Toast.LENGTH_SHORT).show();

        ArrayList<Income_detial> arrayOfIncome = new ArrayList<Income_detial>();
        Income_adepter adapter = new Income_adepter(this, arrayOfIncome);

        if(incomeList.size()!=0) {
            for(int i=0;i<incomeList.size();i++)
            {
                Income_detial newIncome = new Income_detial(incomeList.get(i));
                adapter.add(newIncome);

            }
            ListView lv = (ListView) findViewById(R.id.incomelist);
             lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                  /*  income_id = (TextView) view.findViewById(R.id.income_Id);
                    String incomeId = income_id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),Income_Detial.class);
                    objIndent.putExtra("income_Id", Integer.parseInt( incomeId));
                    startActivity(objIndent);*/
                }
            });

            lv.setAdapter(adapter);


        }
    }
}
