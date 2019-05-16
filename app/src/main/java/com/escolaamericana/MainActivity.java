package com.escolaamericana;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Error;
    private Button Login;
    private Button Cadastro;
    private TextView Aaaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)                {
            setContentView(R.layout.activity_main);
        }else {
            setContentView(R.layout.activity_main_land);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Name = (EditText)findViewById(R.id.editText);
        Password = (EditText)findViewById(R.id.editText3);
        Login = (Button)findViewById(R.id.button);
        Error = (TextView) findViewById(R.id.editText2);
        Cadastro = (Button)findViewById(R.id.button2);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        Cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FifthActivity.class);
                startActivity(intent);
            }
        });

    }
    private void validate (String userName, String userPassword){
        if (!(userName.substring(0, 1).equals("#") || userName.substring(0, 1).equals("@"))){
            TextView tv = MainActivity.this.findViewById(R.id.editText2);
            tv.setText("Usuário CCBEU deve começar com # e para pais, @.");
            tv.setVisibility(View.VISIBLE);
        }
        if (userName.substring(0, 1).equals("#")) {
            if (userName.equals("#ccbeu") && userPassword.equals("bueno123")) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            } else {
                TextView tv = MainActivity.this.findViewById(R.id.editText2);
                tv.setText("Usuário ou senha incorretos");
                tv.setVisibility(View.VISIBLE);
            }
        }
        if (userName.substring(0, 1).equals("@")) {
            if (userName.equals("@floriano") && userPassword.equals("pai")) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            } else {
                TextView tv = MainActivity.this.findViewById(R.id.editText2);
                tv.setText("Usuário ou senha incorretos");
                tv.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
}
