package com.codex.cdevz;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Proxy;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import com.codex.cdevz.R;
import android.view.View.OnClickListener;
import android.view.View;
import android.webkit.WebView;

public class IPHuntActivity extends AppCompatActivity {

	private Button btn_chck_ip;
	private TextView ms;
	private TextView stats;
	public String ip;
	String myUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphunt);
		Toolbar toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		
		StrictMode.setThreadPolicy(policy);
		btn_chck_ip = (Button)findViewById(R.id.appButton1);
		ms = (TextView)findViewById(R.id.notiftext1);
		stats = (TextView)findViewById(R.id.activityiphuntTextView1);
        stats.setText(ip);
        ms.setText(ip);
		btn_chck_ip.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					checkcheck();
				}
			});
		
	}

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

	public void checkcheck()
	{
		ms.setText("Checking your IP, please wait...");
		btn_chck_ip.setText("Checking..");
		new Handler().postDelayed(new Runnable(){

				@Override
				public void run() {
					check_now();
				}
			}, 100);
	}

	public void check_now() {
        int l = 0;
        try {
            URL whatismyip = new URL("http://noloadbalance.globe.com.ph/");
            try {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("104.16.213.74", 80));
                HttpURLConnection connection = (HttpURLConnection) whatismyip.openConnection(proxy);
                connection.setRequestMethod("GET");
                connection.connect();
                connection.getContentLength();
                connection.getContentEncoding();
                connection.getHeaderFields();
                connection.getInputStream();
                connection.setConnectTimeout(0);
                HttpCookie cookie = new HttpCookie("lang", "ph");
                cookie.setDomain("noloadbalance.globe.com.ph");
                cookie.setPath("/");
                cookie.setVersion(0);
                CookieManager cookieManager = new CookieManager();
                cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                CookieHandler.setDefault(cookieManager);
                InputStream in = new BufferedInputStream(connection.getInputStream());
                byte[] buffer = new byte[4096];
                while (true) {
                    int countBytesRead = in.read(buffer);
                    if (countBytesRead == -1) {
                        break;
                    }
                    l += countBytesRead;
                }
                in.markSupported();
                if (connection.getResponseCode() == 200) {
					this.btn_chck_ip.setText("Success");
                    this.ms.setText("HTTP/1.1 200 ok");
                    this.stats.setText("✅ Success");
                } else if (connection.getResponseCode() == 301) {
                    this.ms.setText("HTTP/1.1 301 Moved Permanently");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 400) {
                    this.ms.setText("HTTP/1.1 400 Bad Request");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 302) {
                    this.ms.setText("HTTP/1.1 302 Temporary Redirect");
                    this.stats.setText("Abnormal IP");
                } else if (connection.getResponseCode() == 202) {
                    this.ms.setText("HTTP/1.1 202 Accepted");
                    this.stats.setText("Not Sure");
                } else if (connection.getResponseCode() == 502) {
                    this.ms.setText("HTTP/1.1 502 Bad Gateway");
                    this.stats.setText("Failed to response");
                } else if (connection.getResponseCode() == 405) {
                    this.ms.setText("HTTP/1.1 405 Method Not Allowed");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 408) {
                    this.ms.setText("HTTP/1.1 408 Request Time-Out");
                    this.stats.setText("Time-Out");
                } else if (connection.getResponseCode() == 409) {
                    this.ms.setText("HTTP/1.1 409 Conflict");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 413) {
                    this.ms.setText("HTTP/1.1 413 Request Entity Too Large");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 403) {
                    this.ms.setText("HTTP/1.1 403 Forbidden");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 504) {
                    this.ms.setText("HTTP/1.1 504 Gateway Timeout");
                    this.stats.setText("Time-Out");
                } else if (connection.getResponseCode() == 410) {
                    this.ms.setText("HTTP/1.1 405 Gone");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 500) {
                    this.ms.setText("HTTP/1.1 500 Internal Server Error");
                    this.stats.setText("❎ Error Server");
                } else if (connection.getResponseCode() == 411) {
                    this.ms.setText("HTTP/1.1 411 Length Required");
                    this.stats.setText("❎ Need Length");
                } else if (connection.getResponseCode() == 300) {
                    this.ms.setText("HTTP/1.1 300 Multiple Choices");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 406) {
                    this.ms.setText("HTTP/1.1 406 Not Acceptable");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 203) {
                    this.ms.setText("HTTP/1.1 203 Non-Authoritative Information");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 404) {
                    this.ms.setText("HTTP/1.1 404 Not Found");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 406) {
                    this.ms.setText("HTTP/1.1 406 Not Acceptable");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 204) {
                    this.ms.setText("HTTP/1.1 204 No Content");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 206) {
                    this.ms.setText("HTTP/1.1 206 Partial Content");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 402) {
                    this.ms.setText("HTTP/1.1 402 Payment Required");
                    this.stats.setText("Need Payment");
                } else if (connection.getResponseCode() == 412) {
                    this.ms.setText("HTTP/1.1 412 Precondition Failed");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 407) {
                    this.ms.setText("HTTP/1.1 407 Proxy-Authentication Required");
                    this.stats.setText("Failed to authentication");
                } else if (connection.getResponseCode() == 414) {
                    this.ms.setText("HTTP/1.1 414 Requires-URI Too Large");
                    this.stats.setText("Need URI Normal");
                } else if (connection.getResponseCode() == 205) {
                    this.ms.setText("HTTP/1.1 205 Reset Content");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 303) {
                    this.ms.setText("HTTP/1.1 303 See Other");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 204) {
                    this.ms.setText("HTTP/1.1 204 No Content");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 503) {
                    this.ms.setText("HTTP/1.1 503 Service Unavailable");
                    this.stats.setText("❎ Service Problem");
                } else if (connection.getResponseCode() == 415) {
                    this.ms.setText("HTTP/1.1 415 Unsupported Media Type");
                    this.stats.setText("❎ Failed");
                } else if (connection.getResponseCode() == 305) {
                    this.ms.setText("HTTP/1.1 305 Use Proxy");
                    this.stats.setText("Contact Developer");
                } else if (connection.getResponseCode() == 505) {
                    this.ms.setText("HTTP/1.1 505 Not Supported HTTP Version");
                    this.stats.setText("❎ Failed");
                }
            } catch (IOException e) {
                this.ms.setText("HTTP/1.1 301 Moved Permanently");
				this.btn_chck_ip.setText("Failed");
                this.stats.setText("❎ Failed");
            }
        } catch (MalformedURLException e2) {
            Toast.makeText(getApplicationContext(), e2.getMessage(), 1).show();
        }
    }
}





