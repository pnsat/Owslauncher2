/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Handler
 *  android.text.Editable
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.widget.EditText
 *  android.widget.TextView
 *  java.io.BufferedReader
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileWriter
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.InterruptedException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Process
 *  java.lang.Runnable
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.nio.charset.Charset
 *  java.util.Date
 */
package com.daydr3am.owslauncher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Date;

public class MainActivity
        extends Activity
        implements View.OnTouchListener {
    float X;
    float Y;
    String error = "\u0e41\u0e08\u0e49\u0e07 084-555-7930";
    Handler handler;
    boolean isLoop;
    Runnable run;

    public MainActivity() {
        this.run = new Runnable() {

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void run() {
                NetworkInfo networkInfo = ((ConnectivityManager) MainActivity.this.getSystemService("connectivity")).getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable() && new File("/mnt/sdcard/Resource/").isDirectory() && MainActivity.this.isLoop) {
                    Log.v((String) "hello", (String) "ready");
                    try {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.setClassName("com.daydr3am.OWS", "com.daydr3am.OWS.MainPage");
                        intent.setFlags(268435456);
                        MainActivity.this.startActivity(intent);
                        MainActivity.this.isLoop = false;
                    } catch (Exception var5_3) {
                        var5_3.printStackTrace();
                    }
                }
                if (MainActivity.this.isLoop) {
                    MainActivity.this.handler.postDelayed(MainActivity.this.run, 1000);
                }
            }
        };
        this.handler = new Handler();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void KillStatusBar() {
        Process process;
        String string2 = "79";
        if (Build.VERSION.SDK_INT >= 14) {
            string2 = "42";
        }
        try {
            Process process2;
            Runtime runtime = Runtime.getRuntime();
            String[] arrstring = new String[]{"su", "-c", "service call activity " + string2 + " s16 com.android.systemui"};
            process = process2 = runtime.exec(arrstring);
        } catch (IOException var2_6) {
            Log.w((String) "Dog", (String) "Failed to kill task bar (1).");
            var2_6.printStackTrace();
            process = null;
        }
        try {
            process.waitFor();
            return;
        } catch (InterruptedException var5_7) {
            Log.w((String) "Dog", (String) "Failed to kill task bar (2).");
            var5_7.printStackTrace();
            return;
        }
    }

    public static String convertStreamToString(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader((Reader) new InputStreamReader(inputStream, Charset.forName((String) "UTF-8")));
        StringBuilder stringBuilder = new StringBuilder();
        do {
            String string2;
            if ((string2 = bufferedReader.readLine()) == null) {
                bufferedReader.close();
                return stringBuilder.toString();
            }
            stringBuilder.append(string2).append("\n");
        } while (true);
    }

    private void dialogPassword(String string2) {
        Date date = new Date();
        int n = 100 * (1 + date.getDay()) + date.getDay() - date.getHours();
        Object[] arrobject = new Object[]{n};
        final String string3 = String.format((String) "%04d", (Object[]) arrobject);
        AlertDialog.Builder builder = new AlertDialog.Builder((Context) this);
        builder.setTitle((CharSequence) "Enter Password");
        builder.setMessage((CharSequence) string2);
        final EditText editText = new EditText((Context) this);
        builder.setView((View) editText);
        builder.setPositiveButton((CharSequence) "Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                String string2 = (Object) editText.getEditableText();
                Log.d((String) "debug", (String) ("EditText = " + string2));
                if (!string2.equalsIgnoreCase(string3)) {
                    MainActivity.this.dialogPassword("Wrong Password");
                    return;
                }
                MainActivity.this.startActivityForResult(new Intent("android.settings.SETTINGS"), 0);
            }
        });
        builder.setNegativeButton((CharSequence) "Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public static String getStringFromFile(String string2) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(string2));
        String string3 = MainActivity.convertStreamToString((InputStream) fileInputStream);
        fileInputStream.close();
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void appendLog(String string2) {
        File file = new File("/mnt/sdcard/Resource/myLog.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var6_4) {
                var6_4.printStackTrace();
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter((Writer) new FileWriter(file, true));
            bufferedWriter.append((CharSequence) string2);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return;
        } catch (IOException var4_5) {
            var4_5.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View view = this.getLayoutInflater().inflate(2130903040, null);
        this.setContentView(view);
        view.setOnTouchListener((View.OnTouchListener) this);
        try {
            this.error = MainActivity.getStringFromFile("/mnt/sdcard/Resource/Error.txt");
        } catch (Exception var3_3) {
            var3_3.printStackTrace();
            this.error = "\u0e41\u0e08\u0e49\u0e07 084-555-7930";
        }
        Log.v((String) "hello", (String) ("error message " + this.error));
        ((TextView) this.findViewById(2131230723)).setText((CharSequence) this.error);
    }

    public boolean onCreateOptionsMenu(Menu menu2) {
        this.getMenuInflater().inflate(2131165184, menu2);
        return true;
    }

    protected void onPause() {
        super.onPause();
        this.isLoop = false;
    }

    protected void onResume() {
        super.onResume();
        this.isLoop = true;
        this.KillStatusBar();
        this.handler.postDelayed(this.run, 45000);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouch(View view, final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                this.X = motionEvent.getX();
                this.Y = motionEvent.getY();
            }
            default: {
                return true;
            }
            case 1:
        }
        new Handler().post(new Runnable() {

            public void run() {
                if (Math.abs((float) (motionEvent.getX() - MainActivity.this.X)) > 200.0f && Math.abs((float) (motionEvent.getY() - MainActivity.this.Y)) > 200.0f) {
                    MainActivity.this.dialogPassword("");
                }
            }
        });
        return true;
    }

}

