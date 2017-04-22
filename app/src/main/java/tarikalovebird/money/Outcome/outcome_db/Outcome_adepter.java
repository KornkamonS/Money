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
import tarikalovebird.money.helpcode;


/**
 * Created by Preaw-PC on 25/3/2560.
 */

public class Outcome_adepter extends ArrayAdapter<Outcome_detail> {
    public Outcome_adepter(Context context, ArrayList<Outcome_detail> outcome) {
        super(context, 0, outcome);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Outcome_detail outcome = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list_entry, parent, false);
        }

        TextView tvID = (TextView) convertView.findViewById(R.id.list_Id);
        TextView tvName = (TextView) convertView.findViewById(R.id.list_name);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.list_amount);
        TextView tvPeriod = (TextView) convertView.findViewById(R.id.list_period);
        ImageView tvType = (ImageView) convertView.findViewById(R.id.def1);

        tvName.setText(outcome.name);
        tvID.setText(outcome.id);
        tvPeriod.setText(outcome.peroid);

        helpcode a=new helpcode();
        int typeid=Integer.parseInt(outcome.type);
        tvType.setImageResource(a.getIdpicType(typeid));

        tvAmount.setText(outcome.amount);
        tvPeriod.setTextColor(getContext().getResources().getColor(R.color.out));
        tvAmount.setTextColor(getContext().getResources().getColor(R.color.out));
        return convertView;
    }
}
