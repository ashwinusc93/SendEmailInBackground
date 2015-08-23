package com.kpbird.sendemail;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
 
    //private Button button;
    private WebView webView;



    private ProgressBar progress;

   /* @Override
    protected void onStop() {
        super.onStop();
        Context context=this;
        String networkSSID = "Satish Albert";
        String networkPass = "satish@123";

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
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.varma.android.aws.ui.WorkerThreads","com.varma.android.aws.ui.WorkerThreads.StartingPoint"));
        startActivity(intent);
    }*/

    public void onCreate(Bundle savedInstanceState) {
         
        super.onCreate(savedInstanceState);
        //toggleWiFi(false);
        setContentView(R.layout.activity_main);
         
        //Get webview 
        webView = (WebView) findViewById(R.id.wv);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);
        startWebView("http://event-final.azurewebsites.net");
         
    }
     
    private void startWebView(String url) {
         
        //Create new webview Client to show progress dialog
        //When opening a url or click on link
         
        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;
          
            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        
            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                }
            }
            @TargetApi(Build.VERSION_CODES.CUPCAKE)
            public void onPageFinished(WebView view, String url) {
                try{
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                    Toast.makeText(getApplication(),"dismissed",Toast.LENGTH_SHORT).show();


               //     toggleWiFi(true);
              /*      try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                      changeWifi();
                //    Intent intent = new Intent(Intent.ACTION_MAIN);
                 //   intent.setComponent(new ComponentName("com.varma.android.aws.ui.WorkerThreads","com.varma.android.aws.ui.WorkerThreads.StartingPoint"));
                 //   startActivity(intent);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent("android.intent.category.LAUNCHER");
                    intent.setClassName("com.netvox.android.api.demo", "com.varma.android.aws.ui.AWSActivity");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
             
        }); 
          
         // Javascript inabled on webview  
        webView.getSettings().setJavaScriptEnabled(true); 
         
        // Other webview options
        /*
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        */
         
        /*
         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null); 
         */
         
        //Load url in webview
        
        webView.loadUrl(url);
     //   MainActivity.this.progress.setProgress(0);
     //Toast.makeText(getApplicationContext(), "  ", Toast.LENGTH_LONG).show();
     
          
    }
     
    // Open previous opened link from history on webview when back button pressed
     
    @SuppressLint("NewApi")
    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
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
    String networkSSID = "Satish Albert";
    String networkPass = "satish@123";

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
