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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import androidx.core.view.MotionEventCompat;

public class etic extends Activity {
    public static final String BROADCAST_ACTION = "com.econavt.uniexam";
    public static final String SHARED_PREFS_NAME = "sett.xml";
    public int ANSWERS = 100;
    boolean BROWSER_ERR = false;
    public String CommString;
    boolean END_EXAMEN = false;
    public int ERRORS = 100;
    int ERR_RATE_3;
    int ERR_RATE_4;
    int ERR_RATE_5;
    boolean EXAMEN_BREAK_ON_ERR = false;
    boolean EXAMEN_BREAK_ON_TIME = false;
    boolean FIRST = true;
    int GreenText = -16733696;
    int HEIGHT;
    public boolean HELP_YES = true;
    int IMAGE_HEIGHT = 235;
    boolean INI_BREAK_ON_ERR;
    boolean INI_CONTR_TIME;
    boolean INI_ENABLE_HINT;
    boolean INI_SDAN;
    boolean INI_SELECT_QST;
    boolean INI_SHOW_REZ;
    boolean INI_WORK_ERR;
    public int[] MASS_ERR_PRESSED;
    public int[] MASS_N_ERR_QST;
    public int[] MASS_N_QST;
    public int[] MASS_PRAV;
    public int[] MASS_PRESSED;
    boolean M_SOUND;
    int NO_ANSWER;
    public int NUM = 1;
    int N_QST;
    int N_TICKET;
    public int[] OTVETS;
    public String PODSKAZKA;
    int SELECTED_VARIANT_TICKETS;
    public boolean STARTING = false;
    int START_WIDTH;
    public boolean Set_ImTic = true;
    int TEK_ALL_QST;
    String TEK_HELP;
    String[] TEK_MAS_OTVET = {"", "", "", "", "", ""};
    int TEK_NUM_VAR_OTVETOV;
    String TEK_PICT;
    int TEK_PR_OTVET;
    int TEK_QST_IN_TICKET;
    String TEK_QUEST;
    int TEK_TICKET;
    int TEK_TIME;
    public int THIS_OTVET = 0;
    int WIDTH;
    String WORK_FAM;
    int WORK_HEIGTH;
    String WORK_NAME;
    String WORK_NUM;
    String WORK_OTCH;
    int WORK_WIDTH;
    int ZADERJKA_t = 0;
    Button bCloseTic;
    ImageButton bNextTic;
    Button bOKTic;
    ImageButton bPrevTic;
    ImageButton bVoprTic;
    int backColor = -858993460;
    int blackColor = -16777216;
    boolean bound = false;
    BroadcastReceiver br4;
    CheckBox chVar1Tic;
    CheckBox chVar2Tic;
    CheckBox chVar3Tic;
    CheckBox chVar4Tic;
    CheckBox chVar5Tic;
    CheckBox chVar6Tic;
    public String[] data_strings = {"", "", "", "", ""};
    FrameLayout frameLayout2Tic;
    ImageButton iBuDownBrowsTic;
    ImageButton iBuUpBrowsTic;
    Intent intent4;
    long interval = 1000;
    LinearLayout linearLayout1Tic;
    LinearLayout linearLayout2Tic;
    ExamServiceRX myService4;
    int pauza_t = 5;
    int redColor = -5636096;
    ServiceConnection sConn4;
    SharedPreferences sPref;
    TextView tAnswerTic;
    TextView tErrorsTic;
    TextView tNErrorsTic;
    TextView tNOtvetsTic;
    TextView tNoAns;
    TextView tNomerTicket;
    TextView tOcenka;
    TextView tOtvetYesTic;
    TextView tOtvetsTic;
    TextView tResult;
    TextView tTimeTic;
    TextView tVar1Tic;
    TextView tVar2Tic;
    TextView tVar3Tic;
    TextView tVar4Tic;
    TextView tVar5Tic;
    TextView tVar6Tic;
    TextView tVariantTic;
    TextView tVoprMainTic;
    TextView tVoprosTic;
    TextView tZap1;
    TextView tZap2;
    TextView tZap3;
    TextView tZap4;
    public Timer tm3;
    public Timer tmr;
    public Timer tmr2;
    ImageView vImageTic;
    int zeroColor = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etic);
        this.bCloseTic = (Button) findViewById(R.id.bCloseTic);
        this.iBuUpBrowsTic = (ImageButton) findViewById(R.id.iBuUpBrowsTic);
        this.iBuDownBrowsTic = (ImageButton) findViewById(R.id.iBuDownBrowsTic);
        this.bOKTic = (Button) findViewById(R.id.bOKTic);
        this.bVoprTic = (ImageButton) findViewById(R.id.bVoprTic);
        this.bPrevTic = (ImageButton) findViewById(R.id.bPrevTic);
        this.bNextTic = (ImageButton) findViewById(R.id.bNextTic);
        this.frameLayout2Tic = (FrameLayout) findViewById(R.id.frameLayout2Tic);
        this.linearLayout1Tic = (LinearLayout) findViewById(R.id.linearLayout1Tic);
        this.linearLayout2Tic = (LinearLayout) findViewById(R.id.linearLayout2Tic);
        this.tVoprosTic = (TextView) findViewById(R.id.tVoprosTic);
        this.tVariantTic = (TextView) findViewById(R.id.tVariantTic);
        this.tVoprMainTic = (TextView) findViewById(R.id.tVoprMainTic);
        this.tOtvetsTic = (TextView) findViewById(R.id.tOtvetsTic);
        this.tNOtvetsTic = (TextView) findViewById(R.id.tNOtvetsTic);
        this.tErrorsTic = (TextView) findViewById(R.id.tErrorsTic);
        this.tNErrorsTic = (TextView) findViewById(R.id.tNErrorsTic);
        this.tOtvetYesTic = (TextView) findViewById(R.id.tOtvetYesTic);
        this.tNomerTicket = (TextView) findViewById(R.id.tNomerTicket);
        this.tAnswerTic = (TextView) findViewById(R.id.tAnswerTic);
        this.tNoAns = (TextView) findViewById(R.id.tNoAns);
        this.chVar1Tic = (CheckBox) findViewById(R.id.chVar1Tic);
        this.chVar2Tic = (CheckBox) findViewById(R.id.chVar2Tic);
        this.chVar3Tic = (CheckBox) findViewById(R.id.chVar3Tic);
        this.chVar4Tic = (CheckBox) findViewById(R.id.chVar4Tic);
        this.chVar5Tic = (CheckBox) findViewById(R.id.chVar5Tic);
        this.chVar6Tic = (CheckBox) findViewById(R.id.chVar6Tic);
        this.vImageTic = (ImageView) findViewById(R.id.vImageTic);
        this.tVar1Tic = (TextView) findViewById(R.id.tVar1Tic);
        this.tVar2Tic = (TextView) findViewById(R.id.tVar2Tic);
        this.tVar3Tic = (TextView) findViewById(R.id.tVar3Tic);
        this.tVar4Tic = (TextView) findViewById(R.id.tVar4Tic);
        this.tVar5Tic = (TextView) findViewById(R.id.tVar5Tic);
        this.tVar6Tic = (TextView) findViewById(R.id.tVar6Tic);
        this.tTimeTic = (TextView) findViewById(R.id.tTimeTic);
        this.tResult = (TextView) findViewById(R.id.tResult);
        this.tOcenka = (TextView) findViewById(R.id.tOcenka);
        this.tZap1 = (TextView) findViewById(R.id.tZap1);
        this.tZap2 = (TextView) findViewById(R.id.tZap2);
        this.tZap3 = (TextView) findViewById(R.id.tZap3);
        this.tZap4 = (TextView) findViewById(R.id.tZap4);
        View.OnClickListener oclBtn2 = v -> {
            switch (v.getId()) {
                case R.id.chVar5Tic:
                    if (etic.this.chVar5Tic.isChecked()) {
                        etic.this.onClickCheck(5);
                        return;
                    } else {
                        etic.this.chVar5Tic.setChecked(true);
                        return;
                    }
                case R.id.chVar4Tic:
                    if (etic.this.chVar4Tic.isChecked()) {
                        etic.this.onClickCheck(4);
                        return;
                    } else {
                        etic.this.chVar4Tic.setChecked(true);
                        return;
                    }
                case R.id.chVar2Tic:
                    if (etic.this.chVar2Tic.isChecked()) {
                        etic.this.onClickCheck(2);
                        return;
                    } else {
                        etic.this.chVar2Tic.setChecked(true);
                        return;
                    }
                case R.id.chVar6Tic:
                    if (etic.this.chVar6Tic.isChecked()) {
                        etic.this.onClickCheck(6);
                        return;
                    } else {
                        etic.this.chVar6Tic.setChecked(true);
                        return;
                    }
                case R.id.tVoprMainTic:
                    etic.this.NUM = 7;
                    etic.this.setNum();
                    return;
                case R.id.bPrevTic:
                    etic.this.Set_ImTic = true;
                    if (etic.this.INI_SELECT_QST && etic.this.TEK_QST_IN_TICKET != 1) {
                        etic etic = etic.this;
                        etic.TEK_QST_IN_TICKET--;
                        etic.this.setNextVopros();
                        return;
                    }
                    return;
                case R.id.bNextTic:
                    etic.this.Set_ImTic = true;
                    if (etic.this.INI_SELECT_QST && etic.this.TEK_QST_IN_TICKET != etic.this.N_QST) {
                        etic.this.TEK_QST_IN_TICKET++;
                        etic.this.setNextVopros();
                        return;
                    }
                    return;
                case R.id.iBuUpBrowsTic:
                    etic.this.scroll(10);
                    return;
                case R.id.iBuDownBrowsTic:
                    etic.this.scroll(-10);
                    return;
                case R.id.chVar1Tic:
                    if (etic.this.chVar1Tic.isChecked()) {
                        etic.this.onClickCheck(1);
                        return;
                    } else {
                        etic.this.chVar1Tic.setChecked(true);
                        return;
                    }
                case R.id.bVoprTic:
                    if (etic.this.HELP_YES) {
                        etic.this.setPodskazka();
                        return;
                    }
                    return;
                case R.id.bOKTic:
                    etic.this.setOK();
                    return;
                case R.id.bCloseTic:
                    if (etic.this.BROWSER_ERR) {
                        etic.this.TEK_QST_IN_TICKET = 1;
                        etic.this.myService4.doSendRX("RN" + etic.this.WORK_NUM + "," + Integer.toString(etic.this.MASS_N_ERR_QST[etic.this.TEK_QST_IN_TICKET - 1]));
                        etic.this.bOKTic.setEnabled(false);
                        etic.this.bCloseTic.setEnabled(false);
                        return;
                    }
                    etic.this.myService4.doSendRX("ETX,1");
                    etic.this.EXAMEN_BREAK_ON_ERR = true;
                    etic.this.finish();
                    return;
                case R.id.chVar3Tic:
                    if (etic.this.chVar3Tic.isChecked()) {
                        etic.this.onClickCheck(3);
                        return;
                    } else {
                        etic.this.chVar3Tic.setChecked(true);
                        return;
                    }
                case R.id.vImageTic:
                    etic.this.click_im();
                    return;
                case R.id.tVar1Tic:
                    etic.this.NUM = 1;
                    etic.this.setNum();
                    return;
                case R.id.tVar3Tic:
                    etic.this.NUM = 3;
                    etic.this.setNum();
                    return;
                case R.id.tVar5Tic:
                    etic.this.NUM = 5;
                    etic.this.setNum();
                    return;
                case R.id.tVar4Tic:
                    etic.this.NUM = 4;
                    etic.this.setNum();
                    return;
                case R.id.tVar6Tic:
                    etic.this.NUM = 6;
                    etic.this.setNum();
                    return;
                case R.id.tVar2Tic:
                    etic.this.NUM = 2;
                    etic.this.setNum();
                    return;
                default:
                    return;
            }
        };
        this.bCloseTic.setOnClickListener(oclBtn2);
        this.iBuUpBrowsTic.setOnClickListener(oclBtn2);
        this.iBuDownBrowsTic.setOnClickListener(oclBtn2);
        this.bOKTic.setOnClickListener(oclBtn2);
        this.bVoprTic.setOnClickListener(oclBtn2);
        this.bPrevTic.setOnClickListener(oclBtn2);
        this.bNextTic.setOnClickListener(oclBtn2);
        this.tVoprMainTic.setOnClickListener(oclBtn2);
        this.tVar1Tic.setOnClickListener(oclBtn2);
        this.tVar2Tic.setOnClickListener(oclBtn2);
        this.tVar3Tic.setOnClickListener(oclBtn2);
        this.tVar4Tic.setOnClickListener(oclBtn2);
        this.tVar5Tic.setOnClickListener(oclBtn2);
        this.tVar6Tic.setOnClickListener(oclBtn2);
        this.chVar1Tic.setOnClickListener(oclBtn2);
        this.chVar2Tic.setOnClickListener(oclBtn2);
        this.chVar3Tic.setOnClickListener(oclBtn2);
        this.chVar4Tic.setOnClickListener(oclBtn2);
        this.chVar5Tic.setOnClickListener(oclBtn2);
        this.chVar6Tic.setOnClickListener(oclBtn2);
        this.vImageTic.setOnClickListener(oclBtn2);
        //todo Найти что за ресурсы
        new ArrayAdapter<>(this, 17367048, this.data_strings).setDropDownViewResource(17367049);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.WIDTH = size.x;
        this.HEIGHT = size.y;
        this.sPref = getSharedPreferences("sett.xml", 0);
        this.WORK_NUM = this.sPref.getString("w_numer", "1");
        this.WORK_NAME = this.sPref.getString("w_name", "Учащийся " + this.WORK_NUM);
        this.M_SOUND = this.sPref.getBoolean("m_sound", true);
        this.pauza_t = this.sPref.getInt("pauza_t", 5);
        this.WORK_HEIGTH = this.sPref.getInt("FontSize", 10);
        this.IMAGE_HEIGHT = this.sPref.getInt("ImageHeight", 235);
        this.tVoprMainTic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar1Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar2Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar3Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar4Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar5Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar6Tic.setTextSize((float) this.WORK_HEIGTH);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().hide();
        set_timer1();
        if (!this.STARTING) {
            this.br4 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    byte[] byteArray = intent.getByteArrayExtra(MainActivity.PARAM_BUF);


                    if (byteArray[0] == 45) {
                        etic.this.CommString = new String(byteArray);
                        etic.this.set_comand(etic.this.CommString);
                    } else if (byteArray[0] != 61) {
                        UniLog.onReceived("Image");
                        Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                        etic.this.vImageTic.setBackgroundColor(etic.this.zeroColor);
                        etic.this.vImageTic.setImageBitmap(bit);
                    }
                }
            };
            registerReceiver(this.br4, new IntentFilter("com.econavt.uniexam"));
        }
        this.intent4 = new Intent(this, ExamServiceRX.class);
        this.sConn4 = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                if (!etic.this.STARTING) {
                    etic.this.myService4 = ((ExamServiceRX.MyBinder) binder).getService();
                    etic.this.bound = true;
                    etic.this.END_EXAMEN = false;
                    etic.this.myService4.doSendRX("EIP", () -> STARTING = true);

//                    etic.this.STARTING = true;
                }
            }

            public void onServiceDisconnected(ComponentName name) {
                etic.this.bound = false;
            }
        };
    }


