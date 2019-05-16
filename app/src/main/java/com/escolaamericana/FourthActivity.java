package com.escolaamericana;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity {
    ListView listView;
    Button saidaAluno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.listaAlunos);
        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Maria Aparecida");
        arrayList.add("Jessica Caiado");
        arrayList.add("Tais Barbosa");
        arrayList.add("Natasha Burrel");
        arrayList.add("Solange Gomes");
        arrayList.add("Arcabouço");
        arrayList.add("Biggus Dickus");
        arrayList.add("Maria Aparecida");
        arrayList.add("Jessica Caiado");
        arrayList.add("Tais Barbosa");
        arrayList.add("Natasha Burrel");
        arrayList.add("Solange Gomes");
        arrayList.add("Arcabouço");
        arrayList.add("Biggus Dickus");
        arrayList.add("Maria Aparecida");
        arrayList.add("Jessica Caiado");
        arrayList.add("Tais Barbosa");
        arrayList.add("Natasha Burrel");
        arrayList.add("Solange Gomes");
        arrayList.add("Arcabouço");
        arrayList.add("Biggus Dickus");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, arrayList);
        listView.setAdapter(arrayAdapter);
        saidaAluno = (Button) findViewById(R.id.saidaAluno);
        saidaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                Intent intent = new Intent(FourthActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent2 = new Intent(FourthActivity.this, SecondActivity.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

}
