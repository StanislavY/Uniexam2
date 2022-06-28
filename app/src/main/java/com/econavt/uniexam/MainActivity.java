package com.econavt.uniexam;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    public static final String BROADCAST_ACTION = "com.econavt.uniexam";
    public static String PARAM_BUF = "";
    public static final String SHARED_PREFS_NAME = "sett.xml";
    public String CommandString;
    LinearLayout LLBut3;
    boolean M_SOUND;
    String SERVER_ADR = "192.168.0.60";
    String SERVER_PORT = "5556";
    public boolean STARTING = false;
    int WORK_HEIGTH;
    String WORK_NAME = "Учащийся ";
    public String WORK_NUM = "1";
    Button bExit;
    Button bHelp;
    Button bL_Data;
    Button bSet;
    Button bStart;
    boolean bound = false;

    /* renamed from: br */
    BroadcastReceiver f3br;
    byte[] buffer;
    Intent intent;
//    ExamService2 myService;
    ExamServiceRX myService;
    int pauza_t = 5;
    ServiceConnection sConn;
    SharedPreferences sPref;
    TextView tN_Tem;
    TextView tN_Ticket;
    TextView tN_Vopr;
    TextView tName;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bL_Data = (Button) findViewById(R.id.bL_Data);
        this.bSet = (Button) findViewById(R.id.bSet);
        this.bHelp = (Button) findViewById(R.id.bHelp);
        this.bStart = (Button) findViewById(R.id.bStart);
        this.bExit = (Button) findViewById(R.id.bExit);
        this.tName = (TextView) findViewById(R.id.tName);
        this.tN_Ticket = (TextView) findViewById(R.id.tN_Ticket);
        this.tN_Vopr = (TextView) findViewById(R.id.tN_Vopr);
        this.tN_Tem = (TextView) findViewById(R.id.tN_Tem);
        this.LLBut3 = (LinearLayout) findViewById(R.id.LLBut3);
        this.STARTING = false;
        View.OnClickListener oclBtn = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bL_Data:
                        MainActivity.this.Sett_1();
                        return;
                    case R.id.bSet:
                        MainActivity.this.Sett_2();
                        return;
                    case R.id.bHelp:
                        MainActivity.this.Browse(4);
                        return;
                    case R.id.bStart:
                        MainActivity.this.StartStop();
                        return;
                    case R.id.bExit:
                        Process.killProcess(Process.myPid());
                        return;
                    default:
                        return;
                }
            }
        };
        this.bStart.setOnClickListener(oclBtn);
        this.bL_Data.setOnClickListener(oclBtn);
        this.bSet.setOnClickListener(oclBtn);
        this.bHelp.setOnClickListener(oclBtn);
        this.bExit.setOnClickListener(oclBtn);
        if (!this.STARTING) {
            this.f3br = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    byte[] byteArray = intent.getByteArrayExtra(MainActivity.PARAM_BUF);
                    if (byteArray[0] == 61) {
                        MainActivity.this.CommandString = new String(byteArray);
                        MainActivity.this.set_comand(MainActivity.this.CommandString);
                    }
                }
            };
            registerReceiver(this.f3br, new IntentFilter("com.econavt.uniexam"));
        }
        this.intent = new Intent(this, ExamServiceRX.class);
        this.sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                if (!MainActivity.this.STARTING) {
                    MainActivity.this.myService = ((ExamServiceRX.MyBinder) binder).getService();
                    MainActivity.this.bound = true;
                }
            }

            public void onServiceDisconnected(ComponentName name) {
                MainActivity.this.bound = false;
            }
        };
        startService(this.intent);
        this.sPref = getSharedPreferences("sett.xml", 0);
        this.WORK_NUM = this.sPref.getString("w_numer", "1");
        this.WORK_NAME = this.sPref.getString("w_name", "Учащийся " + this.WORK_NUM);
        this.SERVER_ADR = this.sPref.getString("w_address", "192.168.0.61");
        this.SERVER_PORT = this.sPref.getString("w_port", "5556");
        this.M_SOUND = this.sPref.getBoolean("m_sound", true);
        this.pauza_t = this.sPref.getInt("pauza_t", 5);
        this.WORK_HEIGTH = this.sPref.getInt("FontSize", 10);
        this.bL_Data.setTextSize((float) this.WORK_HEIGTH);
        this.bSet.setTextSize((float) this.WORK_HEIGTH);
        this.bHelp.setTextSize((float) this.WORK_HEIGTH);
        this.bStart.setTextSize((float) this.WORK_HEIGTH);
        this.bExit.setTextSize((float) this.WORK_HEIGTH);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        bindService(this.intent, this.sConn, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.sPref = getSharedPreferences("sett.xml", 0);
        this.WORK_NUM = this.sPref.getString("w_numer", "1");
        this.WORK_NAME = this.sPref.getString("w_name", "Учащийся " + this.WORK_NUM);
        this.SERVER_ADR = this.sPref.getString("w_address", "192.168.0.61");
        this.SERVER_PORT = this.sPref.getString("w_port", "5556");
        this.M_SOUND = this.sPref.getBoolean("m_sound", true);
        this.pauza_t = this.sPref.getInt("pauza_t", 5);
        this.WORK_HEIGTH = this.sPref.getInt("FontSize", 10);
        this.bL_Data.setTextSize((float) this.WORK_HEIGTH);
        this.bSet.setTextSize((float) this.WORK_HEIGTH);
        this.bHelp.setTextSize((float) this.WORK_HEIGTH);
        this.bStart.setTextSize((float) this.WORK_HEIGTH);
        this.bExit.setTextSize((float) this.WORK_HEIGTH);
    }

    public void set_timer() {
        final Timer tmr = new Timer();
        final Handler uiHandler = new Handler();
        tmr.schedule(new TimerTask() {
            public void run() {
                Handler handler = uiHandler;
                final Timer timer = tmr;
                handler.post(() -> {
                    MainActivity.this.setNumName();
                    timer.cancel();
                });
            }
        }, 300);
    }

    public void setNumName() {
//        this.myService.doSend("#" + this.WORK_NAME + "#" + this.WORK_NUM);
        this.myService.doSendRX("#" + this.WORK_NAME + "#" + this.WORK_NUM);
    }

    public void set_comand(String str) {
        String Comm = str;
        byte[] buffer2 = str.getBytes();
        if (buffer2[0] == 61) {
            switch (buffer2[1]) {
                case 49:
                    String[] fields_main = Comm.split("#");
                    this.tName.setText(fields_main[1]);
                    this.tN_Ticket.setText(fields_main[2]);
                    this.tN_Vopr.setText(fields_main[3]);
                    this.tN_Tem.setText(fields_main[4]);
                    return;
                case 50:
                    Browse(1);
                    return;
                case 51:
                    Browse(2);
                    return;
                case 52:
                    Browse(3);
                    return;
                default:
                    return;
            }
        }
    }

    public void Browse(int sel) {
        switch (sel) {
            case 1:
                startActivity(new Intent(this, browser.class));
                return;
            case 2:
                startActivity(new Intent(this, etic.class));
                return;
            case 3:
                startActivity(new Intent(this, etema.class));
                return;
            case 4:
                startActivity(new Intent(this, help.class));
                return;
            default:
                return;
        }
    }

    public void Sett_1() {
        startActivity(new Intent(this, sett.class));
    }

    public void Sett_2() {
        startActivity(new Intent(this, sett2.class));
    }

    public void StartStop() {
        if (this.STARTING) {
            this.myService.doSend("RX");
            unbindService(this.sConn);
            this.bound = false;
            this.myService.stop_service();
            super.onStop();
            Process.killProcess(Process.myPid());
            return;
        }
        this.myService.doSend(this.SERVER_ADR + "#" + this.SERVER_PORT + "#" + this.WORK_NAME);
        for (int i = 1000; i > 0; i--) {
            this.tName.setText("wremennyi Text 1234567890 1234567890");
            this.tName.setText("");
        }
        this.tName.setText("");
        this.myService.connect();
        int T_Time = 35000;
        while (T_Time != 0) {
            T_Time--;
            this.tName.setText("wremennyi Text 1234567890 1234567890");
            this.tName.setText("");
            if (this.myService.connect_yes) {
                break;
            }
        }
        this.tName.setText("");
        if (!this.myService.connect_yes) {
            Toast.makeText(this, "Неудачное подключение. Попробуйте ещё раз.", Toast.LENGTH_SHORT).show();
            return;
        }
        this.myService.schedule();
        for (int i2 = 600; i2 > 0; i2--) {
            this.tName.setText("wremennyi Text 1234567890 1234567890");
            this.tName.setText("");
        }
        this.tName.setText("");
        this.bStart.setText("Выход");
        this.STARTING = true;
        set_timer();
        this.bExit.setVisibility(View.INVISIBLE);
        this.bExit.setText("");
        this.bExit.setWidth(2);
        this.LLBut3.invalidate();
    }
}