//    private void tryDecodeImage(byte[] bytes) {
//
//        String[] elements = new String(bytes).split("#");
//
//
//        byte[] biggestElement = null;
//        int maxSize = 0;
//
//        for (int i = 0; i < elements.length; i++) {
//            if (elements[i].length() > maxSize){
//                maxSize = elements[i].length();
//                biggestElement = elements[i].getBytes();
//            }
//        }
//
//
//
//            try {
//
//                Bitmap bit = BitmapFactory.decodeByteArray(biggestElement, 0, biggestElement.length);
//                etic.this.vImageTic.setBackgroundColor(etic.this.zeroColor);
//                etic.this.vImageTic.setImageBitmap(bit);
//            } catch (
//                    Exception e) {
//                UniLog.error("cant decode");
//            }
//
//
//
//
//
//
//
//
//    }


    /* access modifiers changed from: package-private */
    public void click_im() {
        int WrY;
        ViewGroup.LayoutParams params = this.vImageTic.getLayoutParams();
        if (this.Set_ImTic) {
            this.Set_ImTic = false;
            WrY = this.HEIGHT;
        } else {
            this.Set_ImTic = true;
            WrY = this.IMAGE_HEIGHT;
        }
        params.height = WrY;
        params.width = (int) (((double) WrY) * 1.5d);
        this.vImageTic.setLayoutParams(params);
        this.vImageTic.bringToFront();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        bindService(this.intent4, this.sConn4, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.sPref = getSharedPreferences("sett.xml", 0);
        this.WORK_NUM = this.sPref.getString("w_numer", "1");
        this.WORK_NAME = this.sPref.getString("w_name", "Учащийся " + this.WORK_NUM);
        this.M_SOUND = this.sPref.getBoolean("m_sound", true);
        this.pauza_t = this.sPref.getInt("pauza_t", 5);
        this.WORK_HEIGTH = this.sPref.getInt("FontSize", 10);
        this.IMAGE_HEIGHT = this.sPref.getInt("ImageHeight", 235);
        this.tVoprMainTic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar1Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar2Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar3Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar4Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar5Tic.setTextSize((float) this.WORK_HEIGTH);
        this.tVar6Tic.setTextSize((float) this.WORK_HEIGTH);
    }

    public void set_comand(String str) {
        String Comm = str;
        byte[] buffer = str.getBytes();
        if (buffer[0] == 45) {
            switch (buffer[1]) {
                case 48:
                    EXAMEN_BREAK_ON_TIME = false;
                    EXAMEN_BREAK_ON_ERR = false;
                    END_EXAMEN = false;
                    BROWSER_ERR = false;
                    NO_ANSWER = 0;
                    bOKTic.setEnabled(true);
                    bCloseTic.setEnabled(false);
                    tOtvetsTic.setText("Ответов:");
                    tErrorsTic.setText("Ошибок:");
                    tNoAns.setText("0");
                    String[] fields_main = Comm.split("#");
                    INI_SELECT_QST = Integer.parseInt(fields_main[1]) == 1;
                    INI_SHOW_REZ = Integer.parseInt(fields_main[2]) == 1;
                    INI_ENABLE_HINT = Integer.parseInt(fields_main[3]) == 1;
                    INI_CONTR_TIME = Integer.parseInt(fields_main[4]) == 1;
                    INI_WORK_ERR = Integer.parseInt(fields_main[5]) == 1;
                    INI_BREAK_ON_ERR = Integer.parseInt(fields_main[6]) == 1;
                    INI_SDAN = Integer.parseInt(fields_main[7]) == 1;
                    ERR_RATE_3 = Integer.parseInt(fields_main[8]);
                    ERR_RATE_4 = Integer.parseInt(fields_main[9]);
                    ERR_RATE_5 = Integer.parseInt(fields_main[10]);
                    bVoprTic.setEnabled(INI_ENABLE_HINT);


                    this.myService4.doSendRX("EI");
                    return;
                case 50:
                    String[] fields_main2 = Comm.split("#");
                    N_TICKET = Integer.parseInt(fields_main2[1]);
                    N_QST = Integer.parseInt(fields_main2[2]);
                    TEK_TICKET = Integer.parseInt(fields_main2[3]);
                    TEK_QST_IN_TICKET = Integer.parseInt(fields_main2[4]);
                    SELECTED_VARIANT_TICKETS = Integer.parseInt(fields_main2[5]);
                    TEK_QUEST = fields_main2[6];
                    TEK_PR_OTVET = Integer.parseInt(fields_main2[7]);
                    TEK_PICT = fields_main2[8];
                    TEK_HELP = fields_main2[9];
                    PODSKAZKA = fields_main2[10];
                    if (PODSKAZKA.length() == 0) {
                        bVoprTic.setEnabled(false);
                    } else if (INI_ENABLE_HINT) {
                        bVoprTic.setEnabled(true);
                    } else {
                        bVoprTic.setEnabled(false);
                    }
                    TEK_ALL_QST = Integer.parseInt(fields_main2[11]);
                    TEK_TIME = Integer.parseInt(fields_main2[12]);
                    if (FIRST) {
                        OTVETS = new int[N_QST];
                        MASS_PRAV = new int[N_QST];
                        MASS_N_QST = new int[N_QST];
                        MASS_PRESSED = new int[N_QST];
                        Clear_Otvets();
                        EXAMEN_BREAK_ON_TIME = false;
                        if (TEK_TIME > 0) {
                            start_timer();
                        }
                        this.FIRST = false;
                    }
                    this.MASS_PRAV[this.TEK_QST_IN_TICKET - 1] = this.TEK_PR_OTVET;
                    this.MASS_N_QST[this.TEK_QST_IN_TICKET - 1] = this.TEK_ALL_QST;
                    String[] fields_next = fields_main2[13].split("@");
                    this.TEK_NUM_VAR_OTVETOV = Integer.parseInt(fields_next[0]);
                    for (int i = 0; i < this.TEK_NUM_VAR_OTVETOV; i++) {
                        this.TEK_MAS_OTVET[i] = fields_next[i + 1];
                    }
                    set_ekran();
                    ErrClear();
                    return;
                case 51:
                    String[] fields_main3 = Comm.split("#");
                    this.N_TICKET = Integer.parseInt(fields_main3[1]);
                    this.N_QST = Integer.parseInt(fields_main3[2]);
                    this.TEK_TICKET = Integer.parseInt(fields_main3[3]);
                    this.TEK_QST_IN_TICKET = Integer.parseInt(fields_main3[4]);
                    this.SELECTED_VARIANT_TICKETS = Integer.parseInt(fields_main3[5]);
                    this.TEK_QUEST = fields_main3[6];
                    this.TEK_PR_OTVET = Integer.parseInt(fields_main3[7]);
                    this.TEK_PICT = fields_main3[8];
                    this.TEK_HELP = fields_main3[9];
                    this.PODSKAZKA = fields_main3[10];
                    if (this.PODSKAZKA.length() == 0) {
                        this.bVoprTic.setEnabled(false);
                    } else if (this.INI_ENABLE_HINT) {
                        this.bVoprTic.setEnabled(true);
                    } else {
                        this.bVoprTic.setEnabled(false);
                    }
                    this.TEK_ALL_QST = Integer.parseInt(fields_main3[11]);
                    this.MASS_PRAV[this.TEK_QST_IN_TICKET - 1] = this.TEK_PR_OTVET;
                    this.MASS_N_QST[this.TEK_QST_IN_TICKET - 1] = this.TEK_ALL_QST;
                    String[] fields_next2 = fields_main3[12].split("@");
                    this.TEK_NUM_VAR_OTVETOV = Integer.parseInt(fields_next2[0]);
                    for (int i2 = 0; i2 < this.TEK_NUM_VAR_OTVETOV; i2++) {
                        this.TEK_MAS_OTVET[i2] = fields_next2[i2 + 1];
                    }
                    set_ekran();
                    return;
                case 52:
                    String[] fields_main4 = Comm.split("#");
                    this.TEK_QUEST = fields_main4[1];
                    this.TEK_PR_OTVET = Integer.parseInt(fields_main4[2]);
                    this.TEK_PICT = fields_main4[3];
                    this.TEK_HELP = fields_main4[4];
                    String[] fields_next3 = fields_main4[5].split("@");
                    this.TEK_NUM_VAR_OTVETOV = Integer.parseInt(fields_next3[0]);
                    for (int i3 = 0; i3 < this.TEK_NUM_VAR_OTVETOV; i3++) {
                        this.TEK_MAS_OTVET[i3] = fields_next3[i3 + 1];
                    }
                    this.INI_SHOW_REZ = false;
                    this.INI_SELECT_QST = true;
                    set_ekran();
                    return;
                case 53:
                    this.BROWSER_ERR = false;
                    this.bCloseTic.setTextColor(this.blackColor);
                    this.bCloseTic.setText("Выход");
                    this.bCloseTic.setEnabled(true);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x009f, code lost:
        switch(r8.OTVETS[r8.TEK_QST_IN_TICKET - 1]) {
            case 1: goto L_0x0119;
            case 5: goto L_0x0121;
            default: goto L_0x00a2;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00a2, code lost:
        r8.tOtvetYesTic.setTextColor(r8.blackColor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00a9, code lost:
        setNomVopros();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00b2, code lost:
        if (r8.TEK_PICT.length() != 0) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00b4, code lost:
        r8.vImageTic.setImageBitmap((android.graphics.Bitmap) null);
        r8.vImageTic.setBackgroundColor(r8.backColor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00d6, code lost:
        r8.tVar5Tic.setVisibility(0);
        r8.chVar5Tic.setVisibility(0);
        r8.tVar5Tic.setText(r8.TEK_MAS_OTVET[4]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00e9, code lost:
        r8.tVar4Tic.setVisibility(0);
        r8.chVar4Tic.setVisibility(0);
        r8.tVar4Tic.setText(r8.TEK_MAS_OTVET[3]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00fd, code lost:
        r8.tVar3Tic.setVisibility(0);
        r8.chVar3Tic.setVisibility(0);
        r8.tVar3Tic.setText(r8.TEK_MAS_OTVET[2]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0113, code lost:
        r1 = java.lang.Integer.toString(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0119, code lost:
        r8.tOtvetYesTic.setTextColor(r8.redColor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0121, code lost:
        r8.tOtvetYesTic.setTextColor(r8.GreenText);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x012b, code lost:
        if (r8.BROWSER_ERR == false) goto L_0x015b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x012d, code lost:
        r8.myService4.doSend("RI" + r8.WORK_NUM + "," + java.lang.Integer.toString(r8.MASS_N_ERR_QST[r8.TEK_QST_IN_TICKET - 1]));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x015b, code lost:
        r8.myService4.doSend("EII," + java.lang.Integer.toString(r8.TEK_ALL_QST));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0038, code lost:
        r8.tVar2Tic.setText(r8.TEK_MAS_OTVET[1]);
        r8.tVar1Tic.setText(r8.TEK_MAS_OTVET[0]);
        r8.tOtvetYesTic.setText(java.lang.String.valueOf(java.lang.Integer.toString(r8.TEK_QST_IN_TICKET)) + " из " + java.lang.Integer.toString(r8.N_QST));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0075, code lost:
        if (r8.BROWSER_ERR == false) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0077, code lost:
        r0 = r8.MASS_ERR_PRESSED[r8.TEK_QST_IN_TICKET - 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x007f, code lost:
        if (r0 != 0) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0081, code lost:
        r1 = "--";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0083, code lost:
        r8.tNOtvetsTic.setText(r1);
        r8.tNErrorsTic.setText(java.lang.Integer.toString(r8.TEK_PR_OTVET));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0095, code lost:
        if (r8.INI_SHOW_REZ == false) goto L_0x00a9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */

    public void set_ekran() {
        String WrStr;
        this.ZADERJKA_t = 0;
        this.tVar3Tic.setVisibility(View.INVISIBLE);
        this.tVar4Tic.setVisibility(View.INVISIBLE);
        this.tVar5Tic.setVisibility(View.INVISIBLE);
        this.tVar6Tic.setVisibility(View.INVISIBLE);
        this.chVar3Tic.setVisibility(View.INVISIBLE);
        this.chVar4Tic.setVisibility(View.INVISIBLE);
        this.chVar5Tic.setVisibility(View.INVISIBLE);
        this.chVar6Tic.setVisibility(View.INVISIBLE);
        this.tVoprMainTic.setText(this.TEK_QUEST);
        switch (this.TEK_NUM_VAR_OTVETOV) {
            case 6:
                this.tVar6Tic.setVisibility(View.VISIBLE);
                this.chVar6Tic.setVisibility(View.VISIBLE);
                this.tVar6Tic.setText(this.TEK_MAS_OTVET[5]);
            case 5:
                this.tVar5Tic.setVisibility(View.VISIBLE);
                this.chVar5Tic.setVisibility(View.VISIBLE);
                this.tVar5Tic.setText(this.TEK_MAS_OTVET[4]);
            case 4:
                this.tVar4Tic.setVisibility(View.VISIBLE);
                this.chVar4Tic.setVisibility(View.VISIBLE);
                this.tVar4Tic.setText(this.TEK_MAS_OTVET[3]);
            case 3:
                this.tVar3Tic.setVisibility(View.VISIBLE);
                this.chVar3Tic.setVisibility(View.VISIBLE);
                this.tVar3Tic.setText(this.TEK_MAS_OTVET[2]);
                break;
        }
        this.tVar2Tic.setText(this.TEK_MAS_OTVET[1]);
        this.tVar1Tic.setText(this.TEK_MAS_OTVET[0]);
        this.tOtvetYesTic.setText(String.valueOf(Integer.toString(this.TEK_QST_IN_TICKET)) + " из " + Integer.toString(this.N_QST));
        if (this.BROWSER_ERR) {
            int WrInt = this.MASS_ERR_PRESSED[this.TEK_QST_IN_TICKET - 1];
            if (WrInt == 0) {
                WrStr = "--";
            } else {
                WrStr = Integer.toString(WrInt);
            }
            this.tNOtvetsTic.setText(WrStr);
            this.tNErrorsTic.setText(Integer.toString(this.TEK_PR_OTVET));
        }
        if (this.INI_SHOW_REZ) {
            switch (this.OTVETS[this.TEK_QST_IN_TICKET - 1]) {
                case 1:
                    this.tOtvetYesTic.setTextColor(this.redColor);
                    break;
                case 5:
                    this.tOtvetYesTic.setTextColor(this.GreenText);
                    break;
                default:
                    this.tOtvetYesTic.setTextColor(this.blackColor);
                    break;
            }
        }
        setNomVopros();
        if (this.TEK_PICT.length() == 0) {
            this.vImageTic.setImageBitmap(null);
            this.vImageTic.setBackgroundColor(this.backColor);
        } else if (this.BROWSER_ERR) {
            this.myService4.doSend("RI" + this.WORK_NUM + "," + Integer.toString(this.MASS_N_ERR_QST[this.TEK_QST_IN_TICKET - 1]));
        } else {
            this.myService4.doSend("EII," + Integer.toString(this.TEK_ALL_QST));
        }
    }

    public void set_ekranOld() {


        String a = "";
        String b = a + "";
        System.out.println(b);
        /*
            r8 = this;
            r7 = 4
            r6 = 0
            r8.ZADERJKA_t = r6
            android.widget.TextView r3 = r8.tVar3Tic
            r3.setVisibility(r7)
            android.widget.TextView r3 = r8.tVar4Tic
            r3.setVisibility(r7)
            android.widget.TextView r3 = r8.tVar5Tic
            r3.setVisibility(r7)
            android.widget.TextView r3 = r8.tVar6Tic
            r3.setVisibility(r7)
            android.widget.CheckBox r3 = r8.chVar3Tic
            r3.setVisibility(r7)
            android.widget.CheckBox r3 = r8.chVar4Tic
            r3.setVisibility(r7)
            android.widget.CheckBox r3 = r8.chVar5Tic
            r3.setVisibility(r7)
            android.widget.CheckBox r3 = r8.chVar6Tic
            r3.setVisibility(r7)
            android.widget.TextView r3 = r8.tVoprMainTic
            java.lang.String r4 = r8.TEK_QUEST
            r3.setText(r4)
            int r3 = r8.TEK_NUM_VAR_OTVETOV
            switch(r3) {
                case 3: goto L_0x00fd;
                case 4: goto L_0x00e9;
                case 5: goto L_0x00d6;
                case 6: goto L_0x00c2;
                default: goto L_0x0038;
            }
        L_0x0038:
            android.widget.TextView r3 = r8.tVar2Tic
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r5 = 1
            r4 = r4[r5]
            r3.setText(r4)
            android.widget.TextView r3 = r8.tVar1Tic
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r4 = r4[r6]
            r3.setText(r4)
            android.widget.TextView r3 = r8.tOtvetYesTic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r5 = r8.TEK_QST_IN_TICKET
            java.lang.String r5 = java.lang.Integer.toString(r5)
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r4.<init>(r5)
            java.lang.String r5 = " из "
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = r8.N_QST
            java.lang.String r5 = java.lang.Integer.toString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.setText(r4)
            boolean r3 = r8.BROWSER_ERR
            if (r3 == 0) goto L_0x0093
            int[] r3 = r8.MASS_ERR_PRESSED
            int r4 = r8.TEK_QST_IN_TICKET
            int r4 = r4 + -1
            r0 = r3[r4]
            if (r0 != 0) goto L_0x0113
            java.lang.String r1 = "--"
        L_0x0083:
            android.widget.TextView r3 = r8.tNOtvetsTic
            r3.setText(r1)
            android.widget.TextView r3 = r8.tNErrorsTic
            int r4 = r8.TEK_PR_OTVET
            java.lang.String r4 = java.lang.Integer.toString(r4)
            r3.setText(r4)
        L_0x0093:
            boolean r3 = r8.INI_SHOW_REZ
            if (r3 == 0) goto L_0x00a9
            int[] r3 = r8.OTVETS
            int r4 = r8.TEK_QST_IN_TICKET
            int r4 = r4 + -1
            r3 = r3[r4]
            switch(r3) {
                case 1: goto L_0x0119;
                case 5: goto L_0x0121;
                default: goto L_0x00a2;
            }
        L_0x00a2:
            android.widget.TextView r3 = r8.tOtvetYesTic
            int r4 = r8.blackColor
            r3.setTextColor(r4)
        L_0x00a9:
            r8.setNomVopros()
            java.lang.String r3 = r8.TEK_PICT
            int r3 = r3.length()
            if (r3 != 0) goto L_0x0129
            r2 = 0
            android.widget.ImageView r3 = r8.vImageTic
            r3.setImageBitmap(r2)
            android.widget.ImageView r3 = r8.vImageTic
            int r4 = r8.backColor
            r3.setBackgroundColor(r4)
        L_0x00c1:
            return
        L_0x00c2:
            android.widget.TextView r3 = r8.tVar6Tic
            r3.setVisibility(r6)
            android.widget.CheckBox r3 = r8.chVar6Tic
            r3.setVisibility(r6)
            android.widget.TextView r3 = r8.tVar6Tic
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r5 = 5
            r4 = r4[r5]
            r3.setText(r4)
        L_0x00d6:
            android.widget.TextView r3 = r8.tVar5Tic
            r3.setVisibility(r6)
            android.widget.CheckBox r3 = r8.chVar5Tic
            r3.setVisibility(r6)
            android.widget.TextView r3 = r8.tVar5Tic
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r4 = r4[r7]
            r3.setText(r4)
        L_0x00e9:
            android.widget.TextView r3 = r8.tVar4Tic
            r3.setVisibility(r6)
            android.widget.CheckBox r3 = r8.chVar4Tic
            r3.setVisibility(r6)
            android.widget.TextView r3 = r8.tVar4Tic
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r5 = 3
            r4 = r4[r5]
            r3.setText(r4)
        L_0x00fd:
            android.widget.TextView r3 = r8.tVar3Tic
            r3.setVisibility(r6)
            android.widget.CheckBox r3 = r8.chVar3Tic
            r3.setVisibility(r6)
            android.widget.TextView r3 = r8.tVar3Tic
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r5 = 2
            r4 = r4[r5]
            r3.setText(r4)
            goto L_0x0038
        L_0x0113:
            java.lang.String r1 = java.lang.Integer.toString(r0)
            goto L_0x0083
        L_0x0119:
            android.widget.TextView r3 = r8.tOtvetYesTic
            int r4 = r8.redColor
            r3.setTextColor(r4)
            goto L_0x00a9
        L_0x0121:
            android.widget.TextView r3 = r8.tOtvetYesTic
            int r4 = r8.GreenText
            r3.setTextColor(r4)
            goto L_0x00a9
        L_0x0129:
            boolean r3 = r8.BROWSER_ERR
            if (r3 == 0) goto L_0x015b
            com.econavt.uniexam.ExamService2 r3 = r8.myService4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "RI"
            r4.<init>(r5)
            java.lang.String r5 = r8.WORK_NUM
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = ","
            java.lang.StringBuilder r4 = r4.append(r5)
            int[] r5 = r8.MASS_N_ERR_QST
            int r6 = r8.TEK_QST_IN_TICKET
            int r6 = r6 + -1
            r5 = r5[r6]
            java.lang.String r5 = java.lang.Integer.toString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.doSend(r4)
            goto L_0x00c1
        L_0x015b:
            com.econavt.uniexam.ExamService2 r3 = r8.myService4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "EII,"
            r4.<init>(r5)
            int r5 = r8.TEK_ALL_QST
            java.lang.String r5 = java.lang.Integer.toString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.doSend(r4)
            goto L_0x00c1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.econavt.uniexam.etic.set_ekran():void");
    }

    public void start_timer() {
        final Timer tmr22 = new Timer();
        final Handler uiHandler2 = new Handler();
        tmr22.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Handler handler = uiHandler2;
                final Timer timer = tmr22;
                handler.post(new Runnable() {
                    public void run() {
                        etic access$0 = etic.this;
                        access$0.ZADERJKA_t--;
                        if (etic.this.INI_BREAK_ON_ERR && etic.this.EXAMEN_BREAK_ON_ERR) {
                            timer.cancel();
                            if (etic.this.INI_WORK_ERR) {
                                etic.this.browse_err(false);
                            }
                        } else if (etic.this.END_EXAMEN) {
                            timer.cancel();
                            if (etic.this.INI_WORK_ERR) {
                                etic.this.browse_err(false);
                            }
                        } else if (etic.this.TEK_TIME <= 0) {
                            timer.cancel();
                            etic.this.EXAMEN_BREAK_ON_TIME = true;
                            if (etic.this.INI_CONTR_TIME && etic.this.ZADERJKA_t <= 0) {
                                etic.this.tTimeTic.setText(etic.this.get_Time(etic.this.TEK_TIME));
                            }
                            etic.this.set_resultat();
                            if (etic.this.INI_WORK_ERR) {
                                etic.this.browse_err(false);
                            }
                        } else if (etic.this.INI_CONTR_TIME) {
                            if (etic.this.ZADERJKA_t <= 0) {
                                etic.this.tTimeTic.setText(etic.this.get_Time(etic.this.TEK_TIME));
                            }
                            etic access$02 = etic.this;
                            access$02.TEK_TIME--;
                        }
                    }
                });
            }
        }, 0, this.interval);
    }

    /* access modifiers changed from: package-private */
    public void browse_err(boolean Add) {
        if (this.ERRORS != 0) {
            this.BROWSER_ERR = true;
            this.tNomerTicket.setText("");
            this.tNOtvetsTic.setText("");
            this.tNErrorsTic.setText("");
            this.tOtvetsTic.setText("Отвечено:");
            this.tErrorsTic.setText("Правильно:");
            this.bCloseTic.setEnabled(true);
            this.bCloseTic.setText("ОШИБКИ");
            this.bCloseTic.setTextColor(this.redColor);
            this.TEK_QST_IN_TICKET = 1;
            this.MASS_N_ERR_QST = new int[this.ERRORS];
            this.MASS_ERR_PRESSED = new int[this.ERRORS];
            int j = 0;
            int NoAns = this.NO_ANSWER;
            for (int i = 0; i < this.N_QST; i++) {
                if (this.OTVETS[i] != 5 && this.OTVETS[i] > 0) {
                    this.MASS_N_ERR_QST[j] = this.MASS_N_QST[i];
                    this.MASS_ERR_PRESSED[j] = this.MASS_PRESSED[i];
                    j++;
                }
            }
            if (!Add) {
                this.N_QST = j;
            } else {
                this.N_QST = this.ERRORS;
            }
            this.tNoAns.setText(Integer.toString(NoAns));
            this.tOtvetYesTic.setText(String.valueOf(Integer.toString(this.TEK_QST_IN_TICKET)) + " из " + Integer.toString(this.N_QST));
            this.myService4.doSendRX("RE" + this.WORK_NUM);
        }
    }

    /* access modifiers changed from: package-private */
    public void Add_err() {
        for (int i = 0; i < this.N_QST; i++) {
            if (this.OTVETS[i] == 0) {
                this.NO_ANSWER++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String get_Time(int times) {
        int ost = times % 3600;
        int iMin = ost / 60;
        int iSec = ost % 60;
        String ret_val = Integer.toString(times / 3600);
        String WrStr = Integer.toString(iMin);
        if (iMin < 10) {
            WrStr = "0" + WrStr;
        }
        String ret_val2 = String.valueOf(ret_val) + ":" + WrStr + ":";
        String WrStr2 = Integer.toString(iSec);
        if (iSec < 10) {
            WrStr2 = "0" + WrStr2;
        }
        return String.valueOf(ret_val2) + WrStr2;
    }

    /* access modifiers changed from: package-private */
    public void Clear_Otvets() {
        int cnt = this.OTVETS.length;
        for (int i = 0; i < cnt; i++) {
            this.OTVETS[i] = 0;
            this.MASS_PRAV[i] = 0;
            this.MASS_N_QST[i] = 0;
            this.MASS_PRESSED[i] = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void setOK() {
        if (!this.END_EXAMEN && checkYes()) {
            clearSelect();
            if (this.OTVETS[this.TEK_QST_IN_TICKET - 1] > 0) {
                Toast.makeText(this, "Ответ уже получен.", Toast.LENGTH_SHORT).show();
                return;
            }
            this.myService4.doSendRX("EKI" + this.WORK_NUM + "," + Integer.toString(this.THIS_OTVET) + "," + Integer.toString(this.TEK_QST_IN_TICKET - 1));
            this.ANSWERS++;
            this.tNOtvetsTic.setText(Integer.toString(this.ANSWERS));
            this.MASS_PRESSED[this.TEK_QST_IN_TICKET - 1] = this.THIS_OTVET;
            if (this.TEK_PR_OTVET == this.THIS_OTVET) {
                this.OTVETS[this.TEK_QST_IN_TICKET - 1] = 5;
            } else {
                this.OTVETS[this.TEK_QST_IN_TICKET - 1] = 1;
                this.ERRORS++;
                if (this.INI_SHOW_REZ) {
                    this.tNErrorsTic.setText(Integer.toString(this.ERRORS));
                } else {
                    this.tNErrorsTic.setText(" ");
                }
            }
            if (this.TEK_QST_IN_TICKET < this.N_QST) {
                this.TEK_QST_IN_TICKET++;
                if (!this.INI_BREAK_ON_ERR || this.ERRORS <= this.ERR_RATE_3) {
                    setNextVopros();
                }
            }
            if (this.INI_BREAK_ON_ERR && this.ERRORS > this.ERR_RATE_3) {
                set_resultat();
                this.myService4.doSendRX("ETE");
            }
            if (get_examen()) {
                set_resultat();
                this.myService4.doSendRX("ETE");
            }
            this.bPrevTic.setEnabled(false);
            this.bNextTic.setEnabled(false);
            this.bOKTic.setEnabled(false);
            set_timer2();
        }
    }

    public boolean get_examen() {
        for (int i = 0; i < this.N_QST; i++) {
            if (this.OTVETS[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public void set_resultat() {
        Add_err();
        this.tNoAns.setText(Integer.toString(this.NO_ANSWER));
        if (this.ERRORS + this.NO_ANSWER > this.ERR_RATE_3) {
            this.tResult.setTextColor(this.redColor);
            this.tResult.setText("Не сдан");
            if (this.M_SOUND) {
                MediaPlayer.create(this, R.raw.s5).start();
            }
            this.EXAMEN_BREAK_ON_ERR = true;
        }
        if (this.ERRORS + this.NO_ANSWER <= this.ERR_RATE_3) {
            this.tResult.setTextColor(this.GreenText);
            this.tResult.setText("Сдан");
            if (this.M_SOUND) {
                MediaPlayer.create(this, R.raw.tada).start();
            }
        }
        set_ocenka();
        this.END_EXAMEN = true;
    }

    public void set_ocenka() {
        if (this.INI_SDAN) {
            this.tOcenka.setText(" ");
            return;
        }
        if (this.ERRORS + this.NO_ANSWER > this.ERR_RATE_3) {
            this.tOcenka.setText("2");
        }
        if (this.ERRORS + this.NO_ANSWER > this.ERR_RATE_4 && this.ERRORS + this.NO_ANSWER <= this.ERR_RATE_3) {
            this.tOcenka.setText("3");
        }
        if (this.ERRORS + this.NO_ANSWER > this.ERR_RATE_5 && this.ERRORS + this.NO_ANSWER <= this.ERR_RATE_4) {
            this.tOcenka.setText("4");
        }
        if (this.ERRORS + this.NO_ANSWER <= this.ERR_RATE_5) {
            this.tOcenka.setText("5");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean checkYes() {
        if (this.chVar1Tic.isChecked()) {
            return true;
        }
        if (this.chVar2Tic.isChecked()) {
            return true;
        }
        if (this.chVar3Tic.isChecked()) {
            return true;
        }
        if (this.chVar4Tic.isChecked()) {
            return true;
        }
        if (this.chVar5Tic.isChecked()) {
            return true;
        }
        if (this.chVar6Tic.isChecked()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void clearSelect() {
        this.chVar1Tic.setChecked(false);
        this.chVar2Tic.setChecked(false);
        this.chVar3Tic.setChecked(false);
        this.chVar4Tic.setChecked(false);
        this.chVar5Tic.setChecked(false);
        this.chVar6Tic.setChecked(false);
        this.tAnswerTic.setText("");
    }

    /* access modifiers changed from: package-private */
    public void ErrClear() {
        this.ERRORS = 0;
        this.ANSWERS = 0;
        if (this.SELECTED_VARIANT_TICKETS == 3) {
            this.tNomerTicket.setText("Набор");
        } else {
            this.tNomerTicket.setText(Integer.toString(this.TEK_TICKET));
        }
        this.tNOtvetsTic.setText(Integer.toString(this.ANSWERS));
        this.tNErrorsTic.setText(Integer.toString(this.ERRORS));
    }

    /* access modifiers changed from: package-private */
    public void setPodskazka() {
        Toast.makeText(this, this.PODSKAZKA, Toast.LENGTH_LONG).show();
    }

    /* access modifiers changed from: package-private */
    public void setNextVopros() {
        if (this.BROWSER_ERR) {
            this.myService4.doSendRX("RN" + this.WORK_NUM + "," + Integer.toString(this.MASS_N_ERR_QST[this.TEK_QST_IN_TICKET - 1]));
        } else {
            String Tic = Integer.toString(this.TEK_TICKET);
            this.myService4.doSendRX("EIN" + this.WORK_NUM + "," + Tic + "," + Integer.toString(this.TEK_QST_IN_TICKET));
        }
        setNomVopros();
        this.bPrevTic.setEnabled(false);
        this.bNextTic.setEnabled(false);
        this.bOKTic.setEnabled(false);
        set_timer2();
    }

    /* access modifiers changed from: package-private */
    public void setNomVopros() {
        this.tOtvetYesTic.setText(String.valueOf(Integer.toString(this.TEK_QST_IN_TICKET)) + " из " + Integer.toString(this.N_QST));
        clearSelect();
    }

    /* access modifiers changed from: package-private */
    public void scroll(int n) {
        switch (this.NUM) {
            case 1:
                this.tVar1Tic.scrollBy(0, n);
                break;
            case 2:
                this.tVar2Tic.scrollBy(0, n);
                break;
            case 3:
                this.tVar3Tic.scrollBy(0, n);
                break;
            case 4:
                this.tVar4Tic.scrollBy(0, n);
                break;
            case 5:
                this.tVar5Tic.scrollBy(0, n);
                break;
            case 6:
                this.tVar6Tic.scrollBy(0, n);
                break;
            case MotionEventCompat.ACTION_HOVER_MOVE:
                this.tVoprMainTic.scrollBy(0, n);
                break;
        }
        this.ZADERJKA_t = this.pauza_t + 1;
    }

    /* access modifiers changed from: package-private */
    public void setNum() {
        switch (this.NUM) {
            case 1:
                this.tVoprMainTic.setBackgroundColor(this.zeroColor);
                this.tVar1Tic.setBackgroundColor(this.backColor);
                this.tVar2Tic.setBackgroundColor(this.zeroColor);
                this.tVar3Tic.setBackgroundColor(this.zeroColor);
                this.tVar4Tic.setBackgroundColor(this.zeroColor);
                this.tVar5Tic.setBackgroundColor(this.zeroColor);
                this.tVar6Tic.setBackgroundColor(this.zeroColor);
                return;
            case 2:
                this.tVoprMainTic.setBackgroundColor(this.zeroColor);
                this.tVar1Tic.setBackgroundColor(this.zeroColor);
                this.tVar2Tic.setBackgroundColor(this.backColor);
                this.tVar3Tic.setBackgroundColor(this.zeroColor);
                this.tVar4Tic.setBackgroundColor(this.zeroColor);
                this.tVar5Tic.setBackgroundColor(this.zeroColor);
                this.tVar6Tic.setBackgroundColor(this.zeroColor);
                return;
            case 3:
                this.tVoprMainTic.setBackgroundColor(this.zeroColor);
                this.tVar1Tic.setBackgroundColor(this.zeroColor);
                this.tVar2Tic.setBackgroundColor(this.zeroColor);
                this.tVar3Tic.setBackgroundColor(this.backColor);
                this.tVar4Tic.setBackgroundColor(this.zeroColor);
                this.tVar5Tic.setBackgroundColor(this.zeroColor);
                this.tVar6Tic.setBackgroundColor(this.zeroColor);
                return;
            case 4:
                this.tVoprMainTic.setBackgroundColor(this.zeroColor);
                this.tVar1Tic.setBackgroundColor(this.zeroColor);
                this.tVar2Tic.setBackgroundColor(this.zeroColor);
                this.tVar3Tic.setBackgroundColor(this.zeroColor);
                this.tVar4Tic.setBackgroundColor(this.backColor);
                this.tVar5Tic.setBackgroundColor(this.zeroColor);
                this.tVar6Tic.setBackgroundColor(this.zeroColor);
                return;
            case 5:
                this.tVoprMainTic.setBackgroundColor(this.zeroColor);
                this.tVar1Tic.setBackgroundColor(this.zeroColor);
                this.tVar2Tic.setBackgroundColor(this.zeroColor);
                this.tVar3Tic.setBackgroundColor(this.zeroColor);
                this.tVar4Tic.setBackgroundColor(this.zeroColor);
                this.tVar5Tic.setBackgroundColor(this.backColor);
                this.tVar6Tic.setBackgroundColor(this.zeroColor);
                return;
            case 6:
                this.tVoprMainTic.setBackgroundColor(this.zeroColor);
                this.tVar1Tic.setBackgroundColor(this.zeroColor);
                this.tVar2Tic.setBackgroundColor(this.zeroColor);
                this.tVar3Tic.setBackgroundColor(this.zeroColor);
                this.tVar4Tic.setBackgroundColor(this.zeroColor);
                this.tVar5Tic.setBackgroundColor(this.zeroColor);
                this.tVar6Tic.setBackgroundColor(this.backColor);
                return;
            case MotionEventCompat.ACTION_HOVER_MOVE:
                this.tVoprMainTic.setBackgroundColor(this.backColor);
                this.tVar1Tic.setBackgroundColor(this.zeroColor);
                this.tVar2Tic.setBackgroundColor(this.zeroColor);
                this.tVar3Tic.setBackgroundColor(this.zeroColor);
                this.tVar4Tic.setBackgroundColor(this.zeroColor);
                this.tVar5Tic.setBackgroundColor(this.zeroColor);
                this.tVar6Tic.setBackgroundColor(this.zeroColor);
                return;
            default:
                return;
        }
    }

    public void set_timer2() {
        final Timer tmr3 = new Timer();
        final Handler uiHandler3 = new Handler();
        tmr3.schedule(new TimerTask() {
            public void run() {
                Handler handler = uiHandler3;
                final Timer timer = tmr3;
                handler.post(new Runnable() {
                    public void run() {
                        timer.cancel();
                        etic.this.bPrevTic.setEnabled(true);
                        etic.this.bNextTic.setEnabled(true);
                        etic.this.bOKTic.setEnabled(true);
                    }
                });
            }
        }, 1500);
    }

    public void set_timer1() {
        final Timer tmr3 = new Timer();
        final Handler uiHandler = new Handler();
        tmr3.schedule(new TimerTask() {
            public void run() {
                Handler handler = uiHandler;
                final Timer timer = tmr3;
                handler.post(new Runnable() {
                    public void run() {
                        etic.this.start1();
                        timer.cancel();
                    }
                });
            }
        }, 400);
    }

    public void start1() {
        int WrHeight = (this.HEIGHT - this.frameLayout2Tic.getHeight()) - this.linearLayout1Tic.getHeight();
        int WrInt = (WrHeight - 30) / 3;
        this.chVar1Tic.setHeight(WrInt / 2);
        this.chVar2Tic.setHeight(WrInt / 2);
        this.tZap1.setHeight(WrInt / 4);
        this.tZap1.setVisibility(View.INVISIBLE);
        this.tZap2.setHeight(WrInt / 4);
        this.tZap2.setVisibility(View.INVISIBLE);
        this.tZap3.setHeight(WrInt / 4);
        this.tZap3.setVisibility(View.INVISIBLE);
        this.tZap4.setHeight(WrInt / 4);
        this.tZap4.setVisibility(View.INVISIBLE);
        this.chVar3Tic.setHeight(WrInt / 2);
        this.chVar4Tic.setHeight(WrInt / 2);
        this.chVar5Tic.setHeight(WrInt / 2);
        this.chVar6Tic.setHeight(WrInt / 2);
        int WrInt2 = WrInt - 30;
        this.tVar1Tic.setHeight(WrInt2);
        this.tVar2Tic.setHeight(WrInt2);
        this.tVar3Tic.setHeight(WrInt2);
        this.tVar4Tic.setHeight(WrInt2);
        this.tVar5Tic.setHeight(WrInt2);
        this.tVar6Tic.setHeight(WrInt2);
        int WrInt3 = ((WrHeight - this.linearLayout2Tic.getHeight()) - this.tVoprMainTic.getHeight()) - 90;
        ViewGroup.LayoutParams params = this.vImageTic.getLayoutParams();
        params.height = this.IMAGE_HEIGHT;
        int WrInt4 = (int) (((double) this.IMAGE_HEIGHT) * 1.5d);
        params.width = WrInt4;
        this.vImageTic.setLayoutParams(params);
        int WrWidth = (((this.WIDTH - WrInt4) - 40) / 2) - this.chVar1Tic.getWidth();
        this.tVar1Tic.setWidth(WrWidth);
        this.tVar2Tic.setWidth(WrWidth);
        this.tVar3Tic.setWidth(WrWidth);
        this.tVar4Tic.setWidth(WrWidth);
        this.tVar5Tic.setWidth(WrWidth);
        this.tVar6Tic.setWidth(WrWidth);
        int WrInt5 = (WrInt4 - this.tVoprosTic.length()) / 2;
        this.tVar1Tic.setText(" ");
        if (this.HELP_YES) {
            this.bVoprTic.setEnabled(true);
        } else {
            this.bVoprTic.setEnabled(false);
        }
        this.tAnswerTic.setText("");
    }

    public void onClickCheck(int num) {
        switch (num) {
            case 1:
                this.chVar1Tic.setChecked(true);
                this.chVar2Tic.setChecked(false);
                this.chVar3Tic.setChecked(false);
                this.chVar4Tic.setChecked(false);
                this.chVar5Tic.setChecked(false);
                this.chVar6Tic.setChecked(false);
                break;
            case 2:
                this.chVar1Tic.setChecked(false);
                this.chVar2Tic.setChecked(true);
                this.chVar3Tic.setChecked(false);
                this.chVar4Tic.setChecked(false);
                this.chVar5Tic.setChecked(false);
                this.chVar6Tic.setChecked(false);
                break;
            case 3:
                this.chVar1Tic.setChecked(false);
                this.chVar2Tic.setChecked(false);
                this.chVar3Tic.setChecked(true);
                this.chVar4Tic.setChecked(false);
                this.chVar5Tic.setChecked(false);
                this.chVar6Tic.setChecked(false);
                break;
            case 4:
                this.chVar1Tic.setChecked(false);
                this.chVar2Tic.setChecked(false);
                this.chVar3Tic.setChecked(false);
                this.chVar4Tic.setChecked(true);
                this.chVar5Tic.setChecked(false);
                this.chVar6Tic.setChecked(false);
                break;
            case 5:
                this.chVar1Tic.setChecked(false);
                this.chVar2Tic.setChecked(false);
                this.chVar3Tic.setChecked(false);
                this.chVar4Tic.setChecked(false);
                this.chVar5Tic.setChecked(true);
                this.chVar6Tic.setChecked(false);
                break;
            case 6:
                this.chVar1Tic.setChecked(false);
                this.chVar2Tic.setChecked(false);
                this.chVar3Tic.setChecked(false);
                this.chVar4Tic.setChecked(false);
                this.chVar5Tic.setChecked(false);
                this.chVar6Tic.setChecked(true);
                break;
        }
        this.tAnswerTic.setText(String.valueOf(Integer.toString(num)) + "?");
        this.THIS_OTVET = num;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 || keyCode == 3 || keyCode == 16908332) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
