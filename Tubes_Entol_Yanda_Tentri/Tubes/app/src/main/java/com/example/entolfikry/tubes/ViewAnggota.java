package com.example.entolfikry.tubes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entolfikry.tubes.config.Config;
import com.example.entolfikry.tubes.config.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewAnggota extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId ,editTextNama, editTextPosisi, editTextPenyakit, editTexTinggi;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_anggota);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.ANG_ID);

        editTextId = (EditText) findViewById(R.id.etid);
        editTextNama = (EditText) findViewById(R.id.etnama);
        editTextPosisi=(EditText) findViewById(R.id.etposisi);
        editTextPenyakit=(EditText) findViewById(R.id.etpenyakit);
        editTexTinggi=(EditText) findViewById(R.id.ettinggi);

        buttonUpdate = (Button) findViewById(R.id.btonupdt);
        buttonDelete = (Button) findViewById(R.id.btondlt);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getAnggota();
    }

    private void getAnggota(){
        class GetAnggota extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAnggota.this,"Please...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showAnggota(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ANG,id);
                return s;
            }
        }
        GetAnggota ge = new GetAnggota();
        ge.execute();
    }

    private void showAnggota(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama = c.getString(Config.TAG_NAMA);
            String posisi = c.getString(Config.TAG_POSISI);
            String penyakit = c.getString(Config.TAG_PENYAKIT);
            String tinggi = c.getString(Config.TAG_TINGGI);

            editTextNama.setText(nama);
            editTextPosisi.setText(posisi);
            editTextPenyakit.setText(penyakit);
            editTexTinggi.setText(tinggi);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateAnggota(){
        final String nama = editTextNama.getText().toString().trim();
        final String posisi = editTextPosisi.getText().toString().trim();
        final String penyakit = editTextPenyakit.getText().toString().trim();
        final String tinggi = editTexTinggi.getText().toString().trim();

        class UpdateAnggota extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAnggota.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewAnggota.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_ANG_ID,id);
                hashMap.put(Config.KEY_ANG_NAMA,nama);
                hashMap.put(Config.KEY_ANG_POSISI,posisi);
                hashMap.put(Config.KEY_ANG_PENYAKIT,penyakit);
                hashMap.put(Config.KEY_ANG_TINGGI,tinggi);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_ANG,hashMap);

                return s;
            }
        }

        UpdateAnggota ue = new UpdateAnggota();
        ue.execute();
    }

    private void deleteAnggota(){
        class DeleteAnggota extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAnggota.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewAnggota.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_ANG, id);
                return s;
            }
        }

        DeleteAnggota de = new DeleteAnggota();
        de.execute();
    }

    private void confirmDeleteAnggota(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah anda yakin ingin menghapus anggota ini?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteAnggota();
                        startActivity(new Intent(ViewAnggota.this,MenuUtama.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateAnggota();
        }

        if(v == buttonDelete){
            confirmDeleteAnggota();
        }
    }
}
