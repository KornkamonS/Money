package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tarikalovebird.money.Income.income_db.Income_data;
import tarikalovebird.money.Income.income_db.Income_detial;
import tarikalovebird.money.Outcome.outcome_db.Outcome_data;
import tarikalovebird.money.Outcome.outcome_db.Outcome_detail;
import tarikalovebird.money.Picture.Mypic;
import tarikalovebird.money.Summary.report_db.Report_data;
import tarikalovebird.money.Summary.report_db.Report_metaData;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int cday;
    private int cmonth;
    private int cyear;
    private Calendar c;

    private static final int ADDTARGATRESULT=1;
    private static final int INCOMERESULT=2;
    private static final int OUTCOMERESULT=3;
    private Button outcomeBut;
    private ImageView pic;
    private TextView t;
    private Target target;
    private TextView day;
    private Button incomeBut;
    private TextView total;
    private Report_data a= new Report_data(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        target = new Target(this);
        Timer tt = new Timer();
        tt.scheduleAtFixedRate(timeri,0,1000*60*60*24*1);
        t=(TextView) findViewById(R.id.nameTarget);
        day=(TextView) findViewById(R.id.CoundownDay) ;
        incomeBut = (Button) findViewById(R.id.IncomeBut);
        outcomeBut = (Button) findViewById(R.id.OutcomeBut);
        pic = (ImageView) findViewById(R.id.Hometarget_pic);
        total=(TextView) findViewById(R.id.TodayTotal);

        PrintPage();
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TagetDetail.class);
                startActivityForResult(i,ADDTARGATRESULT);
            }
        }) ;

        incomeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_Income.class);
                startActivityForResult(i, INCOMERESULT);
            }
        }) ;
        outcomeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_Outcome.class);
                startActivityForResult(i, OUTCOMERESULT);
            }
        }) ;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                PrintPage();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {

        } else if (id == R.id.nav_Income) {
            Intent i = new Intent(getApplicationContext(), Income_table.class);
            startActivity(i);

        } else if (id == R.id.nav_Target) {
            Intent i = new Intent(getApplicationContext(), TagetDetail.class);
            startActivityForResult(i,ADDTARGATRESULT);

        }else if (id == R.id.nav_Outcom) {
            Intent i = new Intent(getApplicationContext(), Outcome_table.class);
            startActivity(i);
        } else if (id == R.id.nav_Summary) {
            Intent i = new Intent(getApplicationContext(), Summary_table.class);
            startActivity(i);
        } else if (id == R.id.nav_TIPs) {
            Intent i = new Intent(getApplicationContext(), Tips.class);
            startActivity(i);

        } else if (id == R.id.nav_Noti) {
            Intent i = new Intent(getApplicationContext(), Notification.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void PrintPage() {

        target.setHAVE(a.getTotalmoney());
        t.setText(target.getTargetName());
        String d=target.getCountDown();

        total.setText(String.format("%.2f", target.CanuseToday()));

        boolean flag=false;
        if(target.getRest()<=0)
            flag=true;
        if(d==target.KEY_MISSIONFAIL&&flag==false)
        {
            day.setTextColor(getApplication().getResources().getColor(R.color.out));
            day.setText(target.KEY_MISSIONFAIL);
        }
        else {
            if(flag){ day.setText("Successful !!");
                day.setTextColor(getApplication().getResources().getColor(R.color.in));}
            else day.setText(d.toString());
        }
        switch (target.getTargetType()) {
            case R.id.TypeLearning:
                pic.setImageResource(R.drawable.learning1);
                break;
            case R.id.TypeGift:
                pic.setImageResource(R.drawable.gift1);
                break;
            case R.id.TypeToy:
                pic.setImageResource(R.drawable.toy1);
                break;
            case R.id.TypeMusic:
                pic.setImageResource(R.drawable.music2);
                break;
            case R.id.TypeTechno:
                pic.setImageResource(R.drawable.techno1);
                break;
            case R.id.AddTypeTarget:
                pic.setImageBitmap(Mypic.bitmap);
                break;
            default:
                pic.setImageResource(R.drawable.main);
        }
    }

    TimerTask timeri= new TimerTask(){
        @Override
        public void run() {
            c = Calendar.getInstance();
            cday = c.get(Calendar.DAY_OF_MONTH);
            cmonth = c.get(Calendar.MONTH);
            cyear = c.get(Calendar.YEAR);
            if(cday==1)
            {
                Income_data a=new Income_data(getApplicationContext());
                List<String> incomeListMonth=a.getIncomeMonthList();

                Report_metaData add =new Report_metaData();
                Report_data re=new Report_data(getApplicationContext());

                for(int i=0;i<incomeListMonth.size();i++)
                {
                    Income_detial newIncome = new Income_detial(incomeListMonth.get(i));
                    add.setValue(-1,newIncome.name,Float.parseFloat(newIncome.amount),
                            cday,cmonth+1,cyear,Report_metaData.IN,Integer.parseInt(newIncome.type));
                    re.insert(add);
                }

                Outcome_data o=new Outcome_data(getApplicationContext());
                List<String> outcomeListMonth=o.getOutcomeMonthList();

                for(int i=0;i<outcomeListMonth.size();i++)
                {
                    Outcome_detail newOutcome = new Outcome_detail(outcomeListMonth.get(i));
                    add.setValue(-1,newOutcome.name,Float.parseFloat(newOutcome.amount),
                            cday,cmonth+1,cyear,Report_metaData.OUT,Integer.parseInt(newOutcome.type));

                    re.insert(add);
                }


           }
            if(cday==1&&cmonth==1)
            {
                Income_data a=new Income_data(getApplicationContext());
                List<String> incomeListYear=a.getIncomeYearList();

                Report_metaData add =new Report_metaData();
                Report_data re=new Report_data(getApplicationContext());

                for(int i=0;i<incomeListYear.size();i++)
                {
                    Income_detial newIncome = new Income_detial(incomeListYear.get(i));
                    add.setValue(-1,newIncome.name,Float.parseFloat(newIncome.amount),
                            cday,cmonth+1,cyear,Report_metaData.IN,Integer.parseInt(newIncome.type));

                    re.insert(add);
                }

                Outcome_data o=new Outcome_data(getApplicationContext());
                List<String> outcomeListYear=o.getOutcomeYearList();

                for(int i=0;i<outcomeListYear.size();i++)
                {
                    Outcome_detail newOutcome = new Outcome_detail(outcomeListYear.get(i));
                    add.setValue(-1,newOutcome.name,Float.parseFloat(newOutcome.amount),
                            cday,cmonth+1,cyear,Report_metaData.OUT,Integer.parseInt(newOutcome.type));

                    re.insert(add);
                }

           }
           runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PrintPage();
                }
            });
        }

    };

}
