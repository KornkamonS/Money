package income_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tarikalovebird.money.R;

/**
 * Created by TunasanG on 25/3/2560.
 */

public class Income_adepter extends ArrayAdapter<Income_detial>{
    public Income_adepter(Context context, ArrayList<Income_detial> income) {
        super(context, 0, income);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Income_detial income = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_income_entry, parent, false);
        }
        // Lookup view for data population
        TextView tvID = (TextView) convertView.findViewById(R.id.income_Id);
        TextView tvName = (TextView) convertView.findViewById(R.id.income_name);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.income_amount);
        TextView tvPeriod = (TextView) convertView.findViewById(R.id.income_period);
        ImageView tvType = (ImageView) convertView.findViewById(R.id.def1);

        // Populate the data into the template view using the data object
        tvName.setText(income.name);
        tvID.setText(income.id);
        tvPeriod.setText(income.peroid);

        int typeid=Integer.parseInt(income.type);
        switch (typeid)
        {
            case R.id.gift: tvType.setImageResource(R.drawable.gifts1); break;
            case R.id.salary: tvType.setImageResource(R.drawable.salary1); break;
            case R.id.sale:  tvType.setImageResource(R.drawable.sale1); break;
            case R.id.add: tvType.setImageResource(R.drawable.add1); break;
        }


        tvAmount.setText(income.amount);
        // Return the completed view to render on screen
        return convertView;
    }

}