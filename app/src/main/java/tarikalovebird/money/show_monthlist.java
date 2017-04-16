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

import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_detail_month;
import tarikalovebird.money.Summary.report_db.Report_metaData;
import tarikalovebird.money.Summary.report_db.total_month_adepter;
import tarikalovebird.money.Summary.report_db.total_outmonth_adepter;

/**
 * Created by tunas on 16-Apr-17.
 */

public class show_monthlist extends Activity {

    TextView outcome_id;
    int _list_id;
    int _case;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_schedule);
        Intent intent = getIntent();
        _list_id = intent.getIntExtra("list_Id",-1);
        _case = intent.getIntExtra("_case",-1);
        print_all(_case);
    }
    private void print_list(List<String> item_list)
    {
        if(_case==0||_case==1)
        {
            ArrayList<Report_detail_month> arrayOfOutcome = new ArrayList<Report_detail_month>();
            total_month_adepter adapter = new total_month_adepter(this, arrayOfOutcome);
            ListView lv = (ListView) findViewById(R.id.list);
            if(item_list.size()!=0) {
                for(int i=0;i<item_list.size();i++){
                    Report_detail_month newOutcome = new Report_detail_month(item_list.get(i));
                    adapter.add(newOutcome);
                }

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        outcome_id = (TextView) view.findViewById(R.id.list_Id);
                        String outcomeId = outcome_id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),show_daylist.class);
                        objIndent.putExtra("list_Id", Integer.parseInt(outcomeId));
                        objIndent.putExtra("_case",_case);
                        startActivity(objIndent);
                    }
                });



            }
            lv.setAdapter(adapter);
        }
        else
        {
            ArrayList<Report_detail_month> arrayOfOutcome = new ArrayList<Report_detail_month>();
            total_outmonth_adepter adapter = new total_outmonth_adepter(this, arrayOfOutcome);
            ListView lv = (ListView) findViewById(R.id.list);
            if(item_list.size()!=0) {
                for(int i=0;i<item_list.size();i++){
                    Report_detail_month newOutcome = new Report_detail_month(item_list.get(i));
                    adapter.add(newOutcome);
                }

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        outcome_id = (TextView) view.findViewById(R.id.list_Id);
                        String outcomeId = outcome_id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),show_daylist.class);
                        objIndent.putExtra("list_Id", Integer.parseInt(outcomeId));
                        objIndent.putExtra("_case",_case);
                        startActivity(objIndent);
                    }
                });



            }
            lv.setAdapter(adapter);
        }
    }
    private void print_all(int __case) {
        Report_data repo = new Report_data(this);
        Report_metaData item =  repo.getReportById(_list_id);

        List<String> item_list;
        switch (__case)
        {
           case 0: item_list=repo.getTotalListbyMonth(item.month,item.year);break;
           case 1: item_list=repo.getIncomeListbyMonth(item.month,item.year);break;
           case 2: item_list=repo.getOutcomeListbyMonth(item.month,item.year);break;
           default:item_list=null;
        }
        print_list(item_list);

    }
}
