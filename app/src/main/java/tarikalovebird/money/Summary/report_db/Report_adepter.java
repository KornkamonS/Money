package tarikalovebird.money.Summary.report_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import tarikalovebird.money.R;
import tarikalovebird.money.helpcode;


public class Report_adepter extends ArrayAdapter<Report_detail> {
    public Report_adepter(Context context, ArrayList<Report_detail> outcome) {
        super(context, 0, outcome);
    }
   @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Report_detail report = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list_entry, parent, false);
        }

        TextView tvID = (TextView) convertView.findViewById(R.id.list_Id);
        TextView tvName = (TextView) convertView.findViewById(R.id.list_name);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.list_amount);
        TextView tvSettext = (TextView) convertView.findViewById(R.id.settext);
        TextView tvDate = (TextView) convertView.findViewById(R.id.list_period);
        ImageView tvType = (ImageView) convertView.findViewById(R.id.def1);


       tvSettext.setText("DATE :");
        tvName.setText(report.name);
        tvID.setText(report.id);

       int typeid=Integer.parseInt(report.type);
       tvType.setImageResource(helpcode.getIdpicType(typeid));
       int month= Integer.parseInt(report.month);


       tvDate.setText(helpcode.formatDayMonthYear(Integer.parseInt(report.day),month-1,Integer.parseInt(report.year)));
       tvAmount.setText(report.amount);
       if(report.inorout.equals(String.valueOf(Report_metaData.IN)))
       {tvDate.setTextColor(getContext().getResources().getColor(R.color.in));
       tvAmount.setTextColor(getContext().getResources().getColor(R.color.in));}

       else
           {tvDate.setTextColor(getContext().getResources().getColor(R.color.out));
               tvAmount.setTextColor(getContext().getResources().getColor(R.color.out));}

        return convertView;
    }
}
