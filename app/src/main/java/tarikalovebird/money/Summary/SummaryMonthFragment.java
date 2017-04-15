package tarikalovebird.money.Summary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tarikalovebird.money.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryMonthFragment extends Fragment {


    public SummaryMonthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_month, container, false);
    }

}
