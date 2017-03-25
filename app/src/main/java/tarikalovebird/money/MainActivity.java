package tarikalovebird.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ADDTARGATRESULT=1;
    private static final int INCOMERESULT=2;
    private static final int OUTCOMERESULT=3;
    private Button outcomeBut;
    private ImageView pic;
    private TextView t;
    private Target target;
    private TextView day;
    private Button incomeBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        target = new Target(this);

        t=(TextView) findViewById(R.id.nameTarget);
        day=(TextView) findViewById(R.id.CoundownDay) ;
        incomeBut = (Button) findViewById(R.id.IncomeBut);
        outcomeBut = (Button) findViewById(R.id.OutcomeBut);
        pic = (ImageView) findViewById(R.id.Hometarget_pic);

        PrintPage();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.imageTargetBut);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TagetDetail.class);
                startActivityForResult(i,ADDTARGATRESULT);
            }
        });
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
        if (requestCode == ADDTARGATRESULT) {

            if(resultCode == RESULT_OK){
                //Update List
                PrintPage();
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
        if (requestCode == INCOMERESULT)
        {
            if(resultCode == RESULT_OK){
                //refresh page
                Toast.makeText(this, "Income",
                        Toast.LENGTH_LONG).show();
                PrintPage();
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
        if (requestCode == OUTCOMERESULT)
        {
            if(resultCode == RESULT_OK){
                //refresh page
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
    }//onActivityResult*/

    /*@Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here

    }*/
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

        } else if (id == R.id.nav_Outcom) {
            Intent i = new Intent(getApplicationContext(), Outcome_table.class);
            startActivity(i);
        } else if (id == R.id.nav_Summary) {
            Intent i = new Intent(getApplicationContext(), Summary_table.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_TIPs) {

        } else if (id == R.id.nav_Noti) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void PrintPage() {
        t.setText(target.getTargetName());
        day.setText(target.getCountDown());
        switch ( target.getTargetType())
        {
            case R.id.TypeLearning: pic.setImageResource(R.drawable.type_book);
                break;
            case R.id.TypeGift:pic.setImageResource(R.drawable.type_gift);
                break;
            case R.id.TypeToy:pic.setImageResource(R.drawable.type_toy);
                break;
            case R.id.TypeMusic:pic.setImageResource(R.drawable.type_music);
                break;
            case R.id.TypeTechno: pic.setImageResource(R.drawable.type_techno);
                break;
            case R.id.AddType: pic.setImageResource(R.drawable.type_add);
                break;
            default: pic.setImageResource(R.mipmap.ic_launcher_round);
        }
    }
}
