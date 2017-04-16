package tarikalovebird.money.Summary.report_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import tarikalovebird.money.R;


public class total_month_adepter extends ArrayAdapter<Report_detail_month> {
    public total_month_adepter(Context context, ArrayList<Report_detail_month> list) {
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
        String tvMonth="Jan";
        int month= Integer.parseInt(report.month);
        switch (month)
        {
            case 1: tvMonth="Jan"; break;
            case 2:  tvMonth="Feb"; break;
            case 3:   tvMonth="Mar"; break;
            case 4:  tvMonth="April"; break;
            case 5:   tvMonth="May"; break;
            case 6:  tvMonth="Jun"; break;
            case 7:   tvMonth="July"; break;
            case 8:  tvMonth="Aug"; break;
            case 9:  tvMonth="Sep"; break;
            case 10:  tvMonth="Oct"; break;
            case 11:  tvMonth="Nov"; break;
            case 12:   tvMonth="Dec"; break;
        }
        tvName.setText(report.day+" "+ tvMonth +" " +report.year);
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
