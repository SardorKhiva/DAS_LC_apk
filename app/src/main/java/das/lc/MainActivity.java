package das.lc;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView liteCartWebVIew;
    public static final String LITE_CART_URL = "https://umidbek.uz/lc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liteCartWebVIew = findViewById(R.id.liteCartWebView);

        // WebView sozlamalari
        WebSettings webSettings = liteCartWebVIew.getSettings();
        webSettings.setJavaScriptEnabled(true); // JS ni yoqish
        webSettings.setDomStorageEnabled(true); // DOM ni yoqamiz
        webSettings.setLoadWithOverviewMode(true); // umumiy ko'rinishini yuklash
        webSettings.setUseWideViewPort(true); // Keng oynalarda ko'rishga ruxsat

        // hammasi dastur ichida ishlashi uchun WebViewClien ni o'rnatish
        liteCartWebVIew.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // URL ni o'zi yuklashi uchun false qaytaramiz
                return false;
            }
        });

        // sahifani yuklash
        liteCartWebVIew.loadUrl(LITE_CART_URL);
    }

    // orqaga qaytish funksionali

    public void onBackPressedDispatcher() {
        if (liteCartWebVIew.canGoBack()) {
            liteCartWebVIew.goBack(); // agar tarixda oldingi sahifalar bo'lsa shunga qaytsin
        } else {
            super.getOnBackPressedDispatcher();  // aks holda dasturdan chiqsin
        }
    }
}