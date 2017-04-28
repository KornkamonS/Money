package tarikalovebird.money.Summary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tarikalovebird.money.R;
import tarikalovebird.money.Summary.report_db.Report_adepter;
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_detail;
import tarikalovebird.money.Summary.report_db.Report_metaData;
import tarikalovebird.money.helpcode;

public class SummaryDayFragment extends Fragment {

    public SummaryDayFragment(){}

    private TextView dateBut;
    private int mYear;
    private int mMonth;
    private int mDay;
    DatePickerDialog datePickerDialog;
    private  View myView;
    public TextView Report_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView= inflater.inflate(R.layout.fragment_graph, container, false);
        setDefault();
        Select_Date();
        return myView;
    }
    private void Select_Date() {
        dateBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mYear=year;
                                mMonth=monthOfYear+1;
                                mDay=dayOfMonth;
                                updateCurrentDate();
                                print_all();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }
    private void setDefault(){
        dateBut = (TextView) myView.findViewById(R.id.seldate);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateCurrentDate();
        print_all();
    }
    private void print_list(List<String> reportList){

        ArrayList<Report_detail> arrayOfReport = new ArrayList<Report_detail>();
        Report_adepter adapter = new Report_adepter(getActivity(), arrayOfReport);
        ListView lv = (ListView) myView.findViewById(R.id.listR);

        if(reportList.size()!=0) {
            for(int i=0;i<reportList.size();i++){
                Report_detail newReport = new Report_detail(reportList.get(i));
                adapter.add(newReport);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Report_id = (TextView) view.findViewById(R.id.list_Id);
                    String incomeId = Report_id.getText().toString();
                    Report_data search=new Report_data(getActivity());
                    Report_metaData a= search.getReportById(Integer.parseInt(incomeId));
                    if(a.inorout==Report_metaData.IN)
                    {
                        Intent objIndent = new Intent(getContext(),edit_in_type.class);
                        objIndent.putExtra("income_Id", Integer.parseInt(incomeId));
                        startActivityForResult(objIndent,1);
                    }
                    else if(a.inorout==Report_metaData.OUT)
                    {
                        Intent objIndent = new Intent(getContext(),edit_out_type.class);
                        objIndent.putExtra("income_Id", Integer.parseInt(incomeId));
                        startActivityForResult(objIndent,1);
                    }
                }
            });
            lv.setAdapter(adapter);
        }
        lv.setAdapter(adapter);
    }
    private void print_graph(List<String> reportList)
    {
        GraphView graph = (GraphView) myView.findViewById(R.id.graph);
        DataPoint[] values = new DataPoint[reportList.size()];

        float min=0,max=0;
        if(reportList.size()!=0) {
            graph.removeAllSeries();
            for(int i=0;i<reportList.size();i++)
            {
                Report_detail newReport = new Report_detail(reportList.get(i));
                float y = Float.parseFloat(newReport.amount)*Integer.parseInt(newReport.inorout);
                DataPoint v = new DataPoint(i, y);
                values[i] = v;
                if(y>max)max=y;
                if(y<min)min=y;
            }

            BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(values);
            graph.getGridLabelRenderer().setGridColor(getContext().getResources().getColor(R.color.text));
            series.setSpacing(50);
            graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
            graph.getGridLabelRenderer().setNumHorizontalLabels(reportList.size());
            series.setAnimated(true);
            series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint data) {
                    if(data.getY()>=0)
                        return getContext().getResources().getColor(R.color.in);
                    else return getContext().getResources().getColor(R.color.out);
                }
            });

            graph.setTitleColor(getContext().getResources().getColor(R.color.text));
            graph.getGridLabelRenderer().setGridStyle( GridLabelRenderer.GridStyle.HORIZONTAL );
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(min);
            graph.getViewport().setMaxY(max);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(reportList.size());
            graph.getViewport().setScrollable(true);
            graph.getViewport().setScrollableY(true);
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopSize(40);
            series.setValuesOnTopColor(getContext().getResources().getColor(R.color.text));
            graph.addSeries(series);

        }else graph.removeAllSeries();

    }
    private void print_all() {
        Report_data repo = new Report_data(getContext());
        List<String> reportList =  repo.getReportListbyDate(mDay,mMonth,mYear);
        print_list(reportList);
        print_graph(reportList);
    }
    private void updateCurrentDate() {
        dateBut.setText(helpcode.formatDayMonthYear(mDay,mMonth-1,mYear));

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
       print_all();
    }
}
