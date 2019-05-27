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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public EditText Name;
    private EditText Password;
    private TextView Error;
    private Button Login;
    private RequestQueue mQueue;

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

        mQueue = Volley.newRequestQueue(this);

        Name = (EditText)findViewById(R.id.editText);
        Password = (EditText)findViewById(R.id.editText3);
        Login = (Button)findViewById(R.id.button);
        Error = (TextView) findViewById(R.id.editText2);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

    }
    private void validate (final String userName, final String userPassword){
        String url = "http://api.myjson.com/bins/7vyfu";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pais");
                    boolean sucesso = false;
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject pais = jsonArray.getJSONObject(i);

                        String login = pais.getString("login");
                        String senha = pais.getString("senha");
                        String filho = pais.getString("filho");
                        Usuario usuario = new Usuario();
                        usuario.setFilho(filho);
                        Session session = new Session(usuario);
                        if (login.equals(userName) && senha.equals((userPassword))){
                            sucesso = true;
                            if (login.substring(0, 1).equals("#")) {
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(intent);
                            }
                            if (login.substring(0, 1).equals("@")) {
                                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                                startActivity(intent);
                            }
                            break;
                        }
                    }
                    if (!sucesso){
                        TextView tv = MainActivity.this.findViewById(R.id.editText2);
                        tv.setText("Usuário ou senha incorretos");
                        tv.setVisibility(View.VISIBLE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
}
