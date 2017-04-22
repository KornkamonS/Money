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


public class total_year_adepter extends ArrayAdapter<Report_detail_year> {
    public total_year_adepter(Context context, ArrayList<Report_detail_year> list) {
        super(context, 0, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Report_detail_year report = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.individual_my, parent, false);
        }
        TextView tvID = (TextView) convertView.findViewById(R.id.list_Id);
        TextView tvName = (TextView) convertView.findViewById(R.id.nameR);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.amountR);
        tvID.setText(report.id);

        int month= Integer.parseInt(report.month);
        helpcode a=new helpcode();

        tvName.setText( a.getMonthtext(month) +" " +report.year);
        tvAmount.setText(report.amount);
        if(Float.parseFloat(report.amount)<0)
        {
            tvAmount.setTextColor(getContext().getResources().getColor(R.color.out));}

        else{
            tvAmount.setTextColor(getContext().getResources().getColor(R.color.in));}
        // Return the completed view to render on screen
        return convertView;
    }
}
