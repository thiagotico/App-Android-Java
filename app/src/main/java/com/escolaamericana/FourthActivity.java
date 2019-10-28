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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FourthActivity extends AppCompatActivity {
    ListView listView;
    Button saidaAluno;

    final String alunosArray [] = new String[50];
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        saidaAluno = (Button)findViewById(R.id.recarregar);
        saidaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        for (int i = 0; i < 50; i++){
            alunosArray[i] = "";
        }

        mQueue = Volley.newRequestQueue(this);
        getAluno();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.listaAlunos);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, alunosArray);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id){
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(FourthActivity.this);

                // Set a title for alert dialog
                builder.setTitle("Confirmar entrega");

                // Ask the final question
                builder.setMessage("Deseja confirmar a saída do aluno?");
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button
                                for (int i = pos; i < alunosArray.length; i++){
                                    if (i == alunosArray.length - 1){
                                        alunosArray[i] = "";
                                        break;
                                    }
                                    alunosArray[i] = alunosArray[i+1];
                                }
                                listView.setAdapter(arrayAdapter);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                // User clicked the No button
                                Toast.makeText(getApplicationContext(),
                                        "Você cancelou a saída", Toast.LENGTH_SHORT).show();
                                break;

                            case DialogInterface.BUTTON_NEUTRAL:
                                // Neutral/Cancel button clicked
                                Toast.makeText(getApplicationContext(),
                                        "Escolha uma opção", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };
                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Sim", dialogClickListener);

                // Set the alert dialog no button click listener
                builder.setNegativeButton("Não",dialogClickListener);

                // Set the alert dialog cancel/neutral button click listener
                builder.setNeutralButton("Cancela", dialogClickListener);

                AlertDialog dialog = builder.create();
                // Display the three buttons alert dialog on interface
                dialog.show();
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
    private void setAlunos (){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, alunosArray);
        listView.setAdapter(arrayAdapter);
    }
    private void validate (final String userName, final String userPassword){
        String url = "http://restrito.ccbeu.com/android_connect/get_all_alunos.php";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("dados_alunos");
                    boolean sucesso = false;
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject pais = jsonArray.getJSONObject(i);

                        String login = pais.getString("usuario");
                        String senha = pais.getString("senha");
                        String filho = pais.getString("nome_filho");
                        Usuario usuario = new Usuario();
                        usuario.setFilho(filho);
                        Session session = new Session(usuario);
                        alunosArray[i] = filho;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void getAluno() {
        String url = "http://restrito.ccbeu.com/android_connect/get_all_alunos_temp.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("alunos_temp");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject aluno = jsonArray.getJSONObject(i);
                                String pessoa = aluno.getString("aluno");
                                alunosArray[i] = pessoa;
                            }
                            setAlunos();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getAluno();
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

}
