package tarikalovebird.money.Summary;

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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import tarikalovebird.money.R;
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_detail_month;
import tarikalovebird.money.Summary.report_db.total_month_adepter;
import tarikalovebird.money.show_daylist;
import utils.DateDisplayUtils;
import widget.SimpleDatePickerDialog;
import widget.SimpleDatePickerDialogFragment;

public class IncomeMonthFragment extends Fragment implements SimpleDatePickerDialog.OnDateSetListener, View.OnClickListener{

    public IncomeMonthFragment() {
        // Required empty public constructor
    }
    private TextView mPickDateButton;
    private int mYear;
    private int mMonth;
    public TextView Report_id;
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView= inflater.inflate(R.layout.fragment_graph, container, false);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);

        mPickDateButton = (TextView) myView.findViewById(R.id.seldate);
        mPickDateButton.setText(DateDisplayUtils.formatMonthYear(mYear,mMonth));
        mPickDateButton.setOnClickListener(this);
        print_all(mMonth+1,mYear);
        return myView;
    }

    @Override
    public void onDateSet(int year, int monthOfYear) {
        mPickDateButton.setText(DateDisplayUtils.formatMonthYear(year, monthOfYear));
        print_all(monthOfYear+1,year);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.seldate) {
            displaySimpleDatePickerDialogFragment();
        }
    }

    private void displaySimpleDatePickerDialogFragment() {
        SimpleDatePickerDialogFragment datePickerDialogFragment;
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        datePickerDialogFragment = SimpleDatePickerDialogFragment.getInstance(
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        datePickerDialogFragment.setOnDateSetListener(this);
        datePickerDialogFragment.show(getChildFragmentManager(), null);
    }

    private void print_list(List<String> reportList){

        ArrayList<Report_detail_month> arrayOfReport = new ArrayList<Report_detail_month>();
        total_month_adepter adapter = new total_month_adepter(getActivity(), arrayOfReport);
        ListView lv = (ListView) myView.findViewById(R.id.listR);

        if(reportList.size()!=0) {
            for(int i=0;i<reportList.size();i++)
            {
                Report_detail_month newReport = new Report_detail_month(reportList.get(i));
                if(newReport.id!=null)
                    adapter.add(newReport);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Report_id = (TextView) view.findViewById(R.id.list_Id);
                    String incomeId = Report_id.getText().toString();
                    Intent objIndent = new Intent(getContext(),show_daylist.class);
                    objIndent.putExtra("list_Id", Integer.parseInt(incomeId));
                    objIndent.putExtra("_case",1);
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

        float min=0,max=0;
        graph.removeAllSeries();
        if(reportList.size()!=0) {

            for(int i=0;i<reportList.size();i++)
            {
                Report_detail_month newReport = new Report_detail_month(reportList.get(i));
                float y = Float.parseFloat(newReport.amount);
                DataPoint v = new DataPoint(i, y);

                values[i] = v;
                if(y>max)max=y;
                if(y<min)min=y;
            }

            BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(values);
            series.setSpacing(50);
            graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
            graph.getGridLabelRenderer().setNumHorizontalLabels(reportList.size());
            series.setAnimated(true);

            series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint data) {
                        return getContext().getResources().getColor(R.color.in);
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
    private void print_all(int m,int y) {
        Report_data repo = new Report_data(getContext());
        List<String> reportList =  repo.getIncomeListbyMonth(m,y);
        print_list(reportList);
        print_graph(reportList);
    }


}
