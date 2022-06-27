package com.econavt.uniexam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sett extends Activity {
    public static final String SHARED_PREFS_NAME = "sett.xml";
    int WORK_HEIGTH;
    String WORK_NAME;
    String WORK_NUM;
    Button bPrev1;
    SharedPreferences sPref;
    Button w_OK_Sett;
    EditText w_nam;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0051R.layout.sett);
        this.w_nam = (EditText) findViewById(C0051R.C0052id.w_nam);
        this.w_OK_Sett = (Button) findViewById(C0051R.C0052id.w_OK_Sett);
        this.bPrev1 = (Button) findViewById(C0051R.C0052id.bPrev1);
        View.OnClickListener oclBtn = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case C0051R.C0052id.bPrev1:
                        sett.this.finish();
                        return;
                    case C0051R.C0052id.w_OK_Sett:
                        sett.this.onClickSet();
                        return;
                    default:
                        return;
                }
            }
        };
        this.w_OK_Sett.setOnClickListener(oclBtn);
        this.bPrev1.setOnClickListener(oclBtn);
        this.sPref = getSharedPreferences("sett.xml", 0);
        this.WORK_NUM = this.sPref.getString("w_numer", "1");
        this.WORK_NAME = this.sPref.getString("w_name", "Учащийся " + this.WORK_NUM);
        this.WORK_HEIGTH = this.sPref.getInt("FontSize", 10);
        boolean M_SOUND = this.sPref.getBoolean("m_sound", true);
        this.bPrev1.setTextSize((float) this.WORK_HEIGTH);
        this.w_OK_Sett.setTextSize((float) this.WORK_HEIGTH);
        this.w_nam.setTextSize((float) this.WORK_HEIGTH);
        if (M_SOUND) {
            MediaPlayer.create(this, C0051R.raw.f8s2).start();
        }
        this.w_nam.setText(this.WORK_NAME);
    }

    public void onClickSet() {
        this.sPref = getSharedPreferences("sett.xml", 0);
        SharedPreferences.Editor ed = this.sPref.edit();
        ed.putString("w_name", this.w_nam.getText().toString());
        ed.commit();
        Toast.makeText(this, "Настройки сохранены", 0).show();
    }
}
