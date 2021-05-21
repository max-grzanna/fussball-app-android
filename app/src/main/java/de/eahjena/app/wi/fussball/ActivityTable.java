package de.eahjena.app.wi.fussball;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityTable extends AppCompatActivity {

    //hier link einfügen
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("tabelle.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set's the view to display content
        setContentView(R.layout.activity_tabelle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize the RecyclerView
        ArrayList<TableTeam> tableTeamList = new ArrayList<TableTeam>();

        //get data from json file if no success, output error to log
        try {
            JSONObject obj = new JSONObject(  loadJSONFromAsset() );
            JSONArray array = obj.getJSONArray("tabelle");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jo_inside = array.getJSONObject(i);

                //use optgetInt
                int teamid = jo_inside.getInt("id");
                String teamname = jo_inside.getString("name");
                int teamtore = jo_inside.getInt("tore");
                int teampunkte = jo_inside.getInt("punkte");
                int teammatches = jo_inside.getInt("spiele");
                String teamlogo = jo_inside.getString("logo");

                TableTeam team = new TableTeam(teamid,  teamname, teamtore, teampunkte, teammatches, teamlogo);
                tableTeamList.add(team);
            }
        } catch (JSONException e) {
            Log.e("JS error",e.getMessage());
        }

        //set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.team_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TableAdapter adapter = new TableAdapter(this, tableTeamList);
        //bind adapter to view
        recyclerView.setAdapter(adapter);
    }


}