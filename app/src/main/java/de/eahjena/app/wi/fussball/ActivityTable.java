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

    //https://bezkoder.com/java-android-read-json-file-assets-gson/
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

        //Initialisiert the RecyclerView
        //In Android, RecyclerView provides an ability to implement the horizontal, vertical and Expandable List. It is mainly used when we have data collections whose elements can change at run time based on user action or any network events. For using this widget we have to specify the Adapter and Layout Manager.
        ArrayList<TableTeam> tableTeamList = new ArrayList<TableTeam>();

        //get data from json file if no success, output error to log
        try {
            JSONObject object = new JSONObject(loadJSONFromAsset());
            JSONArray array = object.getJSONArray("tabelle");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);


                int teamid = jsonObject.getInt("id");
                String teamname = jsonObject.getString("name");
                int teamtore = jsonObject.getInt("tore");
                int teampunkte = jsonObject.getInt("punkte");
                int teammatches = jsonObject.getInt("spiele");
                String teamlogo = jsonObject.getString("logo");

                TableTeam team = new TableTeam(teamid, teamname, teamtore, teampunkte, teammatches, teamlogo);
                tableTeamList.add(team);
            }
        } catch (JSONException e) {
            Log.e("JS error", e.getMessage());
        }

        //set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.team_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TableAdapter adapter = new TableAdapter(this, tableTeamList);
        //bind adapter to view
        recyclerView.setAdapter(adapter);
    }


}