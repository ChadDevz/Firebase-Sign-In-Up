package com.codex.cdevz;

import com.codex.cdevz.adapter.TypeAdapter;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.view.View;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.TextView;
import java.nio.charset.StandardCharsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.view.View;
import android.util.TypedValue;
import com.codex.cdevz.R;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.os.Handler;
import androidx.appcompat.widget.Toolbar;

public class EncryptorActivity extends AppCompatActivity {
    
	private Spinner TypeSpinner;
	private TypeAdapter TypeAdapter;
	private TextInputEditText encdec;
	private TextView text;
	private Button convert;
    private TextView txt1;
    private TextView txt2;
    public static View htoRelativeLayout;
	private final String[] encryptType = {"String to Byte",
		"String to Hex",
		"String to Binary",
		"Byte to String",
		"Hex to String", 
		"Binary to String" };
		private Toolbar tool_bar;
	
    @Override
	protected  void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.encryptor_layout);
	
	     tool_bar = (Toolbar) findViewById(R.id.tool_bar);
	     setSupportActionBar(tool_bar);
		 getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		encdec = (TextInputEditText) findViewById(R.id.encrypt_decrypt);
		text = (TextView) findViewById(R.id.activitymainTextView1);
		convert = (Button) findViewById(R.id.convert);
		convert.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View p1) {
        int tite = TypeSpinner.getSelectedItemPosition();
        String jabol = encdec.getText().toString();
        try {
            switch (tite) {
                case 0:
                    if (jabol.isEmpty()) {
                        customToast("Input Text to Convert Is Empty", "");
                    } else {
                        String inputText = encdec.getText().toString();
                        byte[] bytes = inputText.getBytes();
                        StringBuilder byteString = new StringBuilder();
                        for (byte b : bytes) {
                            byteString.append(b).append(",");
                        }
                        String displayText = byteString.toString();
                        text.setText(displayText);
                    }
                    break;
                case 1:
                    if (jabol.isEmpty()) {
                        customToast("Input Text to Convert Is Empty", "");
                    } else {
                        String inputText = encdec.getText().toString();
                        byte[] bytes = inputText.getBytes();
                        StringBuilder hexBuilder = new StringBuilder();
                        for (byte b : bytes) {
                            hexBuilder.append(String.format("%02X", b));
                        }
                        String hexString = hexBuilder.toString();
                        text.setText(hexString);
                    }
                    break;
                case 2:
                    if (jabol.isEmpty()) {
                        customToast("Input Text to Convert Is Empty", "");
                    } else {
                        String inputText = encdec.getText().toString();
                        StringBuilder binaryBuilder = new StringBuilder();
                        for (char c : inputText.toCharArray()) {
                            String binary = Integer.toBinaryString(c);
                            binaryBuilder.append(String.format("%8s", binary).replace(' ', '0'));
                        }
                        String binaryString = binaryBuilder.toString();
                        text.setText(binaryString);
                    }
                    break;
                case 3:
                    if (jabol.isEmpty()) {
                        customToast("Input Text to Convert Is Empty", "");
                    } else {
                        String bytesString = encdec.getText().toString();
                        String[] byteValues = bytesString.split(",");
                        byte[] bytes = new byte[byteValues.length];
                        for (int i = 0; i < byteValues.length; i++) {
                            bytes[i] = Byte.parseByte(byteValues[i]);
                        }
                        String decodedText = new String(bytes, StandardCharsets.UTF_8);
                        text.setText(decodedText);
                    }
                    break;
                case 4:
                    if (jabol.isEmpty()) {
                        customToast("Input Text to Convert Is Empty", "");
                    } else {
                        String hexString = encdec.getText().toString();
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < hexString.length(); i += 2) {
                            String hex = hexString.substring(i, i + 2);
                            int decimal = Integer.parseInt(hex, 16);
                            stringBuilder.append((char) decimal);
                        }
                        String decodedText = stringBuilder.toString();
                        text.setText(decodedText);
                    }
                    break;
                case 5:
                    if (jabol.isEmpty()) {
                        customToast("Input Text to Convert Is Empty", "");
                    } else {
                        String binaryString = encdec.getText().toString();
                        StringBuilder stringBuilder = new StringBuilder();
                        int length = binaryString.length();
                        for (int i = 0; i < length; i += 8) {
                            String binaryByte = binaryString.substring(i, Math.min(i + 8, length));
                            int decimal = Integer.parseInt(binaryByte, 2);
                            stringBuilder.append((char) decimal);
                        }
                        String decodedText = stringBuilder.toString();
                        text.setText(decodedText);
                    }
                    break;
            }
        } catch (Exception e) {
            // Handle the exception here
            e.printStackTrace();
            customToast("An error occurred during conversion: " + e.getMessage(), "");
        }
    }
});


		TypeSpinner = (Spinner) findViewById(R.id.activitymainSpinner1);
		TypeAdapter = new TypeAdapter(this, R.id.activitymainSpinner1, encryptType);
		TypeSpinner.setAdapter(TypeAdapter);

        final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);        
        text.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					String text = ((TextView) v).getText().toString();
					ClipData clipData = ClipData.newPlainText("Label", text);
					clipboardManager.setPrimaryClip(clipData);
					Toast.makeText(getApplicationContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();
					return true;
				}
			});

        htoRelativeLayout = findViewById(R.id.htoRelativeLayout1);
		htoRelativeLayout.setVisibility(View.GONE);
        txt1 = (TextView) findViewById(R.id.toastxt);
		txt2 = (TextView)findViewById(R.id.btntoastxt);
        txt1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
		txt2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);

    }

    public void customToast(String t1, String t2) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_in);
        htoRelativeLayout.setVisibility(View.VISIBLE);
        txt1.setText(t1);
        txt2.setText(t2);
        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    htoRelativeLayout.setVisibility(View.GONE);
                }
            }, 5000);
        htoRelativeLayout.startAnimation(anim);
	}
	
	
    
}
