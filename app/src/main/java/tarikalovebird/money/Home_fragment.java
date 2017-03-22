package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by TunasanG on 22/3/2560.
 */

public class Home_fragment extends Fragment{

    private ImageButton targetBut;
    private TextView t;
    private Target target;
    private TextView day;

    View myview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.content_home,container,false);


        targetBut = (ImageButton) myview.findViewById(R.id.imageTargetBut);
        t=(TextView) myview.findViewById(R.id.nameTarget);
        day=(TextView) myview.findViewById(R.id.CoundownDay) ;
        target=new Target(this.getContext());
        t.setText(target.getTargetName());
        day.setText(target.getCountDown());

        targetBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getActivity(),AddTarget.class);
                startActivityForResult(i, 1);
            }
        });
        return myview;
    }
    @Override
    public   void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){
                //Update List
                t.setText(target.getTargetName());
                day.setText(target.getCountDown());
                int idpic=0;
                switch ( target.getTargetType())
                {
                    case R.id.TypeFood: targetBut.setBackgroundResource(R.drawable.type_food);
                        break;
                    case R.id.TypeGift: targetBut.setBackgroundResource(R.drawable.type_gift);
                        break;
                    case R.id.TypeLearning: targetBut.setBackgroundResource(R.drawable.type_learning);
                        break;
                    case R.id.TypeMusic: targetBut.setBackgroundResource(R.drawable.type_music);
                        break;
                    case R.id.TypeTechno: targetBut.setBackgroundResource(R.drawable.type_techno);
                        break;
                    case R.id.AddType: targetBut.setBackgroundResource(R.drawable.type_add);
                        break;
                    default: targetBut.setBackgroundResource(R.drawable.ic_menu_gallery);
                }

            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
    }
}
