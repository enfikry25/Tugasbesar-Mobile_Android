package com.example.entolfikry.tubes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.entolfikry.tubes.config.Config;
import com.example.entolfikry.tubes.config.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuUtama extends AppCompatActivity implements  AdapterView.OnItemClickListener {

    Button jdwlbtn;
    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
        jdwlbtn = (Button) findViewById(R.id.buttonjadwal);
    }

    public void tombolJadwal (View v) {
        startActivity(new Intent(this,Jadwal.class));
    }

    private void showAnggota() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String nama = jo.getString(Config.TAG_NAMA);

                HashMap<String, String> anggota = new HashMap<>();
                anggota.put(Config.TAG_ID, id);
                anggota.put(Config.TAG_NAMA, nama);
                list.add(anggota);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                MenuUtama.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID, Config.TAG_NAMA},
                new int[]{R.id.id, R.id.nama});

        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MenuUtama.this, "Fetching Data", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showAnggota();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewAnggota.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String angid = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.ANG_ID, angid);
        startActivity(intent);

    }
}

