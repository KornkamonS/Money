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

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        myView= inflater.inflate(R.layout.fragment_summary_day, container, false);

        dateBut = (Button) myView.findViewById(R.id.seldate);
        dateBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), myCalendarView.class);
                startActivityForResult(intent,CALENDAR_VIEW_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateCurrentDate();
        aa=new Get_datefromCalender(getActivity());

        print();
        return myView;
    }
    void print()
    {
        Report_data repo = new Report_data(getContext());
        List<String> reportList =  repo.getReportListbyDate(mDay,mMonth,mYear);

        ArrayList<Report_detail> arrayOfReport = new ArrayList<Report_detail>();
        Report_adepter adapter = new Report_adepter(getActivity(), arrayOfReport);
        ListView lv = (ListView) myView.findViewById(R.id.listR);
        DataPoint[] values = new DataPoint[reportList.size()];
        String[] xlabel= new String[reportList.size()];
        if(reportList.size()!=0) {
            for(int i=0;i<reportList.size();i++)
            {
                Report_detail newReport = new Report_detail(reportList.get(i));
                //Date x = new Date(Integer.parseInt(newReport.year),Integer.parseInt(newReport.month),Integer.parseInt(newReport.day));

                float y = Integer.parseInt(newReport.amount)*Integer.parseInt(newReport.inorout);
                DataPoint v = new DataPoint(i, y);
                xlabel[i]=newReport.name;
                values[i] = v;
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

        GraphView graph = (GraphView) myView.findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(values);
        series.setSpacing(20);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(xlabel);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                if(data.getY()>=0)
                    return getContext().getResources().getColor(R.color.in);
                else return getContext().getResources().getColor(R.color.out);
            }
        });

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(reportList.size()+1);
        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        series.setDrawValuesOnTop(true);
        series.setValuesOnTopSize(40);

        series.setValuesOnTopColor(getContext().getResources().getColor(R.color.colorPrimaryDark));

        graph.addSeries(series);
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
                    print();
                    break;
                }
        }

    }
}
