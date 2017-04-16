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


public class Report_adepter extends ArrayAdapter<Report_detail> {
    public Report_adepter(Context context, ArrayList<Report_detail> outcome) {
        super(context, 0, outcome);
    }
   @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Report_detail report = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list_entry, parent, false);
        }
        // Lookup view for data population
        TextView tvID = (TextView) convertView.findViewById(R.id.list_Id);
        TextView tvName = (TextView) convertView.findViewById(R.id.list_name);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.list_amount);
        TextView tvSettext = (TextView) convertView.findViewById(R.id.settext);
        TextView tvDate = (TextView) convertView.findViewById(R.id.list_period);
        ImageView tvType = (ImageView) convertView.findViewById(R.id.def1);

        tvSettext.setText("DATE :");
        // Populate the data into the template view using the data object
        tvName.setText(report.name);
        tvID.setText(report.id);
        int typeid=Integer.parseInt(report.type);
        switch (typeid)
        {
            case R.id.gift2: tvType.setImageResource(R.drawable.food1); break;
            case R.id.oil: tvType.setImageResource(R.drawable.oil1); break;
            case R.id.home:  tvType.setImageResource(R.drawable.home1); break;
            case R.id.elect: tvType.setImageResource(R.drawable.elec1); break;
            case R.id.shop:  tvType.setImageResource(R.drawable.shop1); break;
            case R.id.entertain: tvType.setImageResource(R.drawable.entertain1); break;
            case R.id.pen:  tvType.setImageResource(R.drawable.pen1); break;
            case R.id.study: tvType.setImageResource(R.drawable.study1); break;
            case R.id.add2: tvType.setImageResource(R.drawable.addd1); break;

            case R.id.gift: tvType.setImageResource(R.drawable.gifts1); break;
            case R.id.salary: tvType.setImageResource(R.drawable.salary1); break;
            case R.id.sale:  tvType.setImageResource(R.drawable.sale1); break;
            case R.id.add: tvType.setImageResource(R.drawable.add1); break;
        }
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
        tvDate.setText(report.day+"-"+tvMonth+"-"+report.year);
       tvAmount.setText(report.amount);
       if(report.inorout.equals(String.valueOf(Report_metaData.IN)))
       {tvDate.setTextColor(getContext().getResources().getColor(R.color.in));
       tvAmount.setTextColor(getContext().getResources().getColor(R.color.in));}

       else
           {tvDate.setTextColor(getContext().getResources().getColor(R.color.out));
               tvAmount.setTextColor(getContext().getResources().getColor(R.color.out));}


        // Return the completed view to render on screen
        return convertView;
    }
}
