package com.escolaamericana;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class SeventhActivity extends AppCompatActivity {
    ListView listView;
    Button saidaAluno;
    String pid;
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    // JSON parser class
    JSONParser jsonParser = new JSONParser();

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

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick (AdapterView<?> parent, View view, int position, long id){
//                final int pos = position;
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeventhActivity.this);
//
//                // Set a title for alert dialog
//                builder.setTitle("Confirmar entrega");
//
//                // Ask the final question
//                builder.setMessage("Deseja confirmar a saída do aluno?");
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case DialogInterface.BUTTON_POSITIVE:
//                                // User clicked the Yes button
//                                setPos(pos);
//                                for (int i = pos; i < alunosArray.length; i++){
//                                    if (i == alunosArray.length - 1){
//                                        alunosArray[i] = "";
//                                        break;
//                                    }
//                                    alunosArray[i] = alunosArray[i+1];
//                                }
//                                listView.setAdapter(arrayAdapter);
//                                break;
//
//                            case DialogInterface.BUTTON_NEGATIVE:
//                                // User clicked the No button
//                                Toast.makeText(getApplicationContext(),
//                                        "Você cancelou a saída", Toast.LENGTH_SHORT).show();
//                                break;
//
//                            case DialogInterface.BUTTON_NEUTRAL:
//                                // Neutral/Cancel button clicked
//                                Toast.makeText(getApplicationContext(),
//                                        "Escolha uma opção", Toast.LENGTH_SHORT).show();
//                                break;
//                        }
//                    }
//                };
//                // Set the alert dialog yes button click listener
//                builder.setPositiveButton("Sim", dialogClickListener);
//
//                // Set the alert dialog no button click listener
//                builder.setNegativeButton("Não",dialogClickListener);
//
//                // Set the alert dialog cancel/neutral button click listener
//                builder.setNeutralButton("Cancela", dialogClickListener);
//
//                AlertDialog dialog = builder.create();
//                // Display the three buttons alert dialog on interface
//                dialog.show();
//            }
//
//        });
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
                Intent intent = new Intent(SeventhActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent2 = new Intent(SeventhActivity.this, SecondActivity.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
    private void setAlunos (){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, alunosArray);
        listView.setAdapter(arrayAdapter);
    }

    private void setPos (final int teste2){

        String url = "http://restrito.ccbeu.com/android_connect/get_all_alunos_temp.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        int test = teste2;
                        try {
                            JSONArray jsonArray = response.getJSONArray("alunos_temp");

                            JSONObject position = jsonArray.getJSONObject(test);
                            String positionString = position.getString("pid");
                            pid = positionString;
                            new DeleteProduct().execute();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setPos(teste2);
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }


    private void getAluno() {
        String url = "http://restrito.ccbeu.com/android_connect/get_all_alunos_temp.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {


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

    //    private void deleteAluno () {
//        String url_delete_product = "http://restrito.ccbeu.com/android_connect/delete_aluno_saida.php";
//
//
//        // Check for success tag
//        int success;
//        try {
//            // Building Parameters
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("pid", pid));
//
//            // getting product details by making HTTP request
//            JSONObject json = jsonParser.makeHttpRequest(
//                    url_delete_product, "POST", params);
//
//            // check your log for json response
//
//            // json success tag
//            success = json.getInt(TAG_SUCCESS);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
    class DeleteProduct extends AsyncTask<String, String, String> {
        String url_delete_product = "http://restrito.ccbeu.com/android_connect/delete_aluno_saida.php";
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SeventhActivity.this);
            pDialog.setMessage("Retirando aluno da lista");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Deleting product
         * */
        protected String doInBackground(String... args) {

            // Check for success tag
            int success;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("pid", pid));

            // getting product details by making HTTP request
            JSONObject json = jsonParser.makeHttpRequest(
                    url_delete_product, "POST", params);

            // check your log for json response
//                Log.d("Delete Product", json.toString());

            // json success tag

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();

        }

    }
}

