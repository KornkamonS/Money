package Income;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tarikalovebird.money.EditIncomeSchedule;
import tarikalovebird.money.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeDayFragment extends Fragment {

    View myview;
    private Button incomesche;
    public IncomeDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview=inflater.inflate(R.layout.fragment_income_day,container,false);
        incomesche= (Button) myview.findViewById(R.id.editincomesche);

        incomesche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditIncomeSchedule.class);
                startActivity(i);
            }
        });
        return myview;
    }

}
