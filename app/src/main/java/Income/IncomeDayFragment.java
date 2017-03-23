package Income;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tarikalovebird.money.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeDayFragment extends Fragment {


    public IncomeDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income_day, container, false);
    }

}
