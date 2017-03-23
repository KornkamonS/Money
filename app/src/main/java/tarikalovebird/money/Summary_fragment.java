package tarikalovebird.money;

/**
 * Created by TunasanG on 22/3/2560.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TunasanG on 22/3/2560.
 */

public class Summary_fragment extends Fragment{

    View myview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.content_summary,container,false);
        return myview;

    }
}
