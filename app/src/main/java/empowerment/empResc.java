package empowerment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.finalhavenus.R;

public class empResc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresc);

        // Find the CardView by its ID
        CardView wao = findViewById(R.id.card1);
        CardView wow = findViewById(R.id.card2);
        CardView g4g = findViewById(R.id.card3);
        CardView awam = findViewById(R.id.card4);

        // Set an OnClickListener for the CardView
        wao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                openLink("https://wao.org.my/");
            }
        });

        wow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                openLink("https://www.womenofwill.org.my/");
            }
        });

        g4g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                openLink("https://projectg4g.org/chapter/malaysia/");
            }
        });

        awam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                openLink("https://www.awam.org.my/");
            }
        });


        ImageView legal = findViewById(R.id.imageView2);
        ImageView couns = findViewById(R.id.imageView3);
        ImageView empw = findViewById(R.id.imageView4);

        legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                openLink("https://www.unwomen.org/en/digital-library/multimedia/2021/7/infographic-womens-rights-and-the-law");
            }
        });

        couns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                openLink("https://www.awam.org.my/telenita-2/");
            }
        });

        empw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                openLink("https://wao.org.my/");
            }
        });
    }


    // A method to open a link using a WebView
    private void openLink(String url) {
        WebView webView = new WebView(this);
        setContentView(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Open all links within the WebView
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                // Handle errors here
                Toast.makeText(empResc.this, "Error loading the page", Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(url);
    }

