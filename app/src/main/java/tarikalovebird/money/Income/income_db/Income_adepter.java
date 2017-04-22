package tarikalovebird.money.Income.income_db;

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list_entry, parent, false);
        }
        // Lookup view for data population
        TextView tvID = (TextView) convertView.findViewById(R.id.list_Id);
        TextView tvName = (TextView) convertView.findViewById(R.id.list_name);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.list_amount);
        TextView tvPeriod = (TextView) convertView.findViewById(R.id.list_period);
        ImageView tvType = (ImageView) convertView.findViewById(R.id.def1);

        // Populate the data into the template view using the data object
        tvName.setText(income.name);
        tvID.setText(income.id);
        tvPeriod.setText(income.peroid);

        int typeid=Integer.parseInt(income.type);
        helpcode a =new helpcode();
        tvType.setImageResource(a.getIdpicType(typeid));

        tvPeriod.setTextColor(getContext().getResources().getColor(R.color.in));
        tvAmount.setTextColor(getContext().getResources().getColor(R.color.in));
        tvAmount.setText(income.amount);

        return convertView;
    }

}