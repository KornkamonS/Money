package tarikalovebird.money.Outcome.outcome_db;

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
 * Created by Preaw-PC on 25/3/2560.
 */

public class Outcome_adepter extends ArrayAdapter<Outcome_detail> {
    public Outcome_adepter(Context context, ArrayList<Outcome_detail> outcome) {
        super(context, 0, outcome);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Outcome_detail outcome = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_outcome_entry, parent, false);
        }
        // Lookup view for data population
        TextView tvID = (TextView) convertView.findViewById(R.id.outcome_Id);
        TextView tvName = (TextView) convertView.findViewById(R.id.outcome_name);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.outcome_amount);
        TextView tvPeriod = (TextView) convertView.findViewById(R.id.outcome_period);
        ImageView tvType = (ImageView) convertView.findViewById(R.id.def2);

        // Populate the data into the template view using the data object
        tvName.setText(outcome.name);
        tvID.setText(outcome.id);
        tvPeriod.setText(outcome.peroid);

        int typeid=Integer.parseInt(outcome.type);
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
        }

        tvAmount.setText(outcome.amount);
        // Return the completed view to render on screen
        return convertView;
    }
}
