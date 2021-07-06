package com.mitadt.newui.ComputerScience;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.mitadt.newui.R;

import java.net.URLEncoder;

public class OpenAdsaPdf extends AppCompatActivity {
    WebView pdf;
    WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_adsa_pdf);



        pdf  = findViewById(R.id.viewpdf);
        pdf.getSettings().setJavaScriptEnabled(true);
        pdf.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        pdf.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        pdf.getSettings().setAppCacheEnabled(true);
        pdf.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings = pdf.getSettings();
        webSettings.setDomStorageEnabled(true);


        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);

        String filename=getIntent().getStringExtra("filename");
        String fileurl=getIntent().getStringExtra("fileurl");

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle(filename);
        pd.setMessage("Please Wait for the Document to Load...!!");

        pdf.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });

        String link = "";
        try {
            link = URLEncoder.encode(fileurl,"UTF-8");
        }catch (Exception ex){

        }
        pdf.loadUrl("https://docs.google.com/gview?embedded=true&url=" + link);

    }
}