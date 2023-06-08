package com.codex.cdevz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import com.codex.cdevz.databinding.ActivityMainBinding;
import com.itsaky.androidide.logsender.LogSender;
import android.widget.Button;
import android.content.Intent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.codex.cdevz.R;
import android.view.View;
import com.codex.cdevz.IPHuntActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

	
	private ActivityMainBinding binding;
	Button btnLogOut;
    FirebaseAuth mAuth;
	Toolbar tool_bar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		LogSender.startLogging(this);
        super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
		
		tool_bar = (Toolbar) findViewById(R.id.tool_bar);
	     setSupportActionBar(tool_bar);
		 //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		btnLogOut = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
	
        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
		}
		
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
	
	public void encrypt(View v){
		startActivity(new Intent(MainActivity.this, EncryptorActivity.class));
	}
	
    public void hunt(View v){
		startActivity(new Intent(MainActivity.this, IPHuntActivity.class));
	}
}
