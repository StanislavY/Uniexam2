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
//import android.support.p000v4.view.MotionEventCompat;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
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

public class etema extends Activity {
    public static final String BROADCAST_ACTION = "com.econavt.uniexam";
    public static final String SHARED_PREFS_NAME = "sett.xml";
    static TimerTask tTask;
    boolean ALLOWED = false;
    boolean ALLOWED_NEXT = false;
    int ALL_QST;
    public int ANSWERS = 100;
    boolean BROWSER_ERR = false;
    public String CommString;
    boolean END_EXAMEN = false;
    int END_QST;
    public int ERRORS = 100;
    int ERR_RATE_3;
    int ERR_RATE_4;
    int ERR_RATE_5;
    boolean EXAMEN_BREAK = false;
    boolean EXAMEN_BREAK_ON_ERR = false;
    boolean EXAMEN_BREAK_ON_TIME = false;
    boolean FIRST = true;
    boolean FOR_ALLOWED = false;
    boolean FOR_ALLOWED_NEXT = false;
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
    public int MAXVOPR = 3;
    boolean M_SOUND;
    public int NOMVOPR = 0;
    int NO_ANSWER;
    public int NUM = 1;
    int N_QST;
    int N_TICKET;
    public int[] OTVETS;
    public String PODSKAZKA;
    public boolean STARTING = false;
    int START_QST;
    int START_WIDTH;
    public boolean Set_ImTem = true;
    int TEK_ALL_QST = 0;
    String TEK_HELP;
    String[] TEK_MAS_OTVET = {"", "", "", "", "", ""};
    String TEK_NAME_TEMA;
    int TEK_NUM_VAR_OTVETOV;
    String TEK_PICT;
    int TEK_PR_OTVET;
    int TEK_QST;
    String TEK_QUEST;
    int TEK_TIME;
    int THIS_OTVET = 0;
    int WIDTH;
    String WORK_FAM;
    int WORK_HEIGTH;
    String WORK_NAME;
    String WORK_NUM;
    String WORK_OTCH;
    int WORK_WIDTH;
    String WremStr;
    int ZADERJKA_t = 0;
    Button bCloseTem;
    ImageButton bNextTem;
    Button bOKTem;
    ImageButton bPrevTem;
    ImageButton bVoprTem;
    int backColor = -858993460;
    int blackColor = -16777216;
    boolean bound = false;
    BroadcastReceiver br3;
    CheckBox chVar1Tem;
    CheckBox chVar2Tem;
    CheckBox chVar3Tem;
    CheckBox chVar4Tem;
    CheckBox chVar5Tem;
    CheckBox chVar6Tem;
    FrameLayout frameLayout1Tem;
    FrameLayout frameLayout2Tem;
    ImageButton iBuDownTem;
    ImageButton iBuUpTem;
    Intent intent3;
    long interval = 1000;
    LinearLayout linearLayout1Tem;
    LinearLayout linearLayout2Tem;
    ExamServiceRX myService3;
    int pauza_t = 5;
    int redColor = -5636096;
    ServiceConnection sConn3;
    SharedPreferences sPref;
    TextView tAnswerTem;
    TextView tErrorsTem;
    TextView tNErrorsTem;
    TextView tNOtvetsTem;
    TextView tNoAns;
    TextView tOcenka;
    TextView tOtvetYesTem;
    TextView tOtvetsTem;
    TextView tResult;
    TextView tTema;
    TextView tTimeTicTem;
    TextView tVar1Tem;
    TextView tVar2Tem;
    TextView tVar3Tem;
    TextView tVar4Tem;
    TextView tVar5Tem;
    TextView tVar6Tem;
    TextView tVariantTem;
    TextView tVoprMainTem;
    TextView tVoprosTem;
    TextView tZap1T;
    TextView tZap2T;
    TextView tZap3T;
    TextView tZap4T;
    public Timer tmr;
    public Timer tmr2;
    Timer tmr3;
    public Timer tmr4;
    ImageView vImageTem;
    int zeroColor = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etema);
        this.bCloseTem = (Button) findViewById(R.id.bCloseTem);
        this.iBuUpTem = (ImageButton) findViewById(R.id.iBuUpTem);
        this.iBuDownTem = (ImageButton) findViewById(R.id.iBuDownTem);
        this.bOKTem = (Button) findViewById(R.id.bOKTem);
        this.bVoprTem = (ImageButton) findViewById(R.id.bVoprTem);
        this.bPrevTem = (ImageButton) findViewById(R.id.bPrevTem);
        this.bNextTem = (ImageButton) findViewById(R.id.bNextTem);
        this.frameLayout1Tem = (FrameLayout) findViewById(R.id.frameLayout1Tem);
        this.frameLayout2Tem = (FrameLayout) findViewById(R.id.frameLayout2Tem);
        this.linearLayout1Tem = (LinearLayout) findViewById(R.id.linearLayout1Tem);
        this.linearLayout2Tem = (LinearLayout) findViewById(R.id.linearLayout2Tem);
        this.tTema = (TextView) findViewById(R.id.tTema);
        this.tTimeTicTem = (TextView) findViewById(R.id.tTimeTicTem);
        this.tVoprosTem = (TextView) findViewById(R.id.tVoprosTem);
        this.tVariantTem = (TextView) findViewById(R.id.tVariantTem);
        this.tVoprMainTem = (TextView) findViewById(R.id.tVoprMainTem);
        this.tOtvetsTem = (TextView) findViewById(R.id.tOtvetsTem);
        this.tNOtvetsTem = (TextView) findViewById(R.id.tNOtvetsTem);
        this.tErrorsTem = (TextView) findViewById(R.id.tErrorsTem);
        this.tNErrorsTem = (TextView) findViewById(R.id.tNErrorsTem);
        this.tOtvetYesTem = (TextView) findViewById(R.id.tOtvetYesTem);
        this.tAnswerTem = (TextView) findViewById(R.id.tAnswerTem);
        this.tResult = (TextView) findViewById(R.id.tResult);
        this.tOcenka = (TextView) findViewById(R.id.tOcenka);
        this.tNoAns = (TextView) findViewById(R.id.tNoAns);
        this.chVar1Tem = (CheckBox) findViewById(R.id.chVar1Tem);
        this.chVar2Tem = (CheckBox) findViewById(R.id.chVar2Tem);
        this.chVar3Tem = (CheckBox) findViewById(R.id.chVar3Tem);
        this.chVar4Tem = (CheckBox) findViewById(R.id.chVar4Tem);
        this.chVar5Tem = (CheckBox) findViewById(R.id.chVar5Tem);
        this.chVar6Tem = (CheckBox) findViewById(R.id.chVar6Tem);
        this.vImageTem = (ImageView) findViewById(R.id.vImageTem);
        this.tVar1Tem = (TextView) findViewById(R.id.tVar1Tem);
        this.tVar2Tem = (TextView) findViewById(R.id.tVar2Tem);
        this.tVar3Tem = (TextView) findViewById(R.id.tVar3Tem);
        this.tVar4Tem = (TextView) findViewById(R.id.tVar4Tem);
        this.tVar5Tem = (TextView) findViewById(R.id.tVar5Tem);
        this.tVar6Tem = (TextView) findViewById(R.id.tVar6Tem);
        this.tZap1T = (TextView) findViewById(R.id.tZap1T);
        this.tZap2T = (TextView) findViewById(R.id.tZap2T);
        this.tZap3T = (TextView) findViewById(R.id.tZap3T);
        this.tZap4T = (TextView) findViewById(R.id.tZap4T);
        View.OnClickListener oclBtn = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.chVar5Tem:
                        if (etema.this.chVar5Tem.isChecked()) {
                            etema.this.onClickCheck(5);
                            return;
                        } else {
                            etema.this.chVar5Tem.setChecked(true);
                            return;
                        }
                    case R.id.chVar4Tem:
                        if (etema.this.chVar4Tem.isChecked()) {
                            etema.this.onClickCheck(4);
                            return;
                        } else {
                            etema.this.chVar4Tem.setChecked(true);
                            return;
                        }
                    case R.id.chVar6Tem:
                        if (etema.this.chVar6Tem.isChecked()) {
                            etema.this.onClickCheck(6);
                            return;
                        } else {
                            etema.this.chVar6Tem.setChecked(true);
                            return;
                        }
                    case R.id.bPrevTem:
                        etema.this.Set_ImTem = true;
                        if (etema.this.INI_SELECT_QST && etema.this.NOMVOPR != 1) {
                            etema etema = etema.this;
                            etema.NOMVOPR--;
                            etema etema2 = etema.this;
                            etema2.TEK_QST--;
                            etema.this.setNextVopros();
                            return;
                        }
                        return;
                    case R.id.bNextTem:
                        etema.this.Set_ImTem = true;
                        if (etema.this.INI_SELECT_QST && etema.this.NOMVOPR != etema.this.MAXVOPR) {
                            etema.this.NOMVOPR++;
                            etema.this.TEK_QST++;
                            etema.this.setNextVopros();
                            return;
                        }
                        return;
                    case R.id.iBuUpTem:
                        etema.this.scroll(10);
                        return;
                    case R.id.iBuDownTem:
                        etema.this.scroll(-10);
                        return;
                    case R.id.chVar1Tem:
                        if (etema.this.chVar1Tem.isChecked()) {
                            etema.this.onClickCheck(1);
                            return;
                        } else {
                            etema.this.chVar1Tem.setChecked(true);
                            return;
                        }
                    case R.id.tVoprMainTem:
                        etema.this.NUM = 7;
                        etema.this.setNum();
                        return;
                    case R.id.bVoprTem:
                        if (etema.this.HELP_YES) {
                            etema.this.setPodskazka();
                            return;
                        }
                        return;
                    case R.id.bOKTem:
                        etema.this.setOK();
                        return;
                    case R.id.bCloseTem:
                        if (etema.this.BROWSER_ERR) {
                            etema.this.NOMVOPR = 1;
                            etema.this.myService3.doSend("RN" + etema.this.WORK_NUM + "," + Integer.toString(etema.this.MASS_N_ERR_QST[etema.this.NOMVOPR - 1]));
                            etema.this.bOKTem.setEnabled(false);
                            etema.this.bCloseTem.setEnabled(false);
                            return;
                        }
                        etema.this.myService3.doSend("ETX,1");
                        etema.this.EXAMEN_BREAK_ON_ERR = true;
                        etema.this.finish();
                        return;
                    case R.id.chVar2Tem:
                        if (etema.this.chVar2Tem.isChecked()) {
                            etema.this.onClickCheck(2);
                            return;
                        } else {
                            etema.this.chVar2Tem.setChecked(true);
                            return;
                        }
                    case R.id.chVar3Tem:
                        if (etema.this.chVar3Tem.isChecked()) {
                            etema.this.onClickCheck(3);
                            return;
                        } else {
                            etema.this.chVar3Tem.setChecked(true);
                            return;
                        }
                    case R.id.vImageTem:
                        etema.this.click_im();
                        return;
                    case R.id.tVar1Tem:
                        etema.this.NUM = 1;
                        etema.this.setNum();
                        return;
                    case R.id.tVar3Tem:
                        etema.this.NUM = 3;
                        etema.this.setNum();
                        return;
                    case R.id.tVar5Tem:
                        etema.this.NUM = 5;
                        etema.this.setNum();
                        return;
                    case R.id.tVar4Tem:
                        etema.this.NUM = 4;
                        etema.this.setNum();
                        return;
                    case R.id.tVar6Tem:
                        etema.this.NUM = 6;
                        etema.this.setNum();
                        return;
                    case R.id.tVar2Tem:
                        etema.this.NUM = 2;
                        etema.this.setNum();
                        return;
                    default:
                        return;
                }
            }
        };
        this.bCloseTem.setOnClickListener(oclBtn);
        this.iBuUpTem.setOnClickListener(oclBtn);
        this.iBuDownTem.setOnClickListener(oclBtn);
        this.bOKTem.setOnClickListener(oclBtn);
        this.bVoprTem.setOnClickListener(oclBtn);
        this.bPrevTem.setOnClickListener(oclBtn);
        this.bNextTem.setOnClickListener(oclBtn);
        this.tVoprMainTem.setOnClickListener(oclBtn);
        this.tVar1Tem.setOnClickListener(oclBtn);
        this.tVar2Tem.setOnClickListener(oclBtn);
        this.tVar3Tem.setOnClickListener(oclBtn);
        this.tVar4Tem.setOnClickListener(oclBtn);
        this.tVar5Tem.setOnClickListener(oclBtn);
        this.tVar6Tem.setOnClickListener(oclBtn);
        this.chVar1Tem.setOnClickListener(oclBtn);
        this.chVar2Tem.setOnClickListener(oclBtn);
        this.chVar3Tem.setOnClickListener(oclBtn);
        this.chVar4Tem.setOnClickListener(oclBtn);
        this.chVar5Tem.setOnClickListener(oclBtn);
        this.chVar6Tem.setOnClickListener(oclBtn);
        this.vImageTem.setOnClickListener(oclBtn);
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
        this.HEIGHT = this.sPref.getInt("w_height", 480);
        this.WORK_HEIGTH = this.sPref.getInt("FontSize", 10);
        this.WORK_HEIGTH -= 2;
        this.IMAGE_HEIGHT = this.sPref.getInt("ImageHeight", 235);
        this.tVoprMainTem.setTextSize((float) this.WORK_HEIGTH);
        this.tVar1Tem.setTextSize((float) this.WORK_HEIGTH);
        this.tVar2Tem.setTextSize((float) this.WORK_HEIGTH);
        this.tVar3Tem.setTextSize((float) this.WORK_HEIGTH);
        this.tVar4Tem.setTextSize((float) this.WORK_HEIGTH);
        this.tVar5Tem.setTextSize((float) this.WORK_HEIGTH);
        this.tVar6Tem.setTextSize((float) this.WORK_HEIGTH);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().hide();
        this.NUM = 0;
        set_timer1();
        if (!this.STARTING) {
            this.br3 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    byte[] byteArray = intent.getByteArrayExtra(MainActivity.PARAM_BUF);
                    if (byteArray[0] == 45) {
                        etema.this.CommString = new String(byteArray);
                        etema.this.set_comand(etema.this.CommString);
                    } else if (byteArray[0] != 61) {
                        Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                        etema.this.vImageTem.setBackgroundColor(etema.this.zeroColor);
                        etema.this.vImageTem.setImageBitmap(bit);
                    }
                }
            };
            registerReceiver(this.br3, new IntentFilter("com.econavt.uniexam"));
        }
        this.intent3 = new Intent(this, ExamServiceRX.class);
        this.sConn3 = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                if (!etema.this.STARTING) {
                    etema.this.myService3 = ((ExamServiceRX.MyBinder) binder).getService();
                    etema.this.bound = true;
                    etema.this.END_EXAMEN = false;
                    for (int i = 500; i > 0; i--) {
                        etema.this.WremStr = "proba 1 proba 1 proba 1 proba 1 proba 1 ";
                    }
                    etema.this.myService3.doSend("ETP");
                }
            }

            public void onServiceDisconnected(ComponentName name) {
                etema.this.bound = false;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void click_im() {
        int WrY;
        ViewGroup.LayoutParams params = this.vImageTem.getLayoutParams();
        if (this.Set_ImTem) {
            this.Set_ImTem = false;
            WrY = this.HEIGHT;
        } else {
            this.Set_ImTem = true;
            WrY = this.IMAGE_HEIGHT;
        }
        params.height = WrY;
        params.width = (int) (((double) WrY) * 1.5d);
        this.vImageTem.setLayoutParams(params);
        this.vImageTem.bringToFront();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        bindService(this.intent3, this.sConn3, 0);
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

    public void set_comand(String str) {
        String Comm = str;
        byte[] buffer = str.getBytes();
        if (buffer[0] == 45) {
            switch (buffer[1]) {
                case 48:
                    String[] fields_main = Comm.split("#");
                    this.EXAMEN_BREAK_ON_TIME = false;
                    this.EXAMEN_BREAK_ON_ERR = false;
                    this.END_EXAMEN = false;
                    this.BROWSER_ERR = false;
                    this.NO_ANSWER = 0;
                    this.bOKTem.setEnabled(true);
                    this.bCloseTem.setEnabled(false);
                    this.tOtvetsTem.setText("Ответов:");
                    this.tErrorsTem.setText("Ошибок:");
                    this.tNoAns.setText("0");
                    if (Integer.parseInt(fields_main[1]) == 1) {
                        this.INI_SELECT_QST = true;
                    } else {
                        this.INI_SELECT_QST = false;
                    }
                    if (Integer.parseInt(fields_main[2]) == 1) {
                        this.INI_SHOW_REZ = true;
                    } else {
                        this.INI_SHOW_REZ = false;
                    }
                    if (Integer.parseInt(fields_main[3]) == 1) {
                        this.INI_ENABLE_HINT = true;
                    } else {
                        this.INI_ENABLE_HINT = false;
                    }
                    if (Integer.parseInt(fields_main[4]) == 1) {
                        this.INI_CONTR_TIME = true;
                    } else {
                        this.INI_CONTR_TIME = false;
                    }
                    if (Integer.parseInt(fields_main[5]) == 1) {
                        this.INI_WORK_ERR = true;
                    } else {
                        this.INI_WORK_ERR = false;
                    }
                    if (Integer.parseInt(fields_main[6]) == 1) {
                        this.INI_BREAK_ON_ERR = true;
                    } else {
                        this.INI_BREAK_ON_ERR = false;
                    }
                    if (Integer.parseInt(fields_main[7]) == 1) {
                        this.INI_SDAN = true;
                    } else {
                        this.INI_SDAN = false;
                    }
                    this.ERR_RATE_3 = Integer.parseInt(fields_main[8]);
                    this.ERR_RATE_4 = Integer.parseInt(fields_main[9]);
                    this.ERR_RATE_5 = Integer.parseInt(fields_main[10]);
                    if (this.INI_ENABLE_HINT) {
                        this.bVoprTem.setEnabled(true);
                    } else {
                        this.bVoprTem.setEnabled(false);
                    }
                    for (int i = 500; i > 0; i--) {
                        this.WremStr = "proba 1 proba 1 proba 1 proba 1 proba 1 ";
                    }
                    this.myService3.doSend("ET" + this.WORK_NUM + ",-1");
                    return;
                case 49:
                    String[] fields_main2 = Comm.split("#");
                    this.TEK_NAME_TEMA = fields_main2[1];
                    this.START_QST = Integer.parseInt(fields_main2[2]);
                    this.END_QST = Integer.parseInt(fields_main2[3]);
                    this.TEK_QST = Integer.parseInt(fields_main2[4]);
                    this.NOMVOPR = (this.TEK_QST - this.START_QST) + 1;
                    this.MAXVOPR = this.END_QST - this.START_QST;
                    this.TEK_QUEST = fields_main2[5];
                    this.TEK_PR_OTVET = Integer.parseInt(fields_main2[6]);
                    this.TEK_PICT = fields_main2[7];
                    this.TEK_HELP = fields_main2[8];
                    this.PODSKAZKA = fields_main2[9];
                    if (this.PODSKAZKA.length() == 0) {
                        this.bVoprTem.setEnabled(false);
                    } else if (this.INI_ENABLE_HINT) {
                        this.bVoprTem.setEnabled(true);
                    } else {
                        this.bVoprTem.setEnabled(false);
                    }
                    this.TEK_TIME = Integer.parseInt(fields_main2[10]);
                    this.TEK_ALL_QST = Integer.parseInt(fields_main2[11]);
                    if (this.FIRST) {
                        this.OTVETS = new int[this.MAXVOPR];
                        this.MASS_PRAV = new int[this.MAXVOPR];
                        this.MASS_N_QST = new int[this.MAXVOPR];
                        this.MASS_PRESSED = new int[this.MAXVOPR];
                        Clear_Otvets();
                        this.EXAMEN_BREAK_ON_TIME = false;
                        if (this.TEK_TIME > 0) {
                            start_timer();
                        }
                        this.FIRST = false;
                    }
                    this.MASS_PRAV[this.NOMVOPR - 1] = this.TEK_PR_OTVET;
                    this.MASS_N_QST[this.NOMVOPR - 1] = this.TEK_ALL_QST;
                    String[] fields_next = fields_main2[12].split("@");
                    this.TEK_NUM_VAR_OTVETOV = Integer.parseInt(fields_next[0]);
                    for (int i2 = 0; i2 < this.TEK_NUM_VAR_OTVETOV; i2++) {
                        this.TEK_MAS_OTVET[i2] = fields_next[i2 + 1];
                    }
                    set_ekran();
                    return;
                case 52:
                    String[] fields_main3 = Comm.split("#");
                    this.TEK_QUEST = fields_main3[1];
                    this.TEK_PR_OTVET = Integer.parseInt(fields_main3[2]);
                    this.TEK_PICT = fields_main3[3];
                    this.TEK_HELP = fields_main3[4];
                    String[] fields_next2 = fields_main3[5].split("@");
                    this.TEK_NUM_VAR_OTVETOV = Integer.parseInt(fields_next2[0]);
                    for (int i3 = 0; i3 < this.TEK_NUM_VAR_OTVETOV; i3++) {
                        this.TEK_MAS_OTVET[i3] = fields_next2[i3 + 1];
                    }
                    this.INI_SHOW_REZ = false;
                    this.INI_SELECT_QST = true;
                    set_ekran();
                    return;
                case 53:
                    this.BROWSER_ERR = false;
                    this.bCloseTem.setEnabled(true);
                    this.bCloseTem.setTextColor(this.blackColor);
                    this.bCloseTem.setText("Выход");
                    return;
                default:
                    return;
            }
        }
    }

    public void set_timer3() {
        this.tmr3 = new Timer();
        tTask = new TimerTask() {
            public void run() {
                etema.this.tmr3.cancel();
                etema.tTask.cancel();
            }
        };
        this.tmr3.schedule(tTask, 200);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x00a4, code lost:
        switch(r8.OTVETS[r8.NOMVOPR - 1]) {
            case 1: goto L_0x011e;
            case 5: goto L_0x0126;
            default: goto L_0x00a7;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00a7, code lost:
        r8.tOtvetYesTem.setTextColor(r8.blackColor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00ae, code lost:
        setNomVopros();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00b7, code lost:
        if (r8.TEK_PICT.length() != 0) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00b9, code lost:
        r8.vImageTem.setImageBitmap((android.graphics.Bitmap) null);
        r8.vImageTem.setBackgroundColor(r8.backColor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00db, code lost:
        r8.tVar5Tem.setVisibility(0);
        r8.chVar5Tem.setVisibility(0);
        r8.tVar5Tem.setText(r8.TEK_MAS_OTVET[4]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ee, code lost:
        r8.tVar4Tem.setVisibility(0);
        r8.chVar4Tem.setVisibility(0);
        r8.tVar4Tem.setText(r8.TEK_MAS_OTVET[3]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0102, code lost:
        r8.tVar3Tem.setVisibility(0);
        r8.chVar3Tem.setVisibility(0);
        r8.tVar3Tem.setText(r8.TEK_MAS_OTVET[2]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0118, code lost:
        r1 = java.lang.Integer.toString(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x011e, code lost:
        r8.tOtvetYesTem.setTextColor(r8.redColor);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0126, code lost:
        r8.tOtvetYesTem.setTextColor(r8.GreenText);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0130, code lost:
        if (r8.BROWSER_ERR == false) goto L_0x0160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0132, code lost:
        r8.myService3.doSend("RI" + r8.WORK_NUM + "," + java.lang.Integer.toString(r8.MASS_N_ERR_QST[r8.NOMVOPR - 1]));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0160, code lost:
        r8.myService3.doSend("ETI" + r8.WORK_NUM + ",-1");
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
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x003d, code lost:
        r8.tVar2Tem.setText(r8.TEK_MAS_OTVET[1]);
        r8.tVar1Tem.setText(r8.TEK_MAS_OTVET[0]);
        r8.tOtvetYesTem.setText(java.lang.String.valueOf(java.lang.Integer.toString(r8.NOMVOPR)) + " из " + java.lang.Integer.toString(r8.MAXVOPR));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x007a, code lost:
        if (r8.BROWSER_ERR == false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x007c, code lost:
        r0 = r8.MASS_ERR_PRESSED[r8.NOMVOPR - 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0084, code lost:
        if (r0 != 0) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0086, code lost:
        r1 = "--";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0088, code lost:
        r8.tNOtvetsTem.setText(r1);
        r8.tNErrorsTem.setText(java.lang.Integer.toString(r8.TEK_PR_OTVET));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x009a, code lost:
        if (r8.INI_SHOW_REZ == false) goto L_0x00ae;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void set_ekran() {
        /*
            r8 = this;
            r7 = 4
            r6 = 0
            android.widget.TextView r3 = r8.tVar3Tem
            r3.setVisibility(r7)
            android.widget.TextView r3 = r8.tVar4Tem
            r3.setVisibility(r7)
            android.widget.TextView r3 = r8.tVar5Tem
            r3.setVisibility(r7)
            android.widget.TextView r3 = r8.tVar6Tem
            r3.setVisibility(r7)
            android.widget.CheckBox r3 = r8.chVar3Tem
            r3.setVisibility(r7)
            android.widget.CheckBox r3 = r8.chVar4Tem
            r3.setVisibility(r7)
            android.widget.CheckBox r3 = r8.chVar5Tem
            r3.setVisibility(r7)
            android.widget.CheckBox r3 = r8.chVar6Tem
            r3.setVisibility(r7)
            android.widget.TextView r3 = r8.tTema
            java.lang.String r4 = r8.TEK_NAME_TEMA
            r3.setText(r4)
            android.widget.TextView r3 = r8.tVoprMainTem
            java.lang.String r4 = r8.TEK_QUEST
            r3.setText(r4)
            int r3 = r8.TEK_NUM_VAR_OTVETOV
            switch(r3) {
                case 3: goto L_0x0102;
                case 4: goto L_0x00ee;
                case 5: goto L_0x00db;
                case 6: goto L_0x00c7;
                default: goto L_0x003d;
            }
        L_0x003d:
            android.widget.TextView r3 = r8.tVar2Tem
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r5 = 1
            r4 = r4[r5]
            r3.setText(r4)
            android.widget.TextView r3 = r8.tVar1Tem
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r4 = r4[r6]
            r3.setText(r4)
            android.widget.TextView r3 = r8.tOtvetYesTem
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r5 = r8.NOMVOPR
            java.lang.String r5 = java.lang.Integer.toString(r5)
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r4.<init>(r5)
            java.lang.String r5 = " из "
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = r8.MAXVOPR
            java.lang.String r5 = java.lang.Integer.toString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.setText(r4)
            boolean r3 = r8.BROWSER_ERR
            if (r3 == 0) goto L_0x0098
            int[] r3 = r8.MASS_ERR_PRESSED
            int r4 = r8.NOMVOPR
            int r4 = r4 + -1
            r0 = r3[r4]
            if (r0 != 0) goto L_0x0118
            java.lang.String r1 = "--"
        L_0x0088:
            android.widget.TextView r3 = r8.tNOtvetsTem
            r3.setText(r1)
            android.widget.TextView r3 = r8.tNErrorsTem
            int r4 = r8.TEK_PR_OTVET
            java.lang.String r4 = java.lang.Integer.toString(r4)
            r3.setText(r4)
        L_0x0098:
            boolean r3 = r8.INI_SHOW_REZ
            if (r3 == 0) goto L_0x00ae
            int[] r3 = r8.OTVETS
            int r4 = r8.NOMVOPR
            int r4 = r4 + -1
            r3 = r3[r4]
            switch(r3) {
                case 1: goto L_0x011e;
                case 5: goto L_0x0126;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            android.widget.TextView r3 = r8.tOtvetYesTem
            int r4 = r8.blackColor
            r3.setTextColor(r4)
        L_0x00ae:
            r8.setNomVopros()
            java.lang.String r3 = r8.TEK_PICT
            int r3 = r3.length()
            if (r3 != 0) goto L_0x012e
            r2 = 0
            android.widget.ImageView r3 = r8.vImageTem
            r3.setImageBitmap(r2)
            android.widget.ImageView r3 = r8.vImageTem
            int r4 = r8.backColor
            r3.setBackgroundColor(r4)
        L_0x00c6:
            return
        L_0x00c7:
            android.widget.TextView r3 = r8.tVar6Tem
            r3.setVisibility(r6)
            android.widget.CheckBox r3 = r8.chVar6Tem
            r3.setVisibility(r6)
            android.widget.TextView r3 = r8.tVar6Tem
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r5 = 5
            r4 = r4[r5]
            r3.setText(r4)
        L_0x00db:
            android.widget.TextView r3 = r8.tVar5Tem
            r3.setVisibility(r6)
            android.widget.CheckBox r3 = r8.chVar5Tem
            r3.setVisibility(r6)
            android.widget.TextView r3 = r8.tVar5Tem
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r4 = r4[r7]
            r3.setText(r4)
        L_0x00ee:
            android.widget.TextView r3 = r8.tVar4Tem
            r3.setVisibility(r6)
            android.widget.CheckBox r3 = r8.chVar4Tem
            r3.setVisibility(r6)
            android.widget.TextView r3 = r8.tVar4Tem
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r5 = 3
            r4 = r4[r5]
            r3.setText(r4)
        L_0x0102:
            android.widget.TextView r3 = r8.tVar3Tem
            r3.setVisibility(r6)
            android.widget.CheckBox r3 = r8.chVar3Tem
            r3.setVisibility(r6)
            android.widget.TextView r3 = r8.tVar3Tem
            java.lang.String[] r4 = r8.TEK_MAS_OTVET
            r5 = 2
            r4 = r4[r5]
            r3.setText(r4)
            goto L_0x003d
        L_0x0118:
            java.lang.String r1 = java.lang.Integer.toString(r0)
            goto L_0x0088
        L_0x011e:
            android.widget.TextView r3 = r8.tOtvetYesTem
            int r4 = r8.redColor
            r3.setTextColor(r4)
            goto L_0x00ae
        L_0x0126:
            android.widget.TextView r3 = r8.tOtvetYesTem
            int r4 = r8.GreenText
            r3.setTextColor(r4)
            goto L_0x00ae
        L_0x012e:
            boolean r3 = r8.BROWSER_ERR
            if (r3 == 0) goto L_0x0160
            com.econavt.uniexam.ExamService2 r3 = r8.myService3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "RI"
            r4.<init>(r5)
            java.lang.String r5 = r8.WORK_NUM
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = ","
            java.lang.StringBuilder r4 = r4.append(r5)
            int[] r5 = r8.MASS_N_ERR_QST
            int r6 = r8.NOMVOPR
            int r6 = r6 + -1
            r5 = r5[r6]
            java.lang.String r5 = java.lang.Integer.toString(r5)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.doSend(r4)
            goto L_0x00c6
        L_0x0160:
            com.econavt.uniexam.ExamService2 r3 = r8.myService3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "ETI"
            r4.<init>(r5)
            java.lang.String r5 = r8.WORK_NUM
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = ",-1"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.doSend(r4)
            goto L_0x00c6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.econavt.uniexam.etema.set_ekran():void");
    }

    public boolean get_examen() {
        for (int i = 0; i < this.MAXVOPR; i++) {
            if (this.OTVETS[i] == 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void Add_err() {
        for (int i = 0; i < this.MAXVOPR; i++) {
            if (this.OTVETS[i] == 0) {
                this.NO_ANSWER++;
            }
        }
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

    public void start_timer() {
        final Timer tmr22 = new Timer();
        final Handler uiHandler2 = new Handler();
        tmr22.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Handler handler = uiHandler2;
                final Timer timer = tmr22;
                handler.post(new Runnable() {
                    public void run() {
                        etema access$0 = etema.this;
                        access$0.ZADERJKA_t--;
                        if (etema.this.INI_BREAK_ON_ERR && etema.this.EXAMEN_BREAK_ON_ERR) {
                            timer.cancel();
                            if (etema.this.INI_WORK_ERR) {
                                etema.this.browse_err(false);
                            }
                        } else if (etema.this.END_EXAMEN) {
                            timer.cancel();
                            if (etema.this.INI_WORK_ERR) {
                                etema.this.browse_err(false);
                            }
                        } else if (etema.this.TEK_TIME <= 0) {
                            timer.cancel();
                            etema.this.EXAMEN_BREAK_ON_TIME = true;
                            if (etema.this.INI_CONTR_TIME && etema.this.ZADERJKA_t <= 0) {
                                etema.this.tTimeTicTem.setText(etema.this.get_Time(etema.this.TEK_TIME));
                            }
                            etema.this.set_resultat();
                            if (etema.this.INI_WORK_ERR) {
                                etema.this.browse_err(false);
                            }
                        } else if (etema.this.EXAMEN_BREAK) {
                            timer.cancel();
                        } else if (etema.this.INI_CONTR_TIME) {
                            if (etema.this.ZADERJKA_t <= 0) {
                                etema.this.tTimeTicTem.setText(etema.this.get_Time(etema.this.TEK_TIME));
                            }
                            etema access$02 = etema.this;
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
            this.tNOtvetsTem.setText("");
            this.tNErrorsTem.setText("");
            this.tOtvetsTem.setText("Отвечено:");
            this.tErrorsTem.setText("Правильно:");
            this.bCloseTem.setEnabled(true);
            this.bCloseTem.setText("ОШИБКИ");
            this.bCloseTem.setTextColor(this.redColor);
            this.NOMVOPR = 1;
            this.MASS_N_ERR_QST = new int[this.ERRORS];
            this.MASS_ERR_PRESSED = new int[this.ERRORS];
            int j = 0;
            int NoAns = 0;
            for (int i = 0; i < this.MAXVOPR; i++) {
                if (this.OTVETS[i] != 5 && this.OTVETS[i] > 0) {
                    this.MASS_N_ERR_QST[j] = this.MASS_N_QST[i];
                    this.MASS_ERR_PRESSED[j] = this.MASS_PRESSED[i];
                    j++;
                }
                if (this.OTVETS[i] == 0) {
                    NoAns++;
                }
            }
            if (!Add) {
                this.MAXVOPR = j;
            } else {
                this.MAXVOPR = this.ERRORS;
            }
            this.tNoAns.setText(Integer.toString(NoAns));
            this.tOtvetYesTem.setText(String.valueOf(Integer.toString(this.NOMVOPR)) + " из " + Integer.toString(this.MAXVOPR));
            this.myService3.doSend("RE" + this.WORK_NUM);
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
    public boolean checkYes() {
        if (this.chVar1Tem.isChecked()) {
            return true;
        }
        if (this.chVar2Tem.isChecked()) {
            return true;
        }
        if (this.chVar3Tem.isChecked()) {
            return true;
        }
        if (this.chVar4Tem.isChecked()) {
            return true;
        }
        if (this.chVar5Tem.isChecked()) {
            return true;
        }
        if (this.chVar6Tem.isChecked()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setOK() {
        if (!this.END_EXAMEN && checkYes()) {
            clearSelect();
            if (this.OTVETS[this.NOMVOPR - 1] > 0) {
                Toast.makeText(this, "Ответ уже получен.", 0).show();
                return;
            }
            this.myService3.doSend("EKT" + this.WORK_NUM + "," + Integer.toString(this.THIS_OTVET) + "," + Integer.toString(this.NOMVOPR - 1));
            this.ANSWERS++;
            this.tNOtvetsTem.setText(Integer.toString(this.ANSWERS));
            this.MASS_PRESSED[this.NOMVOPR - 1] = this.THIS_OTVET;
            if (this.TEK_PR_OTVET == this.THIS_OTVET) {
                this.OTVETS[this.NOMVOPR - 1] = 5;
            } else {
                this.OTVETS[this.NOMVOPR - 1] = 1;
                this.ERRORS++;
                if (this.INI_SHOW_REZ) {
                    this.tNErrorsTem.setText(Integer.toString(this.ERRORS));
                } else {
                    this.tNErrorsTem.setText(" ");
                }
            }
            if (this.NOMVOPR < this.MAXVOPR) {
                this.TEK_QST++;
                this.NOMVOPR++;
                if (!this.INI_BREAK_ON_ERR || this.ERRORS <= this.ERR_RATE_3) {
                    this.myService3.doSend("ET" + this.WORK_NUM + "," + Integer.toString(this.TEK_QST));
                }
            }
            if (this.INI_BREAK_ON_ERR && this.ERRORS > this.ERR_RATE_3) {
                set_resultat();
                this.myService3.doSend("ETE");
            }
            if (get_examen()) {
                set_resultat();
                this.myService3.doSend("ETE");
            }
            this.bPrevTem.setEnabled(false);
            this.bNextTem.setEnabled(false);
            this.bOKTem.setEnabled(false);
            set_timer2();
        }
    }

    /* access modifiers changed from: package-private */
    public void clearSelect() {
        this.chVar1Tem.setChecked(false);
        this.chVar2Tem.setChecked(false);
        this.chVar3Tem.setChecked(false);
        this.chVar4Tem.setChecked(false);
        this.chVar5Tem.setChecked(false);
        this.chVar6Tem.setChecked(false);
        this.tAnswerTem.setText("");
    }

    /* access modifiers changed from: package-private */
    public void ErrClear() {
        this.ERRORS = 0;
        this.ANSWERS = 0;
        this.tNOtvetsTem.setText(Integer.toString(this.ANSWERS));
        this.tNErrorsTem.setText(Integer.toString(this.ERRORS));
    }

    /* access modifiers changed from: package-private */
    public void setPodskazka() {
        Toast.makeText(this, this.PODSKAZKA, 1).show();
    }

    /* access modifiers changed from: package-private */
    public void setNextVopros() {
        if (this.BROWSER_ERR) {
            this.myService3.doSend("RN" + this.WORK_NUM + "," + Integer.toString(this.MASS_N_ERR_QST[this.NOMVOPR - 1]));
        } else {
            this.myService3.doSend("ET" + this.WORK_NUM + "," + this.TEK_QST);
        }
        setNomVopros();
        this.bPrevTem.setEnabled(false);
        this.bNextTem.setEnabled(false);
        this.bOKTem.setEnabled(false);
        set_timer2();
    }

    /* access modifiers changed from: package-private */
    public void setNomVopros() {
        this.tOtvetYesTem.setText(String.valueOf(Integer.toString(this.NOMVOPR)) + " из " + Integer.toString(this.MAXVOPR));
        clearSelect();
    }

    /* access modifiers changed from: package-private */
    public void scroll(int n) {
        switch (this.NUM) {
            case 1:
                this.tVar1Tem.scrollBy(0, n);
                break;
            case 2:
                this.tVar2Tem.scrollBy(0, n);
                break;
            case 3:
                this.tVar3Tem.scrollBy(0, n);
                break;
            case 4:
                this.tVar4Tem.scrollBy(0, n);
                break;
            case 5:
                this.tVar5Tem.scrollBy(0, n);
                break;
            case 6:
                this.tVar6Tem.scrollBy(0, n);
                break;
            case MotionEventCompat.ACTION_HOVER_MOVE:
                this.tVoprMainTem.scrollBy(0, n);
                break;
        }
        this.ZADERJKA_t = this.pauza_t + 1;
    }

    /* access modifiers changed from: package-private */
    public void setNum() {
        switch (this.NUM) {
            case 1:
                this.tVoprMainTem.setBackgroundColor(this.zeroColor);
                this.tVar1Tem.setBackgroundColor(this.backColor);
                this.tVar2Tem.setBackgroundColor(this.zeroColor);
                this.tVar3Tem.setBackgroundColor(this.zeroColor);
                this.tVar4Tem.setBackgroundColor(this.zeroColor);
                this.tVar5Tem.setBackgroundColor(this.zeroColor);
                this.tVar6Tem.setBackgroundColor(this.zeroColor);
                return;
            case 2:
                this.tVoprMainTem.setBackgroundColor(this.zeroColor);
                this.tVar1Tem.setBackgroundColor(this.zeroColor);
                this.tVar2Tem.setBackgroundColor(this.backColor);
                this.tVar3Tem.setBackgroundColor(this.zeroColor);
                this.tVar4Tem.setBackgroundColor(this.zeroColor);
                this.tVar5Tem.setBackgroundColor(this.zeroColor);
                this.tVar6Tem.setBackgroundColor(this.zeroColor);
                return;
            case 3:
                this.tVoprMainTem.setBackgroundColor(this.zeroColor);
                this.tVar1Tem.setBackgroundColor(this.zeroColor);
                this.tVar2Tem.setBackgroundColor(this.zeroColor);
                this.tVar3Tem.setBackgroundColor(this.backColor);
                this.tVar4Tem.setBackgroundColor(this.zeroColor);
                this.tVar5Tem.setBackgroundColor(this.zeroColor);
                this.tVar6Tem.setBackgroundColor(this.zeroColor);
                return;
            case 4:
                this.tVoprMainTem.setBackgroundColor(this.zeroColor);
                this.tVar1Tem.setBackgroundColor(this.zeroColor);
                this.tVar2Tem.setBackgroundColor(this.zeroColor);
                this.tVar3Tem.setBackgroundColor(this.zeroColor);
                this.tVar4Tem.setBackgroundColor(this.backColor);
                this.tVar5Tem.setBackgroundColor(this.zeroColor);
                this.tVar6Tem.setBackgroundColor(this.zeroColor);
                return;
            case 5:
                this.tVoprMainTem.setBackgroundColor(this.zeroColor);
                this.tVar1Tem.setBackgroundColor(this.zeroColor);
                this.tVar2Tem.setBackgroundColor(this.zeroColor);
                this.tVar3Tem.setBackgroundColor(this.zeroColor);
                this.tVar4Tem.setBackgroundColor(this.zeroColor);
                this.tVar5Tem.setBackgroundColor(this.backColor);
                this.tVar6Tem.setBackgroundColor(this.zeroColor);
                return;
            case 6:
                this.tVoprMainTem.setBackgroundColor(this.zeroColor);
                this.tVar1Tem.setBackgroundColor(this.zeroColor);
                this.tVar2Tem.setBackgroundColor(this.zeroColor);
                this.tVar3Tem.setBackgroundColor(this.zeroColor);
                this.tVar4Tem.setBackgroundColor(this.zeroColor);
                this.tVar5Tem.setBackgroundColor(this.zeroColor);
                this.tVar6Tem.setBackgroundColor(this.backColor);
                return;
            case MotionEventCompat.ACTION_HOVER_MOVE:
                this.tVoprMainTem.setBackgroundColor(this.backColor);
                this.tVar1Tem.setBackgroundColor(this.zeroColor);
                this.tVar2Tem.setBackgroundColor(this.zeroColor);
                this.tVar3Tem.setBackgroundColor(this.zeroColor);
                this.tVar4Tem.setBackgroundColor(this.zeroColor);
                this.tVar5Tem.setBackgroundColor(this.zeroColor);
                this.tVar6Tem.setBackgroundColor(this.zeroColor);
                return;
            default:
                return;
        }
    }

    public void set_timer2() {
        final Timer tmr42 = new Timer();
        final Handler uiHandler = new Handler();
        tmr42.schedule(new TimerTask() {
            public void run() {
                Handler handler = uiHandler;
                final Timer timer = tmr42;
                handler.post(new Runnable() {
                    public void run() {
                        timer.cancel();
                        etema.this.bPrevTem.setEnabled(true);
                        etema.this.bNextTem.setEnabled(true);
                        etema.this.bOKTem.setEnabled(true);
                    }
                });
            }
        }, 1500);
    }

    public void set_timer1() {
        final Timer tmr5 = new Timer();
        final Handler uiHandler = new Handler();
        tmr5.schedule(new TimerTask() {
            public void run() {
                Handler handler = uiHandler;
                final Timer timer = tmr5;
                handler.post(new Runnable() {
                    public void run() {
                        timer.cancel();
                        etema.this.start1();
                    }
                });
            }
        }, 400);
    }

    public void start1() {
        int WrHeight = ((this.HEIGHT - this.frameLayout1Tem.getHeight()) - this.frameLayout2Tem.getHeight()) - this.linearLayout1Tem.getHeight();
        int WrInt = (WrHeight - 30) / 3;
        this.chVar1Tem.setHeight(WrInt / 2);
        this.chVar2Tem.setHeight(WrInt / 2);
        this.tZap1T.setHeight(WrInt / 4);
        this.tZap1T.setVisibility(4);
        this.tZap2T.setHeight(WrInt / 4);
        this.tZap2T.setVisibility(4);
        this.tZap3T.setHeight(WrInt / 4);
        this.tZap3T.setVisibility(4);
        this.tZap4T.setHeight(WrInt / 4);
        this.tZap4T.setVisibility(4);
        this.chVar3Tem.setHeight(WrInt / 2);
        this.chVar4Tem.setHeight(WrInt / 2);
        this.chVar5Tem.setHeight(WrInt / 2);
        this.chVar6Tem.setHeight(WrInt / 2);
        int WrInt2 = WrInt - 30;
        this.tVar1Tem.setHeight(WrInt2);
        this.tVar2Tem.setHeight(WrInt2);
        this.tVar3Tem.setHeight(WrInt2);
        this.tVar4Tem.setHeight(WrInt2);
        this.tVar5Tem.setHeight(WrInt2);
        this.tVar6Tem.setHeight(WrInt2);
        int WrInt3 = ((WrHeight - this.linearLayout2Tem.getHeight()) - this.tVoprMainTem.getHeight()) - 50;
        ViewGroup.LayoutParams params = this.vImageTem.getLayoutParams();
        params.height = this.IMAGE_HEIGHT;
        int WrInt4 = (int) (((double) this.IMAGE_HEIGHT) * 1.5d);
        params.width = WrInt4;
        this.vImageTem.setLayoutParams(params);
        int WrWidth = (((this.WIDTH - WrInt4) - 40) / 2) - this.chVar1Tem.getWidth();
        this.tVar1Tem.setWidth(WrWidth);
        this.tVar2Tem.setWidth(WrWidth);
        this.tVar3Tem.setWidth(WrWidth);
        this.tVar4Tem.setWidth(WrWidth);
        this.tVar5Tem.setWidth(WrWidth);
        this.tVar6Tem.setWidth(WrWidth);
        int WrInt5 = (WrInt4 - this.tVoprosTem.length()) / 2;
        this.tVar1Tem.setText("");
        if (this.INI_SHOW_REZ) {
            this.tOtvetYesTem.setTextColor(this.GreenText);
        }
        this.tOtvetYesTem.setText(String.valueOf(Integer.toString(this.NOMVOPR + 1)) + " из " + Integer.toString(this.MAXVOPR));
        if (this.HELP_YES) {
            this.bVoprTem.setEnabled(true);
        } else {
            this.bVoprTem.setEnabled(false);
        }
        ErrClear();
        this.tAnswerTem.setText("");
    }

    public void onClickCheck(int num) {
        switch (num) {
            case 1:
                this.chVar1Tem.setChecked(true);
                this.chVar2Tem.setChecked(false);
                this.chVar3Tem.setChecked(false);
                this.chVar4Tem.setChecked(false);
                this.chVar5Tem.setChecked(false);
                this.chVar6Tem.setChecked(false);
                break;
            case 2:
                this.chVar1Tem.setChecked(false);
                this.chVar2Tem.setChecked(true);
                this.chVar3Tem.setChecked(false);
                this.chVar4Tem.setChecked(false);
                this.chVar5Tem.setChecked(false);
                this.chVar6Tem.setChecked(false);
                break;
            case 3:
                this.chVar1Tem.setChecked(false);
                this.chVar2Tem.setChecked(false);
                this.chVar3Tem.setChecked(true);
                this.chVar4Tem.setChecked(false);
                this.chVar5Tem.setChecked(false);
                this.chVar6Tem.setChecked(false);
                break;
            case 4:
                this.chVar1Tem.setChecked(false);
                this.chVar2Tem.setChecked(false);
                this.chVar3Tem.setChecked(false);
                this.chVar4Tem.setChecked(true);
                this.chVar5Tem.setChecked(false);
                this.chVar6Tem.setChecked(false);
                break;
            case 5:
                this.chVar1Tem.setChecked(false);
                this.chVar2Tem.setChecked(false);
                this.chVar3Tem.setChecked(false);
                this.chVar4Tem.setChecked(false);
                this.chVar5Tem.setChecked(true);
                this.chVar6Tem.setChecked(false);
                break;
            case 6:
                this.chVar1Tem.setChecked(false);
                this.chVar2Tem.setChecked(false);
                this.chVar3Tem.setChecked(false);
                this.chVar4Tem.setChecked(false);
                this.chVar5Tem.setChecked(false);
                this.chVar6Tem.setChecked(true);
                break;
        }
        this.tAnswerTem.setText(String.valueOf(Integer.toString(num)) + "?");
        this.THIS_OTVET = num;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 || keyCode == 3 || keyCode == 16908332) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
