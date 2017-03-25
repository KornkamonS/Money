package outcome_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        TextView tvType = (TextView) convertView.findViewById(R.id.outcome_type);

        // Populate the data into the template view using the data object
        tvName.setText(outcome.name);
        tvID.setText(outcome.id);
        tvPeriod.setText(outcome.peroid);
        tvType.setText(outcome.type);
        tvAmount.setText(outcome.amount);
        // Return the completed view to render on screen
        return convertView;
    }




}
