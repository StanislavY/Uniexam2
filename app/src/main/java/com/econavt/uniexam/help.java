package com.econavt.uniexam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

public class help extends Activity {
    ImageButton iRet;
    WebView webView1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        this.iRet = (ImageButton) findViewById(R.id.iRet);
        this.webView1 = (WebView) findViewById(R.id.webView1);
        this.webView1.getSettings().setBuiltInZoomControls(true);
        this.iRet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iRet:
                        help.this.finish();
                        return;
                    default:
                        return;
                }
            }
        });
        this.webView1.loadUrl("file:///android_asset/rmu-help.html");
    }
}
