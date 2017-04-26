package tarikalovebird.money;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Notification extends AppCompatActivity {

    int minutes;
    int hour;
    int count = 0;
    int hour_T_1;
    int min_T_1;
    int hour_T_2;
    int min_T_2;
    int hour_T_3;
    int min_T_3;
    int hour_T_4;
    int min_T_4;
    int s1 = 0;
    int s2 = 0;
    int s3 = 0;
    int s4 = 0;
    int cShow1 = 0;
    int cShow2 = 0;
    int cShow3 = 0;
    int cShow4 = 0;
    int[] hourTime = new int[4];
    int[] minTime = new int[4];
    String hh1;
    String hh2;
    String hh3;
    String hh4;
    String mm1;
    String mm2;
    String mm3;
    String mm4;
    int send_1=0;
    int send_2=0;
    int send_3=0;
    int send_4=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calendar cc = Calendar.getInstance();
        minutes = cc.get(Calendar.MINUTE);
        hour = cc.get(Calendar.HOUR);

        TimePicker TimePop = (TimePicker) findViewById(R.id.timeInput);


        TimePop.setIs24HourView(true);

        Timer tt = new Timer();
        tt.scheduleAtFixedRate(timer, 0, 1000 * 1);


        final Switch mySwitch_1 = (Switch) findViewById(R.id.switch1);
        final Switch mySwitch_2 = (Switch) findViewById(R.id.switch2);
        final Switch mySwitch_3 = (Switch) findViewById(R.id.switch3);
        final Switch mySwitch_4 = (Switch) findViewById(R.id.switch4);

        final Button button_1 = (Button) findViewById(R.id.button);
        final Button button_2 = (Button) findViewById(R.id.button2);
        final Button button_3 = (Button) findViewById(R.id.button3);
        final Button button_4 = (Button) findViewById(R.id.button4);

        mySwitch_1.setVisibility(View.INVISIBLE);
        mySwitch_2.setVisibility(View.INVISIBLE);
        mySwitch_3.setVisibility(View.INVISIBLE);
        mySwitch_4.setVisibility(View.INVISIBLE);
        button_1.setVisibility(View.INVISIBLE);
        button_2.setVisibility(View.INVISIBLE);
        button_3.setVisibility(View.INVISIBLE);
        button_4.setVisibility(View.INVISIBLE);


        Button buttonSave = (Button) findViewById(R.id.btn_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (count == 4) {
                } else if (s1 == 0) {

                    TimePicker timePicker1 = (TimePicker) findViewById(R.id.timeInput);
                    timePicker1.setIs24HourView(true);
                    hour_T_1 = timePicker1.getCurrentHour();
                    min_T_1 = timePicker1.getCurrentMinute();


                    if (hour_T_1 <= 9) {
                        hh1 = "0" + hour_T_1;
                    } else {
                        hh1 = String.valueOf(hour_T_1);
                    }
                    if (min_T_1 <= 9) {
                        mm1 = "0" + min_T_1;
                    } else {
                        mm1 = String.valueOf(min_T_1);
                    }

                    String Time_1 = hh1 + ":" + mm1;
                    mySwitch_1.setText(Time_1);

                    for (int k = 0; k <= 3; k++) {
                        if (hourTime[k] == hour_T_1 && minTime[k] == min_T_1) {

                            AlertDialog alertDialog = new AlertDialog.Builder(Notification.this).create();
                            alertDialog.setTitle("ใส่เวลาซ้ำ");
                            alertDialog.setMessage("กรุณาใส่เวลาใหม่อีกครั้งค่ะ");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                            hour_T_1 = 25;
                            min_T_1 = 65;
                            cShow1 = 1;
                            button_1.setVisibility(View.GONE);
                            mySwitch_1.setVisibility(View.GONE);
                            hourTime[0] = 70;
                            minTime[0] = 70;


                        }

                    }

                    if (cShow1 == 0) {
                        s1 = 1;
                        hourTime[0] = hour_T_1;
                        minTime[0] = min_T_1;

                        button_1.setVisibility(View.VISIBLE);
                        mySwitch_1.setVisibility(View.VISIBLE);
                        count++;
                    }
                    cShow1 = 0;


                } else if (s2 == 0) {

                    TimePicker timePicker2 = (TimePicker) findViewById(R.id.timeInput);
                    timePicker2.setIs24HourView(true);
                    hour_T_2 = timePicker2.getCurrentHour();
                    min_T_2 = timePicker2.getCurrentMinute();

                    if (hour_T_2 <= 9) {
                        hh2 = "0" + hour_T_2;
                    } else {
                        hh2 = String.valueOf(hour_T_2);
                    }
                    if (min_T_2 <= 9) {
                        mm2 = "0" + min_T_2;
                    } else {
                        mm2 = String.valueOf(min_T_2);
                    }

                    String Time_2 = hh2 + ":" + mm2;

                    mySwitch_2.setText(Time_2);

                    for (int k = 0; k <= 3; k++) {
                        if (hourTime[k] == hour_T_2 && minTime[k] == min_T_2) {

                            AlertDialog alertDialog = new AlertDialog.Builder(Notification.this).create();
                            alertDialog.setTitle("ใส่เวลาซ้ำ");
                            alertDialog.setMessage("กรุณาใส่เวลาใหม่อีกครั้งค่ะ");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                            hour_T_2 = 26;
                            min_T_2 = 66;
                            cShow2 = 1;
                            button_2.setVisibility(View.GONE);
                            mySwitch_2.setVisibility(View.GONE);


                        }

                    }

                    if (cShow2 == 0) {
                        s2 = 1;
                        hourTime[1] = hour_T_2;
                        minTime[1] = min_T_2;

                        button_2.setVisibility(View.VISIBLE);
                        mySwitch_2.setVisibility(View.VISIBLE);
                        count++;
                    }

                    cShow2 = 0;
                } else if (s3 == 0) {

                    TimePicker timePicker3 = (TimePicker) findViewById(R.id.timeInput);
                    timePicker3.setIs24HourView(true);
                    hour_T_3 = timePicker3.getCurrentHour();
                    min_T_3 = timePicker3.getCurrentMinute();

                    if (hour_T_3 <= 9) {
                        hh3 = "0" + hour_T_3;
                    } else {
                        hh3 = String.valueOf(hour_T_3);
                    }
                    if (min_T_3 <= 9) {
                        mm3 = "0" + min_T_3;
                    } else {
                        mm3 = String.valueOf(min_T_3);
                    }

                    String Time_3 = hh3 + ":" + mm3;
                    mySwitch_3.setText(Time_3);

                    for (int k = 0; k <= 3; k++) {
                        if (hourTime[k] == hour_T_3 && minTime[k] == min_T_3) {

                            AlertDialog alertDialog = new AlertDialog.Builder(Notification.this).create();
                            alertDialog.setTitle("ใส่เวลาซ้ำ");
                            alertDialog.setMessage("กรุณาใส่เวลาใหม่อีกครั้งค่ะ");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                            hour_T_3 = 27;
                            min_T_3 = 67;
                            cShow3 = 1;
                            button_3.setVisibility(View.GONE);
                            mySwitch_3.setVisibility(View.GONE);


                        }

                    }

                    if (cShow3 == 0) {
                        s3 = 1;
                        hourTime[2] = hour_T_3;
                        minTime[2] = min_T_3;

                        button_3.setVisibility(View.VISIBLE);
                        mySwitch_3.setVisibility(View.VISIBLE);
                        count++;
                    }


                    cShow3 = 0;


                } else if (s4 == 0) {

                    TimePicker timePicker4 = (TimePicker) findViewById(R.id.timeInput);
                    timePicker4.setIs24HourView(true);
                    hour_T_4 = timePicker4.getCurrentHour();
                    min_T_4 = timePicker4.getCurrentMinute();
                    if (hour_T_4 <= 9) {
                        hh4 = "0" + hour_T_4;
                    } else {
                        hh4 = String.valueOf(hour_T_4);
                    }
                    if (min_T_4 <= 9) {
                        mm4 = "0" + min_T_4;
                    } else {
                        mm4 = String.valueOf(min_T_4);
                    }

                    String Time_4 = hh4 + ":" + mm4;
                    mySwitch_4.setText(Time_4);

                    for (int k = 0; k <= 3; k++) {
                        if (hourTime[k] == hour_T_4 && minTime[k] == min_T_4) {

                            AlertDialog alertDialog = new AlertDialog.Builder(Notification.this).create();
                            alertDialog.setTitle("ใส่เวลาซ้ำ");
                            alertDialog.setMessage("กรุณาใส่เวลาใหม่อีกครั้งค่ะ");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                            hour_T_4 = 28;
                            min_T_4 = 68;
                            cShow4 = 1;
                            button_4.setVisibility(View.GONE);
                            mySwitch_4.setVisibility(View.GONE);


                        }

                    }

                    if (cShow4 == 0) {
                        s1 = 1;
                        hourTime[3] = hour_T_4;
                        minTime[3] = min_T_4;

                        button_4.setVisibility(View.VISIBLE);
                        mySwitch_4.setVisibility(View.VISIBLE);
                        count++;
                    }

                    cShow4 = 0;


                }


            }

        });


        button_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s1 = 0;
                mySwitch_1.setVisibility(View.GONE);
                button_1.setVisibility(View.GONE);
                count--;
                cShow1 = 0;
                hourTime[0] = 70;
                minTime[0] = 70;

                send_1=0;
                mySwitch_1.setChecked(false);


            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s2 = 0;
                mySwitch_2.setVisibility(View.GONE);
                button_2.setVisibility(View.GONE);
                count--;
                cShow2 = 0;
                hourTime[1] = 71;
                minTime[1] = 71;

                send_2=0;
                mySwitch_2.setChecked(false);


            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s3 = 0;
                mySwitch_3.setVisibility(View.GONE);
                button_3.setVisibility(View.GONE);
                count--;
                cShow3 = 0;
                hourTime[2] = 72;
                minTime[2] = 72;

                send_3=0;
                mySwitch_3.setChecked(false);


            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s4 = 0;
                mySwitch_4.setVisibility(View.GONE);
                button_4.setVisibility(View.GONE);
                count--;
                cShow4 = 0;
                hourTime[3] = 73;
                minTime[3] = 73;

                send_4=0;
                mySwitch_4.setChecked(false);


            }
        });


        mySwitch_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    send_1=1;
                }else{
                    send_1=0;
                }



            }
        });


        mySwitch_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    send_2=1;
                }else{
                    send_2=0;
                }

            }
        });

        mySwitch_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    send_3=1;
                }else{
                    send_3=0;
                }

            }
        });


        mySwitch_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    send_4=1;
                }else{
                    send_4=0;
                }

            }
        });


    }


    ////////////////////
    TimerTask timer = new TimerTask() {
        @Override
        public void run() {
            Calendar mm = Calendar.getInstance();
            minutes = mm.get(Calendar.MINUTE);
            hour = mm.get(Calendar.HOUR_OF_DAY);

            if(send_1==1&&s1==1){
                check(hour,hour_T_1,minutes,min_T_1);
            }
            if(send_2==1&&s2==1){
                check(hour,hour_T_2,minutes,min_T_2);
            }
            if(send_3==1&&s3==1) {
                check(hour,hour_T_3,minutes, min_T_3);
            }
            if(send_4==1&&s4==1){
                check(hour,hour_T_4,minutes,min_T_4);
            }


        }

    };

    public void check(int hour, int no_h, int minutes, int no_m) {

        if (hour == no_h && minutes == no_m) {
            showNotification();
        }

    }

    public void showNotification() {

        Intent intent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        android.app.Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Money Money")
                .setContentText("มากรอกข้อมูลกันเถอะ วันนี้ใช้จ่ายไปกี่บาทแล้ว:)")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);


    }
}
