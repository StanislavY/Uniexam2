package com.econavt.uniexam;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ExamServiceRX extends Service {
    static TimerTask tTask;
    public boolean CREATED = false;
    public int END;
    public String[] Massiv;
    public int RAZMER = 0;
    public int RAZMER_MAX = 0;
    public boolean READIND_DATA = false;
    public boolean STOP_SERVICE = false;
    public String WORK_NAME = "#Учащийся";
    public String WORK_NUM = "1";
    public String W_ADDR;
    public int W_PORT;
    MyBinder binder = new MyBinder();
    public boolean connect_yes;
    public Socket connection;
    public String fromserver;

    /* renamed from: im */
    public byte[] f1im;

    /* renamed from: in */
    public InputStream f2in;
    long interval = 500;
    public InetAddress localAddress;
    public OutputStream out;
    public int size;
    Timer timer;

    public void onCreate() {
        if (!this.CREATED) {
            super.onCreate();
        }
    }


    public void doSend(String str) {

        UniLog.Method("connected: "+ connect_yes);
        if (this.connect_yes) {
            try {
                UniLog.onSend(str);
                this.out.write(str.getBytes());



            } catch (IOException e) {
                e.printStackTrace();
                UniLog.error(e.getMessage());
            }
        } else {
            String[] fields_main = str.split("#");
            this.W_ADDR = fields_main[0];
            this.W_PORT = Integer.parseInt(fields_main[1]);
            this.WORK_NAME = fields_main[2];
        }
    }


    public Observable doSendObservable(String str) {

        return Observable.fromCallable(() -> {

            try {
                doSend(str);

            } catch (Exception e) {
                return false;
            }
            return true;
        });
    }


    public void doSendRX(String str, Runnable callback) {
        doSendObservable(str)
                .subscribeOn(Schedulers.io())
                .doOnNext(o -> callback.run())
                .subscribe();
    }

    public void doSendRX(String str) {
        doSendObservable(str)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }



    public void connect() {
        this.STOP_SERVICE = false;
        InetAddress serverAddr = null;
        try {
            serverAddr = InetAddress.getByName(this.W_ADDR);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        client_connect(serverAddr, this.W_PORT, this.WORK_NAME);
        this.timer = new Timer();
    }

    public void client_disconnect() throws IOException {
        this.connection.close();
        this.connect_yes = false;
        this.CREATED = false;
    }

    public void client_connect(final InetAddress Addr, final int listeningPort, String w_name) {
        if (!this.CREATED) {
            Thread t = new Thread(() -> {
                try {
                    ExamServiceRX.this.localAddress = InetAddress.getLocalHost();
                    ExamServiceRX.this.connection = new Socket(Addr, listeningPort);
                    ExamServiceRX.this.connect_yes = true;
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    ExamServiceRX.this.f2in = ExamServiceRX.this.connection.getInputStream();
                } catch (NullPointerException e3) {
                    e3.printStackTrace();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                try {
                    ExamServiceRX.this.out = ExamServiceRX.this.connection.getOutputStream();
                } catch (NullPointerException e5) {
                    e5.printStackTrace();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            });
            this.CREATED = true;
            t.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void ReadingThread() {
        int length = 0;
        try {
            this.size = this.f2in.available();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (this.size > 0) {
            byte[] b = new byte[this.size];
            try {
                length = this.f2in.read(b);
                if (length < 0) {
                    throw new EOFException("Was got -1");
                }

            } catch (IOException e3) {
                e3.printStackTrace();
                UniLog.error(e3.getMessage());
            }
            if (b[0] == 89) {
                this.READIND_DATA = true;
            }
            if (!this.READIND_DATA) {
                this.RAZMER = 0;
                Intent intent = new Intent("com.econavt.uniexam");
                intent.putExtra(MainActivity.PARAM_BUF, b);
                sendBroadcast(intent);
            } else if (this.RAZMER == 0) {
                String[] Massiv2 = new String(b).split("#");
                this.RAZMER_MAX = Integer.parseInt(Massiv2[1]);
                int start = Massiv2[1].length() + 3;
                int i = length - start;
                this.END = i;
                this.RAZMER = i;
                this.f1im = new byte[this.RAZMER_MAX];
                int i2 = 0;
                while (i2 < this.RAZMER) {
                    this.f1im[i2] = b[i2 + start];
                    i2++;
                }
                this.END = i2;
            } else {
                this.RAZMER += length;
                int i3 = this.END;
                int j = 0;
                while (i3 < this.END + length) {
                    this.f1im[i3] = b[j];
                    i3++;
                    j++;
                }
                this.END = i3;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void GetData() {
        ReadingThread();
        if (this.READIND_DATA) {
            while (this.RAZMER < this.RAZMER_MAX) {
                ReadingThread();
            }
            this.RAZMER = 0;
            this.READIND_DATA = false;
            Intent intent = new Intent("com.econavt.uniexam");
            intent.putExtra(MainActivity.PARAM_BUF, this.f1im);
            sendBroadcast(intent);
            schedule();
        }
    }

    public void schedule() {
        if (tTask != null) {
            tTask.cancel();
        }
        if (this.STOP_SERVICE || this.READIND_DATA) {
            tTask.cancel();
        } else if (this.interval > 0) {
            tTask = new TimerTask() {
                public void run() {
                    if (ExamServiceRX.this.STOP_SERVICE) {
                        ExamServiceRX.this.timer.cancel();
                        ExamServiceRX.tTask.cancel();
                        return;
                    }
                    ExamServiceRX.this.GetData();
                }
            };
            this.timer.schedule(tTask, this.interval, this.interval);
        }
    }

    public IBinder onBind(Intent arg0) {
        return this.binder;
    }

    /* access modifiers changed from: package-private */
    public void stop_timer() {
    }

    public void stop_service() {
        this.STOP_SERVICE = true;
        stopSelf();
    }

    class MyBinder extends Binder {
        MyBinder() {
        }

        /* access modifiers changed from: package-private */
        public ExamServiceRX getService() {
            return ExamServiceRX.this;
        }
    }
}
