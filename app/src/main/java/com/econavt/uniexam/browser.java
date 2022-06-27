package com.econavt.uniexam;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
//import android.support.p000v4.view.MotionEventCompat;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.econavt.uniexam.ExamService2;
import java.util.Timer;
import java.util.TimerTask;

import androidx.core.view.MotionEventCompat;

public class browser extends Activity {
    public static final String BROADCAST_ACTION = "com.econavt.uniexam";
    public static String PARAM_BUF = "";
    public static final String SHARED_PREFS_NAME = "sett.xml";
    int ALL_QST;
    public int ANSWERS = 100;
    public String CommString;
    public int ERRORS = 100;
    int GreenText = -16733696;
    int HEIGHT;
    public boolean HELP_YES = true;
    int IMAGE_HEIGHT = 235;
    int KOL_VOPR_IN_TEMA = 0;
    boolean NO_SPINNER = true;
    public int NUM = 1;
    public int NUM_SELECT = 0;
    int N_QST;
    int N_TICKET;
    public int[] OTVETS;
    public String PODSKAZKA;
    public boolean STARTING = false;
    int START_WIDTH;
    public boolean Set_Im = true;
    int TEK_ALL_QST = 0;
    String TEK_HELP;
    String[] TEK_MAS_OTVET = {"", "", "", "", "", ""};
    int TEK_NUM_VAR_OTVETOV;
    String TEK_PICT;
    int TEK_PR_OTVET;
    int TEK_QST_IN_TICKET;
    String TEK_QUEST;
    int TEK_TEMA;
    int TEK_TICKET;
    int TEK_VOPR_IN_TEMA;
    int THIS_OTVET = 0;
    String TIP_EX = "1";
    int WIDTH;
    int WORK_HEIGTH;
    String WORK_NAME;
    String WORK_NUM;
    int WORK_WIDTH;
    Button bClear;
    Button bClose;
    ImageButton bNext;
    Button bOK;
    ImageButton bPrev;
    ImageButton bVopr;
    int backColor = -858993460;
    int blackColor = -16777216;
    boolean bound = false;
    BroadcastReceiver br2;
    CheckBox chTems;
    CheckBox chTick;
    CheckBox chVar1;
    CheckBox chVar2;
    CheckBox chVar3;
    CheckBox chVar4;
    CheckBox chVar5;
    CheckBox chVar6;
    public String[] data_strings;
    FrameLayout frameLayout1;
    FrameLayout frameLayout2;
    ImageButton iBuDownBrows;
    ImageButton iBuUpBrows;
    Intent intent2;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    ExamService2 myService2;
    int redColor = -5636096;
    ServiceConnection sConn2;
    SharedPreferences sPref;
    Spinner spinner1;
    TextView tAnswer;
    TextView tErrors;
    TextView tNErrors;
    TextView tNOtvets;
    TextView tNumTick;
    TextView tNumTicket;
    TextView tOtvetYes;
    TextView tOtvets;
    TextView tVar1;
    TextView tVar2;
    TextView tVar3;
    TextView tVar4;
    TextView tVar5;
    TextView tVar6;
    TextView tVariant;
    TextView tVoprMain;
    TextView tVopros;
    TextView tZap1B;
    TextView tZap2B;
    TextView tZap3B;
    TextView tZap4B;
    public Timer tmr;
    public Timer tmr5;
    ImageView vImage;
    int zeroColor = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        this.bClose = (Button) findViewById(R.id.bStart);
        this.iBuUpBrows = (ImageButton) findViewById(R.id.iBuUpBrows);
        this.iBuDownBrows = (ImageButton) findViewById(R.id.iBuDownBrows);
        this.bOK = (Button) findViewById(R.id.bOK);
        this.bClear = (Button) findViewById(R.id.bClear);
        this.bVopr = (ImageButton) findViewById(R.id.bVopr);
        this.bPrev = (ImageButton) findViewById(R.id.bPrev);
        this.bNext = (ImageButton) findViewById(R.id.bNext);
        this.spinner1 = (Spinner) findViewById(R.id.spinner1);
        this.frameLayout1 = (FrameLayout) findViewById(R.id.frameLayout1);
        this.frameLayout2 = (FrameLayout) findViewById(R.id.frameLayout2);
        this.linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        this.linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        this.tVopros = (TextView) findViewById(R.id.tVopros);
        this.tVariant = (TextView) findViewById(R.id.tVariant);
        this.tVoprMain = (TextView) findViewById(R.id.tVoprMain);
        this.tOtvets = (TextView) findViewById(R.id.tOtvets);
        this.tNOtvets = (TextView) findViewById(R.id.tNOtvets);
        this.tErrors = (TextView) findViewById(R.id.tErrors);
        this.tNErrors = (TextView) findViewById(R.id.tNErrors);
        this.tOtvetYes = (TextView) findViewById(R.id.tOtvetYes);
        this.tAnswer = (TextView) findViewById(R.id.tAnswer);
        this.tNumTicket = (TextView) findViewById(R.id.tNumTicket);
        this.tNumTick = (TextView) findViewById(R.id.tNumTick);
        this.chVar1 = (CheckBox) findViewById(R.id.chVar1);
        this.chVar2 = (CheckBox) findViewById(R.id.chVar2);
        this.chVar3 = (CheckBox) findViewById(R.id.chVar3);
        this.chVar4 = (CheckBox) findViewById(R.id.chVar4);
        this.chVar5 = (CheckBox) findViewById(R.id.chVar5);
        this.chVar6 = (CheckBox) findViewById(R.id.chVar6);
        this.chTems = (CheckBox) findViewById(R.id.chTems);
        this.chTick = (CheckBox) findViewById(R.id.chTick);
        this.vImage = (ImageView) findViewById(R.id.vImage);
        this.tVar1 = (TextView) findViewById(R.id.tVar1);
        this.tVar2 = (TextView) findViewById(R.id.tVar2);
        this.tVar3 = (TextView) findViewById(R.id.tVar3);
        this.tVar4 = (TextView) findViewById(R.id.tVar4);
        this.tVar5 = (TextView) findViewById(R.id.tVar5);
        this.tVar6 = (TextView) findViewById(R.id.tVar6);
        this.tZap1B = (TextView) findViewById(R.id.tZap1B);
        this.tZap2B = (TextView) findViewById(R.id.tZap2B);
        this.tZap3B = (TextView) findViewById(R.id.tZap3B);
        this.tZap4B = (TextView) findViewById(R.id.tZap4B);
        View.OnClickListener oclBtn = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tVoprMain:
                        browser.this.NUM = 7;
                        browser.this.setNum();
                        return;
                    case R.id.bStart:
                        browser.this.myService2.doSend("B" + browser.this.WORK_NUM + "," + "1,0,0,1");
                        browser.this.finish();
                        return;
                    case R.id.chVar5:
                        if (browser.this.chVar5.isChecked()) {
                            browser.this.onClickCheck(5);
                            return;
                        } else {
                            browser.this.chVar5.setChecked(true);
                            return;
                        }
                    case R.id.chVar4:
                        if (browser.this.chVar4.isChecked()) {
                            browser.this.onClickCheck(4);
                            return;
                        } else {
                            browser.this.chVar4.setChecked(true);
                            return;
                        }
                    case R.id.chVar6:
                        if (browser.this.chVar6.isChecked()) {
                            browser.this.onClickCheck(6);
                            return;
                        } else {
                            browser.this.chVar6.setChecked(true);
                            return;
                        }
                    case R.id.bPrev:
                        browser.this.Set_Im = true;
                        if (browser.this.TIP_EX == "1") {
                            if (browser.this.TEK_VOPR_IN_TEMA != 0) {
                                browser browser = browser.this;
                                browser.TEK_VOPR_IN_TEMA--;
                            } else {
                                return;
                            }
                        } else if (browser.this.TEK_QST_IN_TICKET != 1) {
                            browser browser2 = browser.this;
                            browser2.TEK_QST_IN_TICKET--;
                        } else if (browser.this.TEK_TICKET != 1) {
                            browser browser3 = browser.this;
                            browser3.TEK_TICKET--;
                            browser.this.TEK_QST_IN_TICKET = browser.this.N_QST;
                        } else {
                            return;
                        }
                        browser.this.setNomVopros();
                        return;
                    case R.id.bNext:
                        browser.this.Set_Im = true;
                        if (browser.this.TIP_EX == "1") {
                            if (browser.this.TEK_VOPR_IN_TEMA != browser.this.KOL_VOPR_IN_TEMA - 1) {
                                browser.this.TEK_VOPR_IN_TEMA++;
                            } else {
                                return;
                            }
                        } else if (browser.this.TEK_QST_IN_TICKET != browser.this.N_QST) {
                            browser.this.TEK_QST_IN_TICKET++;
                        } else if (browser.this.TEK_TICKET != browser.this.N_TICKET) {
                            browser.this.TEK_QST_IN_TICKET = 1;
                            browser.this.TEK_TICKET++;
                        } else {
                            return;
                        }
                        browser.this.setNomVopros();
                        return;
                    case R.id.bOK:
                        browser.this.setOK();
                        return;
                    case R.id.iBuUpBrows:
                        browser.this.scroll(10);
                        return;
                    case R.id.iBuDownBrows:
                        browser.this.scroll(-10);
                        return;
                    case R.id.chVar1:
                        if (browser.this.chVar1.isChecked()) {
                            browser.this.onClickCheck(1);
                            return;
                        } else {
                            browser.this.chVar1.setChecked(true);
                            return;
                        }
                    case R.id.bVopr:
                        if (browser.this.HELP_YES) {
                            browser.this.setPodskazka();
                            return;
                        }
                        return;
                    case R.id.chTems:
                        if (browser.this.chTems.isChecked()) {
                            browser.this.onClickCheck(7);
                            return;
                        } else {
                            browser.this.chTems.setChecked(true);
                            return;
                        }
                    case R.id.chTick:
                        if (browser.this.chTick.isChecked()) {
                            browser.this.onClickCheck(8);
                            return;
                        } else {
                            browser.this.chTick.setChecked(true);
                            return;
                        }
                    case R.id.bClear:
                        browser.this.ErrClear();
                        return;
                    case R.id.chVar2:
                        if (browser.this.chVar2.isChecked()) {
                            browser.this.onClickCheck(2);
                            return;
                        } else {
                            browser.this.chVar2.setChecked(true);
                            return;
                        }
                    case R.id.chVar3:
                        if (browser.this.chVar3.isChecked()) {
                            browser.this.onClickCheck(3);
                            return;
                        } else {
                            browser.this.chVar3.setChecked(true);
                            return;
                        }
                    case R.id.tVar1:
                        browser.this.NUM = 1;
                        browser.this.setNum();
                        return;
                    case R.id.tVar3:
                        browser.this.NUM = 3;
                        browser.this.setNum();
                        return;
                    case R.id.tVar5:
                        browser.this.NUM = 5;
                        browser.this.setNum();
                        return;
                    case R.id.tVar4:
                        browser.this.NUM = 4;
                        browser.this.setNum();
                        return;
                    case R.id.tVar6:
                        browser.this.NUM = 6;
                        browser.this.setNum();
                        return;
                    case R.id.tVar2:
                        browser.this.NUM = 2;
                        browser.this.setNum();
                        return;
                    case R.id.vImage:
                        browser.this.click_im();
                        return;
                    default:
                        return;
                }
            }
        };
        this.bClose.setOnClickListener(oclBtn);
        this.iBuUpBrows.setOnClickListener(oclBtn);
        this.iBuDownBrows.setOnClickListener(oclBtn);
        this.bOK.setOnClickListener(oclBtn);
        this.bClear.setOnClickListener(oclBtn);
        this.bVopr.setOnClickListener(oclBtn);
        this.bPrev.setOnClickListener(oclBtn);
        this.bNext.setOnClickListener(oclBtn);
        this.chTems.setOnClickListener(oclBtn);
        this.chTick.setOnClickListener(oclBtn);
        this.tVoprMain.setOnClickListener(oclBtn);
        this.tVar1.setOnClickListener(oclBtn);
        this.tVar2.setOnClickListener(oclBtn);
        this.tVar3.setOnClickListener(oclBtn);
        this.tVar4.setOnClickListener(oclBtn);
        this.tVar5.setOnClickListener(oclBtn);
        this.tVar6.setOnClickListener(oclBtn);
        this.chVar1.setOnClickListener(oclBtn);
        this.chVar2.setOnClickListener(oclBtn);
        this.chVar3.setOnClickListener(oclBtn);
        this.chVar4.setOnClickListener(oclBtn);
        this.chVar5.setOnClickListener(oclBtn);
        this.chVar6.setOnClickListener(oclBtn);
        this.vImage.setOnClickListener(oclBtn);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.WIDTH = size.x;
        this.HEIGHT = size.y;
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().hide();
        this.sPref = getSharedPreferences("sett.xml", 0);
        this.WORK_NUM = this.sPref.getString("w_numer", "1");
        this.WORK_NAME = this.sPref.getString("w_name", "Учащийся " + this.WORK_NUM);
        this.WORK_HEIGTH = this.sPref.getInt("FontSize", 10);
        this.WORK_HEIGTH -= 2;
        this.IMAGE_HEIGHT = this.sPref.getInt("ImageHeight", 235);
        set_timer1();
        if (!this.STARTING) {
            this.br2 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    byte[] byteArray = intent.getByteArrayExtra(MainActivity.PARAM_BUF);
                    if (byteArray[0] == 45) {
                        browser.this.CommString = new String(byteArray);
                        browser.this.set_comand(browser.this.CommString);
                    } else if (byteArray[0] != 61) {
                        Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                        browser.this.vImage.setBackgroundColor(browser.this.zeroColor);
                        browser.this.vImage.setImageBitmap(bit);
                    }
                }
            };
            registerReceiver(this.br2, new IntentFilter("com.econavt.uniexam"));
        }
        this.intent2 = new Intent(this, ExamService2.class);
        this.sConn2 = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                if (!browser.this.STARTING) {
                    browser.this.myService2 = ((ExamService2.MyBinder) binder).getService();
                    browser.this.bound = true;
                    browser.this.myService2.doSend("B" + browser.this.WORK_NUM + ",0,0,0,1");
                }
            }

            public void onServiceDisconnected(ComponentName name) {
                browser.this.bound = false;
            }
        };
        this.tVoprMain.setTextSize((float) this.WORK_HEIGTH);
        this.tVar1.setTextSize((float) this.WORK_HEIGTH);
        this.tVar2.setTextSize((float) this.WORK_HEIGTH);
        this.tVar3.setTextSize((float) this.WORK_HEIGTH);
        this.tVar4.setTextSize((float) this.WORK_HEIGTH);
        this.tVar5.setTextSize((float) this.WORK_HEIGTH);
        this.tVar6.setTextSize((float) this.WORK_HEIGTH);
        this.chTems.setTextSize((float) this.WORK_HEIGTH);
        this.chTick.setTextSize((float) this.WORK_HEIGTH);
        this.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                browser.this.spinner_change(position);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void click_im() {
        int WrY;
        ViewGroup.LayoutParams params = this.vImage.getLayoutParams();
        if (this.Set_Im) {
            this.Set_Im = false;
            WrY = this.HEIGHT;
        } else {
            this.Set_Im = true;
            WrY = this.IMAGE_HEIGHT;
        }
        params.height = WrY;
        params.width = (int) (((double) WrY) * 1.5d);
        this.vImage.setLayoutParams(params);
        this.vImage.bringToFront();
    }

    /* access modifiers changed from: package-private */
    public void spinner_change(int poz) {
        if (this.NO_SPINNER) {
            this.NO_SPINNER = false;
            return;
        }
        this.NUM_SELECT = poz;
        this.myService2.doSend("T" + Integer.toString(this.NUM_SELECT) + ",0");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        bindService(this.intent2, this.sConn2, 0);
        this.NO_SPINNER = true;
    }

    public void set_timer() {
        final Timer tmr2 = new Timer();
        final Handler uiHandler = new Handler();
        tmr2.schedule(new TimerTask() {
            public void run() {
                Handler handler = uiHandler;
                final Timer timer = tmr2;
                handler.post(new Runnable() {
                    public void run() {
                        timer.cancel();
                    }
                });
            }
        }, 400);
    }

    /* access modifiers changed from: package-private */
    public void Clear_Otvets() {
        int cnt = this.OTVETS.length;
        for (int i = 0; i < cnt; i++) {
            this.OTVETS[i] = 0;
        }
    }

    public void set_comand(String str) {
        String Comm = str;
        byte[] buffer = str.getBytes();
        if (buffer[0] == 45) {
            switch (buffer[1]) {
                case 49:
                    String[] fields_main = Comm.split("#");
                    this.ALL_QST = Integer.parseInt(fields_main[1]);
                    setList(fields_main[2]);
                    this.OTVETS = new int[this.ALL_QST];
                    Clear_Otvets();
                    if (this.TIP_EX == "1") {
                        this.tNumTicket.setVisibility(4);
                        this.tNumTick.setVisibility(4);
                        this.myService2.doSend("T" + Integer.toString(this.NUM_SELECT) + ",0");
                        return;
                    }
                    this.tNumTicket.setVisibility(0);
                    this.tNumTick.setVisibility(0);
                    this.myService2.doSend("K0,0");
                    return;
                case 50:
                    setVoprOtv(Comm);
                    if (this.TEK_PICT.length() == 0) {
                        this.vImage.setImageBitmap((Bitmap) null);
                        this.vImage.setBackgroundColor(this.backColor);
                        return;
                    } else if (this.TIP_EX == "1") {
                        this.myService2.doSend("B" + this.WORK_NUM + ",3," + Integer.toString(this.TEK_TEMA) + "," + Integer.toString(this.TEK_VOPR_IN_TEMA) + ",1");
                        return;
                    } else {
                        this.myService2.doSend("B" + this.WORK_NUM + ",3," + Integer.toString(this.TEK_TICKET) + "," + Integer.toString(this.TEK_QST_IN_TICKET) + ",1");
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x00e5, code lost:
        r11.tOtvetYes.setText(java.lang.String.valueOf(java.lang.Integer.toString(r11.TEK_VOPR_IN_TEMA + 1)) + " из " + java.lang.Integer.toString(r11.KOL_VOPR_IN_TEMA));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0115, code lost:
        switch(r11.OTVETS[r11.TEK_ALL_QST]) {
            case 1: goto L_0x01d4;
            case 5: goto L_0x01dd;
            default: goto L_0x0118;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0118, code lost:
        r11.tOtvetYes.setTextColor(r11.blackColor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x011f, code lost:
        r11.tNumTicket.setText(java.lang.String.valueOf(java.lang.Integer.toString(r11.TEK_TICKET)) + " из " + java.lang.Integer.toString(r11.N_TICKET));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0147, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x016e, code lost:
        r11.tVar5.setVisibility(0);
        r11.chVar5.setVisibility(0);
        r11.tVar5.setText(r11.TEK_MAS_OTVET[4]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0181, code lost:
        r11.tVar4.setVisibility(0);
        r11.chVar4.setVisibility(0);
        r11.tVar4.setText(r11.TEK_MAS_OTVET[3]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0194, code lost:
        r11.tVar3.setVisibility(0);
        r11.chVar3.setVisibility(0);
        r11.tVar3.setText(r11.TEK_MAS_OTVET[2]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x01aa, code lost:
        r11.tOtvetYes.setText(java.lang.String.valueOf(java.lang.Integer.toString(r11.TEK_QST_IN_TICKET)) + " из " + java.lang.Integer.toString(r11.N_QST));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x01d4, code lost:
        r11.tOtvetYes.setTextColor(r11.redColor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x01dd, code lost:
        r11.tOtvetYes.setTextColor(r11.GreenText);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x00cd, code lost:
        r11.tVar2.setText(r11.TEK_MAS_OTVET[1]);
        r11.tVar1.setText(r11.TEK_MAS_OTVET[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x00e3, code lost:
        if (r11.TIP_EX != "1") goto L_0x01aa;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setVoprOtv(java.lang.String r12) {
        /*
            r11 = this;
            r10 = 5
            r9 = 3
            r8 = 1
            r6 = 4
            r7 = 0
            java.lang.String r0 = r12.substring(r9)
            java.lang.String r4 = "#"
            java.lang.String[] r1 = r0.split(r4)
            r4 = r1[r7]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.N_TICKET = r4
            r4 = r1[r8]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.N_QST = r4
            r4 = r1[r9]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.TEK_ALL_QST = r4
            r4 = r1[r6]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.TEK_TICKET = r4
            r4 = r1[r10]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.TEK_QST_IN_TICKET = r4
            r4 = 6
            r4 = r1[r4]
            r11.TEK_QUEST = r4
            r4 = 7
            r4 = r1[r4]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.TEK_PR_OTVET = r4
            r4 = 8
            r4 = r1[r4]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.TEK_TEMA = r4
            r4 = 9
            r4 = r1[r4]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.TEK_VOPR_IN_TEMA = r4
            r4 = 10
            r4 = r1[r4]
            r11.TEK_PICT = r4
            r4 = 11
            r4 = r1[r4]
            r11.TEK_HELP = r4
            r4 = 12
            r4 = r1[r4]
            r11.PODSKAZKA = r4
            java.lang.String r4 = r11.PODSKAZKA
            int r4 = r4.length()
            if (r4 != 0) goto L_0x0148
            android.widget.ImageButton r4 = r11.bVopr
            r4.setEnabled(r7)
        L_0x0078:
            r4 = 13
            r4 = r1[r4]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.KOL_VOPR_IN_TEMA = r4
            r4 = 14
            r4 = r1[r4]
            java.lang.String r5 = "@"
            java.lang.String[] r2 = r4.split(r5)
            r4 = r2[r7]
            int r4 = java.lang.Integer.parseInt(r4)
            r11.TEK_NUM_VAR_OTVETOV = r4
            r3 = 0
        L_0x0095:
            int r4 = r11.TEK_NUM_VAR_OTVETOV
            if (r3 < r4) goto L_0x014f
            android.widget.TextView r4 = r11.tVar3
            r4.setVisibility(r6)
            android.widget.TextView r4 = r11.tVar4
            r4.setVisibility(r6)
            android.widget.TextView r4 = r11.tVar5
            r4.setVisibility(r6)
            android.widget.TextView r4 = r11.tVar6
            r4.setVisibility(r6)
            android.widget.CheckBox r4 = r11.chVar3
            r4.setVisibility(r6)
            android.widget.CheckBox r4 = r11.chVar4
            r4.setVisibility(r6)
            android.widget.CheckBox r4 = r11.chVar5
            r4.setVisibility(r6)
            android.widget.CheckBox r4 = r11.chVar6
            r4.setVisibility(r6)
            android.widget.TextView r4 = r11.tVoprMain
            java.lang.String r5 = r11.TEK_QUEST
            r4.setText(r5)
            int r4 = r11.TEK_NUM_VAR_OTVETOV
            switch(r4) {
                case 3: goto L_0x0194;
                case 4: goto L_0x0181;
                case 5: goto L_0x016e;
                case 6: goto L_0x015b;
                default: goto L_0x00cd;
            }
        L_0x00cd:
            android.widget.TextView r4 = r11.tVar2
            java.lang.String[] r5 = r11.TEK_MAS_OTVET
            r5 = r5[r8]
            r4.setText(r5)
            android.widget.TextView r4 = r11.tVar1
            java.lang.String[] r5 = r11.TEK_MAS_OTVET
            r5 = r5[r7]
            r4.setText(r5)
            java.lang.String r4 = r11.TIP_EX
            java.lang.String r5 = "1"
            if (r4 != r5) goto L_0x01aa
            android.widget.TextView r4 = r11.tOtvetYes
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r6 = r11.TEK_VOPR_IN_TEMA
            int r6 = r6 + 1
            java.lang.String r6 = java.lang.Integer.toString(r6)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r5.<init>(r6)
            java.lang.String r6 = " из "
            java.lang.StringBuilder r5 = r5.append(r6)
            int r6 = r11.KOL_VOPR_IN_TEMA
            java.lang.String r6 = java.lang.Integer.toString(r6)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.setText(r5)
        L_0x010f:
            int[] r4 = r11.OTVETS
            int r5 = r11.TEK_ALL_QST
            r4 = r4[r5]
            switch(r4) {
                case 1: goto L_0x01d4;
                case 5: goto L_0x01dd;
                default: goto L_0x0118;
            }
        L_0x0118:
            android.widget.TextView r4 = r11.tOtvetYes
            int r5 = r11.blackColor
            r4.setTextColor(r5)
        L_0x011f:
            android.widget.TextView r4 = r11.tNumTicket
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r6 = r11.TEK_TICKET
            java.lang.String r6 = java.lang.Integer.toString(r6)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r5.<init>(r6)
            java.lang.String r6 = " из "
            java.lang.StringBuilder r5 = r5.append(r6)
            int r6 = r11.N_TICKET
            java.lang.String r6 = java.lang.Integer.toString(r6)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.setText(r5)
            return
        L_0x0148:
            android.widget.ImageButton r4 = r11.bVopr
            r4.setEnabled(r8)
            goto L_0x0078
        L_0x014f:
            java.lang.String[] r4 = r11.TEK_MAS_OTVET
            int r5 = r3 + 1
            r5 = r2[r5]
            r4[r3] = r5
            int r3 = r3 + 1
            goto L_0x0095
        L_0x015b:
            android.widget.TextView r4 = r11.tVar6
            r4.setVisibility(r7)
            android.widget.CheckBox r4 = r11.chVar6
            r4.setVisibility(r7)
            android.widget.TextView r4 = r11.tVar6
            java.lang.String[] r5 = r11.TEK_MAS_OTVET
            r5 = r5[r10]
            r4.setText(r5)
        L_0x016e:
            android.widget.TextView r4 = r11.tVar5
            r4.setVisibility(r7)
            android.widget.CheckBox r4 = r11.chVar5
            r4.setVisibility(r7)
            android.widget.TextView r4 = r11.tVar5
            java.lang.String[] r5 = r11.TEK_MAS_OTVET
            r5 = r5[r6]
            r4.setText(r5)
        L_0x0181:
            android.widget.TextView r4 = r11.tVar4
            r4.setVisibility(r7)
            android.widget.CheckBox r4 = r11.chVar4
            r4.setVisibility(r7)
            android.widget.TextView r4 = r11.tVar4
            java.lang.String[] r5 = r11.TEK_MAS_OTVET
            r5 = r5[r9]
            r4.setText(r5)
        L_0x0194:
            android.widget.TextView r4 = r11.tVar3
            r4.setVisibility(r7)
            android.widget.CheckBox r4 = r11.chVar3
            r4.setVisibility(r7)
            android.widget.TextView r4 = r11.tVar3
            java.lang.String[] r5 = r11.TEK_MAS_OTVET
            r6 = 2
            r5 = r5[r6]
            r4.setText(r5)
            goto L_0x00cd
        L_0x01aa:
            android.widget.TextView r4 = r11.tOtvetYes
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r6 = r11.TEK_QST_IN_TICKET
            java.lang.String r6 = java.lang.Integer.toString(r6)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r5.<init>(r6)
            java.lang.String r6 = " из "
            java.lang.StringBuilder r5 = r5.append(r6)
            int r6 = r11.N_QST
            java.lang.String r6 = java.lang.Integer.toString(r6)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.setText(r5)
            goto L_0x010f
        L_0x01d4:
            android.widget.TextView r4 = r11.tOtvetYes
            int r5 = r11.redColor
            r4.setTextColor(r5)
            goto L_0x011f
        L_0x01dd:
            android.widget.TextView r4 = r11.tOtvetYes
            int r5 = r11.GreenText
            r4.setTextColor(r5)
            goto L_0x011f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.econavt.uniexam.browser.setVoprOtv(java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public void setList(String str2) {
        String[] mas = str2.split("@");
        int cnt_mas = Integer.parseInt(mas[0]);
        String WrStr2 = mas[1];
        for (int i = 2; i <= cnt_mas; i++) {
            WrStr2 = String.valueOf(String.valueOf(WrStr2) + "@") + mas[i];
        }
        this.data_strings = WrStr2.split("@");
        this.NUM_SELECT = 0;
        this.spinner1.setSelection(this.NUM_SELECT);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 17367048, this.data_strings);
        adapter.setDropDownViewResource(17367049);
        this.spinner1.setAdapter(adapter);
    }

    /* access modifiers changed from: package-private */
    public boolean checkYes() {
        if (this.chVar1.isChecked()) {
            return true;
        }
        if (this.chVar2.isChecked()) {
            return true;
        }
        if (this.chVar3.isChecked()) {
            return true;
        }
        if (this.chVar4.isChecked()) {
            return true;
        }
        if (this.chVar5.isChecked()) {
            return true;
        }
        if (this.chVar6.isChecked()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setOK() {
        if (checkYes()) {
            clearSelect();
            if (this.OTVETS[this.TEK_ALL_QST] > 0) {
                Toast.makeText(this, "Ответ уже получен.", 0).show();
                return;
            }
            this.ANSWERS++;
            this.tNOtvets.setText(Integer.toString(this.ANSWERS));
            if (this.TEK_PR_OTVET == this.THIS_OTVET) {
                this.OTVETS[this.TEK_ALL_QST] = 5;
            } else {
                this.OTVETS[this.TEK_ALL_QST] = 1;
                this.ERRORS++;
                this.tNErrors.setText(Integer.toString(this.ERRORS));
            }
            if (this.TIP_EX != "1") {
                if (this.TEK_QST_IN_TICKET != this.N_QST) {
                    this.TEK_QST_IN_TICKET++;
                } else if (this.TEK_TICKET != this.N_TICKET) {
                    this.TEK_QST_IN_TICKET = 1;
                    this.TEK_TICKET++;
                } else {
                    return;
                }
                this.myService2.doSend("K" + Integer.toString(this.TEK_TICKET) + "," + Integer.toString(this.TEK_QST_IN_TICKET));
            } else if (this.TEK_VOPR_IN_TEMA < this.KOL_VOPR_IN_TEMA - 1) {
                this.TEK_VOPR_IN_TEMA++;
                this.myService2.doSend("T" + Integer.toString(this.NUM_SELECT) + "," + Integer.toString(this.TEK_VOPR_IN_TEMA));
            }
            this.bPrev.setEnabled(false);
            this.bNext.setEnabled(false);
            this.bOK.setEnabled(false);
            set_timer2();
        }
    }

    /* access modifiers changed from: package-private */
    public void clearSelect() {
        this.chVar1.setChecked(false);
        this.chVar2.setChecked(false);
        this.chVar3.setChecked(false);
        this.chVar4.setChecked(false);
        this.chVar5.setChecked(false);
        this.chVar6.setChecked(false);
        this.tAnswer.setText("");
    }

    /* access modifiers changed from: package-private */
    public void ErrClear() {
        this.ERRORS = 0;
        this.ANSWERS = 0;
        this.tNOtvets.setText(Integer.toString(this.ANSWERS));
        this.tNErrors.setText(Integer.toString(this.ERRORS));
        Clear_Otvets();
        this.tOtvetYes.setTextColor(this.blackColor);
    }

    /* access modifiers changed from: package-private */
    public void setPodskazka() {
        Toast.makeText(this, this.PODSKAZKA, 1).show();
    }

    /* access modifiers changed from: package-private */
    public void setNomVopros() {
        if (this.TIP_EX == "1") {
            this.tOtvetYes.setText(String.valueOf(Integer.toString(this.TEK_VOPR_IN_TEMA + 1)) + " из " + Integer.toString(this.KOL_VOPR_IN_TEMA));
            this.myService2.doSend("T" + Integer.toString(this.NUM_SELECT) + "," + Integer.toString(this.TEK_VOPR_IN_TEMA));
        } else {
            this.tOtvetYes.setText(String.valueOf(Integer.toString(this.TEK_QST_IN_TICKET)) + " из " + Integer.toString(this.N_QST));
            this.tNumTicket.setText(String.valueOf(Integer.toString(this.TEK_TICKET)) + " из " + Integer.toString(this.N_TICKET));
            this.myService2.doSend("K" + Integer.toString(this.TEK_TICKET) + "," + Integer.toString(this.TEK_QST_IN_TICKET));
        }
        clearSelect();
        this.bPrev.setEnabled(false);
        this.bNext.setEnabled(false);
        this.bOK.setEnabled(false);
        set_timer2();
    }

    /* access modifiers changed from: package-private */
    public void scroll(int n) {
        switch (this.NUM) {
            case 1:
                this.tVar1.scrollBy(0, n);
                return;
            case 2:
                this.tVar2.scrollBy(0, n);
                return;
            case 3:
                this.tVar3.scrollBy(0, n);
                return;
            case 4:
                this.tVar4.scrollBy(0, n);
                return;
            case 5:
                this.tVar5.scrollBy(0, n);
                return;
            case 6:
                this.tVar6.scrollBy(0, n);
                return;
            case MotionEventCompat.ACTION_HOVER_MOVE:
                this.tVoprMain.scrollBy(0, n);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void setNum() {
        switch (this.NUM) {
            case 1:
                this.tVoprMain.setBackgroundColor(this.zeroColor);
                this.tVar1.setBackgroundColor(this.backColor);
                this.tVar2.setBackgroundColor(this.zeroColor);
                this.tVar3.setBackgroundColor(this.zeroColor);
                this.tVar4.setBackgroundColor(this.zeroColor);
                this.tVar5.setBackgroundColor(this.zeroColor);
                this.tVar6.setBackgroundColor(this.zeroColor);
                return;
            case 2:
                this.tVoprMain.setBackgroundColor(this.zeroColor);
                this.tVar1.setBackgroundColor(this.zeroColor);
                this.tVar2.setBackgroundColor(this.backColor);
                this.tVar3.setBackgroundColor(this.zeroColor);
                this.tVar4.setBackgroundColor(this.zeroColor);
                this.tVar5.setBackgroundColor(this.zeroColor);
                this.tVar6.setBackgroundColor(this.zeroColor);
                return;
            case 3:
                this.tVoprMain.setBackgroundColor(this.zeroColor);
                this.tVar1.setBackgroundColor(this.zeroColor);
                this.tVar2.setBackgroundColor(this.zeroColor);
                this.tVar3.setBackgroundColor(this.backColor);
                this.tVar4.setBackgroundColor(this.zeroColor);
                this.tVar5.setBackgroundColor(this.zeroColor);
                this.tVar6.setBackgroundColor(this.zeroColor);
                return;
            case 4:
                this.tVoprMain.setBackgroundColor(this.zeroColor);
                this.tVar1.setBackgroundColor(this.zeroColor);
                this.tVar2.setBackgroundColor(this.zeroColor);
                this.tVar3.setBackgroundColor(this.zeroColor);
                this.tVar4.setBackgroundColor(this.backColor);
                this.tVar5.setBackgroundColor(this.zeroColor);
                this.tVar6.setBackgroundColor(this.zeroColor);
                return;
            case 5:
                this.tVoprMain.setBackgroundColor(this.zeroColor);
                this.tVar1.setBackgroundColor(this.zeroColor);
                this.tVar2.setBackgroundColor(this.zeroColor);
                this.tVar3.setBackgroundColor(this.zeroColor);
                this.tVar4.setBackgroundColor(this.zeroColor);
                this.tVar5.setBackgroundColor(this.backColor);
                this.tVar6.setBackgroundColor(this.zeroColor);
                return;
            case 6:
                this.tVoprMain.setBackgroundColor(this.zeroColor);
                this.tVar1.setBackgroundColor(this.zeroColor);
                this.tVar2.setBackgroundColor(this.zeroColor);
                this.tVar3.setBackgroundColor(this.zeroColor);
                this.tVar4.setBackgroundColor(this.zeroColor);
                this.tVar5.setBackgroundColor(this.zeroColor);
                this.tVar6.setBackgroundColor(this.backColor);
                return;
            case MotionEventCompat.ACTION_HOVER_MOVE:
                this.tVoprMain.setBackgroundColor(this.backColor);
                this.tVar1.setBackgroundColor(this.zeroColor);
                this.tVar2.setBackgroundColor(this.zeroColor);
                this.tVar3.setBackgroundColor(this.zeroColor);
                this.tVar4.setBackgroundColor(this.zeroColor);
                this.tVar5.setBackgroundColor(this.zeroColor);
                this.tVar6.setBackgroundColor(this.zeroColor);
                return;
            default:
                return;
        }
    }

    public void set_timer2() {
        final Timer tmr52 = new Timer();
        final Handler uiHandler = new Handler();
        tmr52.schedule(new TimerTask() {
            public void run() {
                Handler handler = uiHandler;
                final Timer timer = tmr52;
                handler.post(new Runnable() {
                    public void run() {
                        timer.cancel();
                        browser.this.bPrev.setEnabled(true);
                        browser.this.bNext.setEnabled(true);
                        browser.this.bOK.setEnabled(true);
                    }
                });
            }
        }, 1500);
    }

    public void set_timer1() {
        final Timer tmr2 = new Timer();
        final Handler uiHandler = new Handler();
        tmr2.schedule(new TimerTask() {
            public void run() {
                Handler handler = uiHandler;
                final Timer timer = tmr2;
                handler.post(new Runnable() {
                    public void run() {
                        browser.this.start1();
                        timer.cancel();
                    }
                });
            }
        }, 400);
    }

    public void start1() {
        int WrHeight = ((this.HEIGHT - this.frameLayout1.getHeight()) - this.frameLayout2.getHeight()) - this.linearLayout1.getHeight();
        int WrInt = (WrHeight - 30) / 3;
        this.chVar1.setHeight(WrInt / 2);
        this.chVar2.setHeight(WrInt / 2);
        this.tZap1B.setHeight(WrInt / 4);
        this.tZap1B.setVisibility(4);
        this.tZap2B.setHeight(WrInt / 4);
        this.tZap2B.setVisibility(4);
        this.tZap3B.setHeight(WrInt / 4);
        this.tZap3B.setVisibility(4);
        this.tZap4B.setHeight(WrInt / 4);
        this.tZap4B.setVisibility(4);
        this.chVar3.setHeight(WrInt / 2);
        this.chVar4.setHeight(WrInt / 2);
        this.chVar5.setHeight(WrInt / 2);
        this.chVar6.setHeight(WrInt / 2);
        int WrInt2 = WrInt - 30;
        this.tVar1.setHeight(WrInt2);
        this.tVar2.setHeight(WrInt2);
        this.tVar3.setHeight(WrInt2);
        this.tVar4.setHeight(WrInt2);
        this.tVar5.setHeight(WrInt2);
        this.tVar6.setHeight(WrInt2);
        int WrInt3 = ((WrHeight - this.linearLayout2.getHeight()) - this.tVoprMain.getHeight()) - 50;
        ViewGroup.LayoutParams params = this.vImage.getLayoutParams();
        params.height = this.IMAGE_HEIGHT;
        int WrInt4 = (int) (((double) this.IMAGE_HEIGHT) * 1.5d);
        params.width = WrInt4;
        this.vImage.setLayoutParams(params);
        int WrWidth = (((this.WIDTH - WrInt4) - 40) / 2) - this.chVar1.getWidth();
        this.tVar1.setWidth(WrWidth);
        this.tVar2.setWidth(WrWidth);
        this.tVar3.setWidth(WrWidth);
        this.tVar4.setWidth(WrWidth);
        this.tVar5.setWidth(WrWidth);
        this.tVar6.setWidth(WrWidth);
        int WrInt5 = (WrInt4 - this.tVopros.length()) / 2;
        this.tOtvetYes.setTextColor(this.GreenText);
        if (this.HELP_YES) {
            this.bVopr.setEnabled(true);
        } else {
            this.bVopr.setEnabled(false);
        }
        this.ERRORS = 0;
        this.ANSWERS = 0;
        this.tNOtvets.setText(Integer.toString(this.ANSWERS));
        this.tNErrors.setText(Integer.toString(this.ERRORS));
        this.tAnswer.setText("");
    }

    public void onClickCheck(int num) {
        switch (num) {
            case 1:
                this.chVar1.setChecked(true);
                this.chVar2.setChecked(false);
                this.chVar3.setChecked(false);
                this.chVar4.setChecked(false);
                this.chVar5.setChecked(false);
                this.chVar6.setChecked(false);
                break;
            case 2:
                this.chVar1.setChecked(false);
                this.chVar2.setChecked(true);
                this.chVar3.setChecked(false);
                this.chVar4.setChecked(false);
                this.chVar5.setChecked(false);
                this.chVar6.setChecked(false);
                break;
            case 3:
                this.chVar1.setChecked(false);
                this.chVar2.setChecked(false);
                this.chVar3.setChecked(true);
                this.chVar4.setChecked(false);
                this.chVar5.setChecked(false);
                this.chVar6.setChecked(false);
                break;
            case 4:
                this.chVar1.setChecked(false);
                this.chVar2.setChecked(false);
                this.chVar3.setChecked(false);
                this.chVar4.setChecked(true);
                this.chVar5.setChecked(false);
                this.chVar6.setChecked(false);
                break;
            case 5:
                this.chVar1.setChecked(false);
                this.chVar2.setChecked(false);
                this.chVar3.setChecked(false);
                this.chVar4.setChecked(false);
                this.chVar5.setChecked(true);
                this.chVar6.setChecked(false);
                break;
            case 6:
                this.chVar1.setChecked(false);
                this.chVar2.setChecked(false);
                this.chVar3.setChecked(false);
                this.chVar4.setChecked(false);
                this.chVar5.setChecked(false);
                this.chVar6.setChecked(true);
                break;
            case MotionEventCompat.ACTION_HOVER_MOVE:
                this.chTems.setChecked(true);
                this.chTick.setChecked(false);
                this.TIP_EX = "1";
                this.tNumTicket.setVisibility(4);
                this.tNumTick.setVisibility(4);
                this.spinner1.setVisibility(0);
                this.myService2.doSend("T" + Integer.toString(this.NUM_SELECT) + ",0");
                this.bPrev.setEnabled(false);
                this.bNext.setEnabled(false);
                set_timer2();
                break;
            case 8:
                this.chTems.setChecked(false);
                this.chTick.setChecked(true);
                this.TIP_EX = "0";
                this.tNumTicket.setVisibility(0);
                this.tNumTick.setVisibility(0);
                this.spinner1.setVisibility(4);
                this.myService2.doSend("K1,1");
                this.bPrev.setEnabled(false);
                this.bNext.setEnabled(false);
                set_timer2();
                break;
        }
        if (num < 7) {
            this.tAnswer.setText(String.valueOf(Integer.toString(num)) + "?");
        }
        this.THIS_OTVET = num;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 || keyCode == 3 || keyCode == 16908332) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
