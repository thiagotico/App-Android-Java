package com.escolaamericana;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class SecondActivity extends AppCompatActivity {
    Button btnScan;
    Button Lista;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)                {
            setContentView(R.layout.activity_second);
        }else {
            setContentView(R.layout.activity_second_land);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnScan = (Button) findViewById(R.id.btnScan);
        Lista = (Button)findViewById(R.id.button2);
        final Activity activity = this;
        Lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Camera Scan");
                integrator.setCameraId(0);
                integrator.initiateScan();
            }
        });
    }
    private void validate () {
        Intent intent = new Intent(SecondActivity.this, FourthActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                alert(result.getContents());
            }else {
                alert("Scan cancelado");
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void alert (String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
