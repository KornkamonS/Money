package tarikalovebird.money;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import outcome_db.Outcome_adepter;
import outcome_db.Outcome_data;
import outcome_db.Outcome_detail;

public class EditOutcomeSchedule extends Activity {

    TextView outcome_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_outcome_schedule);

        Outcome_data repo = new Outcome_data(this);

        List<String> outcomeList =  repo.getOutcomeList();
        //String a=studentList.get(1).get("id");
        //Toast.makeText(this,a,Toast.LENGTH_SHORT).show();

        ArrayList<Outcome_detail> arrayOfOutcome = new ArrayList<Outcome_detail>();
        Outcome_adepter adapter = new Outcome_adepter(this, arrayOfOutcome);

        if(outcomeList.size()!=0) {
            for(int i=0;i<outcomeList.size();i++)
            {
                Outcome_detail newOutcome = new Outcome_detail(outcomeList.get(i));
                adapter.add(newOutcome);

            }
            ListView lv = (ListView) findViewById(R.id.outcomelist);
             lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();

                }
            });

            lv.setAdapter(adapter);


        }
    }
}
