package hr.stips.mobilnetehnologije.Predavanja.Predavanje8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hr.stips.mobilnetehnologije.R;

public class Predavanje8_MainActivity extends AppCompatActivity {

    ArrayList<Rvdata> proSearch = new ArrayList<Rvdata>();
    RecyclerView rvTechSolPoint;//handler na recyclerView
    RvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p8_activity_main);

        rvTechSolPoint = findViewById(R.id.rv_techsolpoint);
        rvTechSolPoint.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvTechSolPoint.setLayoutManager(layoutManager);

        getServerData();
    }

    private void getServerData(){
        String urlGetServerData = "http://www.techsolpoint.com/api_example/api.json";
        System.out.print(urlGetServerData);

        //json object sa dva objekta u funkciji call back-a
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetServerData, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);

                try {
                    Gson gson = new Gson();
                    JSONArray jsonArray = response.getJSONArray("list");//parsiranje JSON-a

                    for (int p = 0; p < jsonArray.length(); p++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(p);
                        Rvdata rvdata = gson.fromJson(String.valueOf(jsonObject), Rvdata.class);//This method deserializes the Json read from the specified parse tree into an object of the specified type.
                        proSearch.add(rvdata);
                    }

                    rvAdapter = new RvAdapter(getApplicationContext(), proSearch);
                    rvTechSolPoint.setAdapter(rvAdapter);//gotov adapter se stavi na adapter view


                } catch (JSONException e) {//ako je neuspjelo parsiranje
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {//ako je neuspjeo response-a
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());//jedan (ili koji vise) thread za potencialni veci broj istovremenih requestova, ali se stave u red i sljedno se prazne
        requestQueue.add(jsonObjectRequest);

    }
}
