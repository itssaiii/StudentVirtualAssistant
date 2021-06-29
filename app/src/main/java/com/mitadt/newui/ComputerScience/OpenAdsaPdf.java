package com.mitadt.newui.ComputerScience;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mitadt.newui.R;

import java.net.URLEncoder;

public class OpenAdsaPdf extends AppCompatActivity {
    WebView pdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_adsa_pdf);

        pdf  = findViewById(R.id.viewpdf);
        pdf.getSettings().setJavaScriptEnabled(true);

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
        pdf.loadUrl("http://docs.google.com/gview?embedded=true&url=" + link);

    }
}