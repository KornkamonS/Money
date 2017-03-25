package income_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        TextView tvType = (TextView) convertView.findViewById(R.id.income_type);

        // Populate the data into the template view using the data object
        tvName.setText("name "+income.name);
        tvID.setText("id "+income.id);
        tvPeriod.setText("period "+income.peroid);
        tvType.setText("type "+income.type);
        tvAmount.setText("amount "+income.amount);
        // Return the completed view to render on screen
        return convertView;
    }

}
/*public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
       super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       User user = getItem(position);
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
       }
       // Lookup view for data population
       TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
       TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
       // Populate the data into the template view using the data object
       tvName.setText(user.name);
       tvHome.setText(user.hometown);
       // Return the completed view to render on screen
       return convertView;
   }
}*/