package tarikalovebird.money;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageButton targetBut;
    private TextView t;
    private Target target;
    private TextView day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        targetBut = (ImageButton) findViewById(R.id.imageTargetBut);
        t=(TextView) findViewById(R.id.nameTarget);
        day=(TextView) findViewById(R.id.CoundownDay) ;
        target=new Target(this);
        t.setText(target.getTargetName());
        day.setText(target.getCountDown());

        targetBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddTarget.class);
                startActivityForResult(i, 1);
            }
        }) ;

        /*targetBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddTarget.class);
                startActivity(i);
            }
        }) ;*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

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
                    case R.id.TypeGift:targetBut.setBackgroundResource(R.drawable.type_gift);
                        break;
                    case R.id.TypeLearning:targetBut.setBackgroundResource(R.drawable.type_learning);
                        break;
                    case R.id.TypeMusic:targetBut.setBackgroundResource(R.drawable.type_music);
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
    }//onActivityResult
    /*@Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        t.setText(target.getTargetName());
        day.setText(target.getCountDown());
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
            // Handle the camera action
        } else if (id == R.id.nav_Income) {

        } else if (id == R.id.nav_Outcom) {

        } else if (id == R.id.nav_Summary) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_TIPs) {

        } else if (id == R.id.nav_Noti) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
