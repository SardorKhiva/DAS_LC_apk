package das.lc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView liteCartWebView;
    private static final String LITE_CART_URL = "https://umidbek.uz/lc"; // sayt manzili

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liteCartWebView = findViewById(R.id.liteCartWebView);

        // WebView sozlamalari
        WebSettings webSettings = liteCartWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // JavaScript ni yoqish
        webSettings.setDomStorageEnabled(true); // yaxshiroq moslashuvchanlik uchun DOM Storage ni yoqish
//        webSettings.setLoadWithOverviewMode(true); // sahifani umumiy ko'rinishda yuklash
//        webSettings.setUseWideViewPort(true); // keng oynada ko'rinishga ruxsat berish

        // brauzerdamas dasturni o'zida qolsin
        liteCartWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // URL telefonga murojaat qilishini tekshirish
                if (url.startsWith("tel:")) {
                    // Telefon qilish uchun Intent hosil qilish
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent); // telefon qilish oynasini ochish
                    return true; // agar URL qayta ishlangan bo'lsa true qaytarish
                }

                // agar URL telefon raqami bo'lmasa, WebView URL ni o'zi yuklashi uchun false qilish
                return false;
            }
        });

        // sayt url ni yuklash
        liteCartWebView.loadUrl(LITE_CART_URL);

        // --- OnBackPressedDispatcher orqali "orqaga" tugmasini ishlatish  ---
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (liteCartWebView.canGoBack()) {
                    liteCartWebView.goBack(); // agar oldin kirilgan sahifalar bo'lsa shunga qaytsin
                } else {
                    // agar bo'lmasa dasturdan chiqsin
                    setEnabled(false); // sistemaniki ishlashi uchun buni o'chirish
                    onBackPressed(); // sistema onBackPressed orqali chaqirib activity ni o'chiramiz
                }
            }
        });
    }
}