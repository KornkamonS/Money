package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TagetDetail extends AppCompatActivity {

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private Target target;
    private Handler mHandler = new Handler();
    private TextView total;
    private TextView dalay;
    private TextView startdate;
    private TextView targetP;
    private TextView targetN;
    private ImageView targetPic;
    private float percent = 0;
    private int summary = 70;//Must be decrease with total summary

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taget_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        target = new Target(this);
        targetP = (TextView) findViewById(R.id.targetPrice);
        total = (TextView) findViewById(R.id.totaltarget);
        targetN = (TextView) findViewById(R.id.targetName);
        startdate = (TextView) findViewById(R.id.startDatetext);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        targetPic = (ImageView) findViewById(R.id.targetpicc);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddTarget.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here

        targetN.setText(target.getTargetName());
        targetP.setText("Target Price : " + target.getTargetPrice() + " บาท");
        startdate.setText("วันที่เริ่มต้นเก็บเงิน : " + target.getSTARTdate());
        total.setText("จำนวนเงินที่ต้องเก็บ : " + String.valueOf((target.getTargetPrice() - summary)) + " บาท");
        percent = ((summary / target.getTargetPrice()) * 100);
        if (percent > 100) percent = 100;

        ProgressBarAnimation anim = new ProgressBarAnimation(mProgress, 0, percent);
        anim.setDuration(1000);
        mProgress.startAnimation(anim);

        switch (target.getTargetType()) {
            case R.id.TypeFood:
                targetPic.setImageResource(R.drawable.type_food);
                break;
            case R.id.TypeGift:
                targetPic.setImageResource(R.drawable.type_gift);
                break;
            case R.id.TypeLearning:
                targetPic.setImageResource(R.drawable.type_learning);
                break;
            case R.id.TypeMusic:
                targetPic.setImageResource(R.drawable.type_music);
                break;
            case R.id.TypeTechno:
                targetPic.setImageResource(R.drawable.type_techno);
                break;
            case R.id.AddType:
                targetPic.setImageResource(R.drawable.type_add);
                break;
            default:
                targetPic.setImageResource(R.drawable.ic_menu_gallery);
        }
    }

    public class ProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float to;

        public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int) value);
        }

    }
}
