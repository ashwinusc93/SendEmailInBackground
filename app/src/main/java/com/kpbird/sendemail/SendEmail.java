package com.kpbird.sendemail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;


public class SendEmail extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      //  toggleWiFi(false);
/*
        WifiManager wifiManager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);
       // if (status == true && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
*/

        changeWifi();
        sending();

/*
        Context context = this;
        String networkSSID = "zeesense";
        String networkPass = "activedust";

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.preSharedKey = "\"" + networkPass + "\"";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.addNetwork(conf);
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration i : list) {
            if (i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                //   wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();

                break;
            }
        }
*/

    }
    public void sending(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "mail sending", Toast.LENGTH_SHORT).show();
        Mail m = new Mail("ashwin.yeshaswi@gmail.com", "Ash@usc1993");
        String[] toArr = {"sem8team@gmail.com"};
        m.setTo(toArr);
        m.setFrom("ashwin.yeshaswi@gmail.com");
        m.setSubject("Zeesense Security Alert");
        String result="";
        try{
            File myfile=new File(Environment.getExternalStorageDirectory()+"/customer_details/dataforCSharpnew.txt");
            FileInputStream fis=new FileInputStream(myfile);
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            String data=null;
            while((data=br.readLine()) != null)
                result+=data;}
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "EXCEPTION OCCURED", Toast.LENGTH_SHORT).show();

        }
        m.setBody(result);
        try {
            //m.addAttachment("/sdcard/customer_details/dataforCSharpnew.txt");
            if(m.send()) {
                Toast.makeText(this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                Intent i=new Intent(SendEmail.this,MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "Email was not sent.", Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            Log.e("MailApp", "Could not send email", e);
        }

    }

    public  void toggleWiFi(boolean status) {
        WifiManager wifiManager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);
        if (status == true && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (status == false && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }
    public void changeWifi()
    {
        Context context=this;
        String networkSSID = "Tata-Photon-Max-Wi-Fi-25AD";
        String networkPass = "12345678";

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.preSharedKey = "\""+ networkPass +"\"";
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.addNetwork(conf);
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();

                break;
            }
        }
    }
}