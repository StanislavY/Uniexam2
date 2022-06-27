package com.econavt.uniexam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class sett2 extends Activity {
    public static final String SHARED_PREFS_NAME = "sett.xml";
    int IMAGE_HEIGHT = 235;
    boolean M_CONFIRM;
    boolean M_SOUND;
    String SERVER_ADR;
    String SERVER_PORT;
    int WORK_HEIGTH;
    String WORK_NUM;
    CheckBox chVar2Sett2;
    CheckBox chVar3Sett2;
    EditText for_pauza_t;
    EditText im_height;
    int pauza_t = 5;
    SharedPreferences sPref;
    EditText tFontSize;
    Button w_OK_Sett2;
    Button w_Prev;
    EditText w_adr;
    EditText w_num;
    EditText w_port;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0051R.layout.sett2);
        this.w_num = (EditText) findViewById(C0051R.C0052id.w_num);
        this.w_adr = (EditText) findViewById(C0051R.C0052id.w_adr);
        this.w_port = (EditText) findViewById(C0051R.C0052id.w_port);
        this.for_pauza_t = (EditText) findViewById(C0051R.C0052id.for_pauza_t);
        this.im_height = (EditText) findViewById(C0051R.C0052id.im_height);
        this.tFontSize = (EditText) findViewById(C0051R.C0052id.tFontSize);
        this.w_OK_Sett2 = (Button) findViewById(C0051R.C0052id.w_OK_Sett2);
        this.w_Prev = (Button) findViewById(C0051R.C0052id.w_Prev);
        this.chVar2Sett2 = (CheckBox) findViewById(C0051R.C0052id.chVar2Sett2);
        View.OnClickListener oclBtn = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case C0051R.C0052id.w_Prev:
                        sett2.this.onClickPrev2();
                        return;
                    case C0051R.C0052id.w_OK_Sett2:
                        sett2.this.onClickSet();
                        return;
                    default:
                        return;
                }
            }
        };
        this.w_OK_Sett2.setOnClickListener(oclBtn);
        this.w_Prev.setOnClickListener(oclBtn);
        this.sPref = getSharedPreferences("sett.xml", 0);
        this.WORK_NUM = this.sPref.getString("w_numer", "1");
        this.M_CONFIRM = this.sPref.getBoolean("m_confirm", true);
        this.SERVER_ADR = this.sPref.getString("w_address", "192.168.0.60");
        this.SERVER_PORT = this.sPref.getString("w_port", "5556");
        this.M_SOUND = this.sPref.getBoolean("m_sound", true);
        this.pauza_t = this.sPref.getInt("pauza_t", 5);
        this.WORK_HEIGTH = this.sPref.getInt("FontSize", 10);
        this.IMAGE_HEIGHT = this.sPref.getInt("ImageHeight", 235);
        this.w_OK_Sett2.setTextSize((float) this.WORK_HEIGTH);
        this.w_Prev.setTextSize((float) this.WORK_HEIGTH);
        this.for_pauza_t.setTextSize((float) this.WORK_HEIGTH);
        this.tFontSize.setTextSize((float) this.WORK_HEIGTH);
        this.im_height.setTextSize((float) this.WORK_HEIGTH);
        if (this.M_SOUND) {
            MediaPlayer.create(this, C0051R.raw.f8s2).start();
        }
        this.w_num.setText(this.WORK_NUM);
        this.w_adr.setText(this.SERVER_ADR);
        this.w_port.setText(this.SERVER_PORT);
        this.chVar2Sett2.setChecked(this.M_SOUND);
        this.for_pauza_t.setText(Integer.toString(this.pauza_t));
        this.tFontSize.setText(Integer.toString(this.WORK_HEIGTH));
        this.im_height.setText(Integer.toString(this.IMAGE_HEIGHT));
    }

    public void onClickPrev2() {
        finish();
    }

    public void onClickSet() {
        boolean z = true;
        int WrInt = Integer.parseInt(this.for_pauza_t.getText().toString());
        boolean z2 = WrInt < 1;
        if (WrInt <= 6) {
            z = false;
        }
        if (z || z2) {
            Toast.makeText(this, "Задержка вне диапазона", 0).show();
            return;
        }
        int WrInt2 = Integer.parseInt(this.im_height.getText().toString());
        if (WrInt2 < 100 || WrInt2 > 600) {
            Toast.makeText(this, "Высота изображения вне диапазона", 0).show();
            return;
        }
        int WrInt3 = Integer.parseInt(this.tFontSize.getText().toString());
        if (WrInt3 < 10 || WrInt3 > 20) {
            Toast.makeText(this, "Размер шрифта вне диапазона", 0).show();
            return;
        }
        this.sPref = getSharedPreferences("sett.xml", 0);
        SharedPreferences.Editor ed = this.sPref.edit();
        ed.putString("w_numer", this.w_num.getText().toString());
        ed.putString("w_address", this.w_adr.getText().toString());
        ed.putString("w_port", this.w_port.getText().toString());
        ed.putBoolean("m_sound", this.chVar2Sett2.isChecked());
        this.pauza_t = Integer.parseInt(this.for_pauza_t.getText().toString());
        ed.putInt("pauza_t", this.pauza_t);
        this.WORK_HEIGTH = Integer.parseInt(this.tFontSize.getText().toString());
        ed.putInt("FontSize", this.WORK_HEIGTH);
        this.IMAGE_HEIGHT = Integer.parseInt(this.im_height.getText().toString());
        ed.putInt("ImageHeight", this.IMAGE_HEIGHT);
        ed.commit();
        Toast.makeText(this, "Настройки сохранены", 0).show();
    }
}
