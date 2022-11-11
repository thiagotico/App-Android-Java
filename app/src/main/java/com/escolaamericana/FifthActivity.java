package com.escolaamericana;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FifthActivity extends AppCompatActivity {
    //teste
    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputNome;
    EditText inputFilho;
    EditText inputUsuario;
    EditText inputSenha;
    // url to create new product
    private static String url_create_product = "RESTRICTED";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    private Button Cadastro;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        // Edit Text
        inputNome = (EditText) findViewById(R.id.editText1);
        inputFilho = (EditText) findViewById(R.id.editText3);
        inputUsuario = (EditText) findViewById(R.id.editText4);
        inputSenha = (EditText) findViewById(R.id.editText5);

        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.button);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewProduct().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FifthActivity.this);
            pDialog.setMessage("Criando Usuário...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String name = inputNome.getText().toString();
            String price = inputFilho.getText().toString();
            String description = inputUsuario.getText().toString();
            String usuario = inputSenha.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("nome", name));
            params.add(new BasicNameValuePair("nome_filho", price));
            params.add(new BasicNameValuePair("usuario", description));
            params.add(new BasicNameValuePair("senha", usuario));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }

}