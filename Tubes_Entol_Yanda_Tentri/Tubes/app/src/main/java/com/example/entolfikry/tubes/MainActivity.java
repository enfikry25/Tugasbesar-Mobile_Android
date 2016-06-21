package com.example.entolfikry.tubes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entolfikry.tubes.config.Config;
import com.example.entolfikry.tubes.config.RequestHandler;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNama, editTextUsername, editTextPassword, editTextPosisi, editTextPenyakit, editTexTinggi;
    Button btndftr ,buttonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNama = (EditText) findViewById(R.id.etnama);
        editTextUsername = (EditText) findViewById(R.id.etuser);
        editTextPassword = (EditText) findViewById(R.id.etpass);
        editTextPosisi = (EditText) findViewById(R.id.etposisi);
        editTextPenyakit = (EditText) findViewById(R.id.etpenyakit);
        editTexTinggi = (EditText) findViewById(R.id.ettinggi);

        btndftr = (Button) findViewById(R.id.buttondftr);
        buttonlogin = (Button) findViewById(R.id.buttonlog);

        btndftr.setOnClickListener(this);
        buttonlogin.setOnClickListener(this);

    }

    private void addAnggota(){

        final String nama = editTextNama.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String posisi = editTextPosisi.getText().toString().trim();
        final String penyakit = editTextPenyakit.getText().toString().trim();
        final String tinggi = editTexTinggi.getText().toString().trim();

        class AddAnggota extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Please...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_ANG_NAMA,nama);
                params.put(Config.KEY_ANG_USERNAME,username);
                params.put(Config.KEY_ANG_PASSWORD,password);
                params.put(Config.KEY_ANG_POSISI,posisi);
                params.put(Config.KEY_ANG_PENYAKIT,penyakit);
                params.put(Config.KEY_ANG_TINGGI,tinggi);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddAnggota ae = new AddAnggota();
        ae.execute();
    }


    @Override
    public void onClick(View view){
        if(view == btndftr){
            addAnggota();
            startActivity(new Intent(this,Login.class));
        }

        if(view == buttonlogin){
            startActivity(new Intent(this,Login.class));
        }
    }
}
