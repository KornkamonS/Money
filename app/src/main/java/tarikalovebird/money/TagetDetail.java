package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import tarikalovebird.money.Summary.report_db.Report_data;

import static tarikalovebird.money.Picture.Mypic.bitmap;


public class TagetDetail extends AppCompatActivity {

    private static final int PROGRESS = 0x1;
    private static final int ADDTARGET=999;
    private ProgressBar mProgress;
    private Target target;
    private Handler mHandler = new Handler();
    private TextView summarytext;
    private TextView total;
    private TextView dalay;
    private TextView startdate;
    private TextView targetP;
    private TextView targetN;
    private ImageView targetPic;
    private TextView percenttext;
    private TextView endDate;
    private TextView unit3;
    private String per;
    private TextView delay;
    private float percent = 0;
    private float summary = 70;//Must be decrease with total summary

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taget_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        target = new Target(this);
        targetP = (TextView) findViewById(R.id.DetialtargetPrice);
        total = (TextView) findViewById(R.id.Detialtotaltarget);
        targetN = (TextView) findViewById(R.id.DetialtargetName);
        startdate = (TextView) findViewById(R.id.DetialstartDatetext);
        mProgress = (ProgressBar) findViewById(R.id.DetialprogressBar);
        targetPic = (ImageView) findViewById(R.id.Detialtargetpicc);
        summarytext = (TextView) findViewById(R.id.Detialhave);
        percenttext = (TextView) findViewById(R.id.DetialPercent);
        unit3=(TextView) findViewById(R.id.Detialunit3) ;
        endDate=(TextView)findViewById(R.id.DetialFDatetext) ;
        delay=(TextView) findViewById(R.id.Detialdelay) ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Add_Target.class);
                startActivityForResult(i,ADDTARGET);
            }
        });

        printPage();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADDTARGET) {

            if(resultCode == RESULT_OK){
                printPage();
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }

    }
    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
        super.onBackPressed();

    }
    private void printPage()
    {
        summary=target.getTotalkeep();
        float rest =target.getRest();
        String Rest=String.valueOf(rest);
        if(rest<=0&&target.getBool())
        {
            Rest="Successful!!";
            target.resetTarget();
            unit3.setVisibility(View.INVISIBLE);
        }else unit3.setVisibility(View.VISIBLE);

        percent = ((summary / target.getTargetPrice()) * 100);
        if (percent > 100) percent = 100;
        if(percent<0) percent=0;
        if(percent>100) percent=100;
        per=String.valueOf((int)percent)+" %";

        targetN.setText(target.getTargetName());
        targetP.setText(String.valueOf(target.getTargetPrice()));
        if(target.getBool())
        { startdate.setText(target.getSTARTdate());
        endDate.setText(target.getFdate());}
        else {startdate.setText(" ");
            endDate.setText(" ");}
        total.setText(Rest);

        summarytext.setText(String.valueOf(summary));
        percenttext.setText(per);
        delay.setText(""+target.getDiffDay());

        MyAnimation.ProgressBarAnimation anim = new MyAnimation.ProgressBarAnimation(mProgress, 0, percent);
        anim.setDuration(1000);
        mProgress.startAnimation(anim);

        switch (target.getTargetType()) {
            case R.id.TypeLearning:
                targetPic.setImageResource(R.drawable.learning1);
                break;
            case R.id.TypeGift:
                targetPic.setImageResource(R.drawable.gift1);
                break;
            case R.id.TypeToy:
                targetPic.setImageResource(R.drawable.toy1);
                break;
            case R.id.TypeMusic:
                targetPic.setImageResource(R.drawable.music2);
                break;
            case R.id.TypeTechno:
                targetPic.setImageResource(R.drawable.techno1);
                break;
            case R.id.AddTypeTarget:
                targetPic.setImageBitmap(bitmap);
                break;
            default:
                targetPic.setImageResource(R.drawable.main);
        }
    }
}
