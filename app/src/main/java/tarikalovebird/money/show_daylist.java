package tarikalovebird.money;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tarikalovebird.money.Outcome.editperoid.Outcome_edit_item;
import tarikalovebird.money.Outcome.outcome_db.Outcome_adepter;
import tarikalovebird.money.Outcome.outcome_db.Outcome_data;
import tarikalovebird.money.Outcome.outcome_db.Outcome_detail;
import tarikalovebird.money.R;
import tarikalovebird.money.Summary.report_db.Report_adepter;
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_detail;
import tarikalovebird.money.Summary.report_db.Report_metaData;

/**
 * Created by tunas on 16-Apr-17.
 */

public class show_daylist extends Activity {
    TextView outcome_id;
    int _list_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_schedule);
         Intent intent = getIntent();
        _list_id = intent.getIntExtra("list_Id",-1);
        print();
    }

    void print()
    {
        Report_data repo = new Report_data(this);
        Report_metaData item =  repo.getReportById(_list_id);

        List<String> item_list= repo.getReportListbyDate(item.day,item.month,item.year);
        ArrayList<Report_detail> arrayOfOutcome = new ArrayList<Report_detail>();
        Report_adepter adapter = new Report_adepter(this, arrayOfOutcome);
        ListView lv = (ListView) findViewById(R.id.list);
        if(item_list.size()!=0) {
            for(int i=0;i<item_list.size();i++)
            {
                Report_detail newOutcome = new Report_detail(item_list.get(i));
                adapter.add(newOutcome);

            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    outcome_id = (TextView) view.findViewById(R.id.list_Id);
                    String outcomeId = outcome_id.getText().toString();
                    /*Intent objIndent = new Intent(getApplicationContext(), Outcome_edit_item.class);
                    objIndent.putExtra("outcome_Id", Integer.parseInt(outcomeId));
                    startActivityForResult(objIndent,1);*/
                }
            });



        }
        lv.setAdapter(adapter);
    }
}
