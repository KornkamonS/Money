package tarikalovebird.money.Income.editperiod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tarikalovebird.money.Income.income_db.Income_adepter;
import tarikalovebird.money.Income.income_db.Income_data;
import tarikalovebird.money.Income.income_db.Income_detial;
import tarikalovebird.money.R;

public class EditIncomeSchedule extends Activity {

    TextView income_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_income_schedule);
         print();
        }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        print();

    }
    void print()
    {
        Income_data repo = new Income_data(this);
        List<String> incomeList =  repo.getIncomeList();

        ArrayList<Income_detial> arrayOfIncome = new ArrayList<Income_detial>();
        Income_adepter adapter = new Income_adepter(this, arrayOfIncome);
        ListView lv = (ListView) findViewById(R.id.incomelist);

        if(incomeList.size()!=0) {
            for(int i=0;i<incomeList.size();i++)
            {
                Income_detial newIncome = new Income_detial(incomeList.get(i));
                adapter.add(newIncome);

            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    income_id = (TextView) view.findViewById(R.id.income_Id);
                    String incomeId = income_id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),Income_edit_item.class);
                    objIndent.putExtra("income_Id", Integer.parseInt(incomeId));
                    startActivityForResult(objIndent,1);

                }
            });

            lv.setAdapter(adapter);
        }
        lv.setAdapter(adapter);
        /*else{
            print();
        }*/

    }
}
