package tarikalovebird.money.Summary.report_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tarikalovebird.money.R;
import tarikalovebird.money.helpcode;


public class total_outmonth_adepter extends ArrayAdapter<Report_detail_month> {
    public total_outmonth_adepter(Context context, ArrayList<Report_detail_month> list) {
        super(context, 0, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Report_detail_month report = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.individual_my, parent, false);
        }
        TextView tvID = (TextView) convertView.findViewById(R.id.list_Id);
        TextView tvName = (TextView) convertView.findViewById(R.id.nameR);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.amountR);
        tvID.setText(report.id);

        int month= Integer.parseInt(report.month);
        helpcode a=new helpcode();
        tvName.setText(report.day+" "+ a.getMonthtext(month) +" " +report.year);
        tvAmount.setText(report.amount);
        tvAmount.setTextColor(getContext().getResources().getColor(R.color.out));
        return convertView;
    }
}
