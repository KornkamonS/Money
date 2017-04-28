package tarikalovebird.money.Summary;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
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
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_detail_year;
import tarikalovebird.money.Summary.report_db.total_year_adepter;
import tarikalovebird.money.show_monthlist;

public class SummaryYearFragment extends Fragment  {

    public SummaryYearFragment() {}

    private TextView dateBut;
    private int mYear;
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
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }
    public void show()
    {

        final Dialog d = new Dialog(getActivity());
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.fragment_year_picker);
        TextView b1 = (TextView) d.findViewById(R.id.button1);
        TextView b2 = (TextView) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);

        np.setMaxValue(mYear+50);
        np.setMinValue(mYear-50);
        np.setValue(mYear);
        //np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.i("value is",""+newVal);
            }
        } );
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dateBut.setText(String.valueOf(np.getValue()));
                print_all(np.getValue());
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }
    private void setDefault(){
        dateBut = (TextView) myView.findViewById(R.id.seldate);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        dateBut.setText(String.valueOf(mYear));
        print_all(mYear);
    }
    private void print_list(List<String> reportList){

        ArrayList<Report_detail_year> arrayOfReport = new ArrayList<Report_detail_year>();
        total_year_adepter adapter = new total_year_adepter(getActivity(), arrayOfReport);
        ListView lv = (ListView) myView.findViewById(R.id.listR);

        if(reportList.size()!=0) {
            for(int i=0;i<reportList.size();i++){
                Report_detail_year newReport = new Report_detail_year(reportList.get(i));
                adapter.add(newReport);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Report_id = (TextView) view.findViewById(R.id.list_Id);
                    String incomeId = Report_id.getText().toString();
                    Intent objIndent = new Intent(getContext(),show_monthlist.class);
                    objIndent.putExtra("list_Id", Integer.parseInt(incomeId));
                    objIndent.putExtra("_case",0);
                    startActivity(objIndent);
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
        float max=0,min=0;
        if(reportList.size()!=0) {
            graph.removeAllSeries();
            for(int i=0;i<reportList.size();i++)
            {
                Report_detail_year newReport = new Report_detail_year(reportList.get(i));
                float y = Float.parseFloat(newReport.amount);
                DataPoint v = new DataPoint(i, y);
                values[i] = v;
                if(y>max) max=y;
                if(y<min) min=y;
            }

            BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(values);

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

            graph.getGridLabelRenderer().setGridStyle( GridLabelRenderer.GridStyle.HORIZONTAL );
           graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMaxY(max);
            graph.getViewport().setMinY(min);
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
    private void print_all(int year) {
        Report_data repo = new Report_data(getContext());
        List<String> reportList =  repo.getTotalListbyYear(year);
        print_list(reportList);
        print_graph(reportList);
    }
}
