package tarikalovebird.money.Summary;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tarikalovebird.money.Get_datefromCalender;
import tarikalovebird.money.R;
import tarikalovebird.money.Summary.report_db.Report_adepter;
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_detail;
import tarikalovebird.money.myCalendarView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryDayFragment extends Fragment {

    private Button dateBut;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int CALENDAR_VIEW_ID = 1;
    private Get_datefromCalender aa;
    public SummaryDayFragment() {
        // Required empty public constructor
    }
    private  View myView;
    public TextView Report_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView= inflater.inflate(R.layout.fragment_summary_day, container, false);

        dateBut = (Button) myView.findViewById(R.id.seldate);

        // add a click listener to the button
        dateBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), myCalendarView.class);
                startActivityForResult(intent,CALENDAR_VIEW_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        updateCurrentDate();

        aa=new Get_datefromCalender(getActivity());

        print();
        return myView;
    }
    void print()
    {
        Report_data repo = new Report_data(getContext());
        List<String> reportList =  repo.getReportList();

        ArrayList<Report_detail> arrayOfReport = new ArrayList<Report_detail>();
        Report_adepter adapter = new Report_adepter(getActivity(), arrayOfReport);
        ListView lv = (ListView) myView.findViewById(R.id.listR);
        Toast.makeText(getActivity(),String.valueOf(reportList.size()),Toast.LENGTH_SHORT).show();
        if(reportList.size()!=0) {
            for(int i=0;i<reportList.size();i++)
            {
                Report_detail newReport = new Report_detail(reportList.get(i));
                adapter.add(newReport);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Report_id = (TextView) view.findViewById(R.id.IdR);
                    String incomeId = Report_id.getText().toString();
                    //Intent objIndent = new Intent(getContext(),Income_edit_item.class);
                    // objIndent.putExtra("income_Id", Integer.parseInt(incomeId));
                    // startActivityForResult(objIndent,1);
                }
            });
            lv.setAdapter(adapter);
        }
        lv.setAdapter(adapter);
    }

    private void updateCurrentDate() {
        dateBut.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append("-")
                        .append(mMonth).append("-")
                        .append(mYear).append(" "));
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
            case CALENDAR_VIEW_ID:
                if (resultCode == Activity.RESULT_OK) {

                    mDay=aa.getDay();
                    mMonth=aa.getMonth();
                    mYear=aa.getYear();

                    Bundle bundle = data.getExtras();
                    dateBut.setText(bundle.getString("dateSelected"));
                    break;
                }
        }

    }
}
